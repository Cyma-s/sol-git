package wiki.vero.solgit.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wiki.vero.solgit.application.dto.GithubEventResponse;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GithubEvent {

    private String id;
    private GithubEventType type;
    private GithubRepository repository;

    public static GithubEvent from(final GithubEventResponse githubEventResponse) {
        return new GithubEvent(
            githubEventResponse.id(),
            GithubEventType.from(githubEventResponse.type()),
            GithubRepository.from(githubEventResponse.repo().name(), githubEventResponse.repo().url()));
    }
}
