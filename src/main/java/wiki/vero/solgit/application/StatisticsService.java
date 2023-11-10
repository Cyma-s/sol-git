package wiki.vero.solgit.application;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wiki.vero.solgit.application.dto.GithubEventResponse;
import wiki.vero.solgit.application.dto.StatisticsResponse;
import wiki.vero.solgit.domain.GithubEvent;
import wiki.vero.solgit.domain.GithubEventType;
import wiki.vero.solgit.domain.Member;
import wiki.vero.solgit.domain.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class StatisticsService {

    private static final String GITHUB_LINK = "https://api.github.com";

    private final RestTemplate restTemplate;
    private final MemberRepository memberRepository;

    public StatisticsResponse showStatistics(Long memberId) {
        final Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        final List<GithubEvent> githubEvents = getEventsByRepository(member);

        return StatisticsResponse.of(githubEvents);
    }

    private List<GithubEvent> getEventsByRepository(Member member) {
        int page = 1;
        List<GithubEventResponse> responses = getGithubEventsByPage(member, page);
        final List<GithubEventResponse> result = new ArrayList<>(responses);

        while (!isLastPage(responses)) {
            responses = getGithubEventsByPage(member, ++page);
            result.addAll(responses);
        }

        final List<GithubEventResponse> filteredResponses = result.stream()
            .filter(localDateTime -> ZonedDateTime.parse(localDateTime.createdAt(), DateTimeFormatter.ISO_DATE_TIME)
                .toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))
            .toList();

        return filteredResponses.stream()
            .map(GithubEvent::from)
            .filter(githubEvent -> githubEvent.getType() == GithubEventType.ELSE)
            .toList();
    }

    private List<GithubEventResponse> getGithubEventsByPage(Member member, final int page) {
        String url =
            GITHUB_LINK + "/users/" + member.getNickname() + "/events?page=" + page;
        System.out.println(url);

        return restTemplate.exchange(
            url, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<GithubEventResponse>>() {
            }).getBody();
    }

    private boolean isLastPage(List<GithubEventResponse> responses) {
        return responses.stream()
            .map(GithubEventResponse::createdAt)
            .map(this::getLocalDateTime)
            .anyMatch(localDateTime -> localDateTime.toLocalDate()
                .isBefore(LocalDateTime.now().minusDays(1).toLocalDate()));
    }

    private LocalDateTime getLocalDateTime(String time) {
        final ZonedDateTime zonedDateTime = ZonedDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        return zonedDateTime.toLocalDateTime();
    }
}
