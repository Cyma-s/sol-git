package wiki.vero.solgit.application.dto;

import java.util.List;

public record StatisticsResponse(int commitCount, int issueCount, int pullRequestCount) {

    public static StatisticsResponse of(final List<GithubEventResponse> responses) {
        return new StatisticsResponse(responses.size(), 0, 0);
    }
}
