package wiki.vero.solgit.application.dto;

import java.util.List;
import wiki.vero.solgit.domain.CommitConstant;
import wiki.vero.solgit.domain.GithubEvent;
import wiki.vero.solgit.domain.GithubEventType;
import wiki.vero.solgit.domain.IssueConstant;
import wiki.vero.solgit.domain.PullRequestConstant;
import wiki.vero.solgit.domain.TotalConstant;

public record StatisticsResponse(String total, String commit, String issue, String pullRequest) {

    public static StatisticsResponse of(final List<GithubEvent> responses) {
        final List<GithubEvent> commitEvents = responses.stream()
            .filter(githubEvent -> githubEvent.getType() == GithubEventType.COMMIT)
            .toList();
        final List<GithubEvent> issueEvents = responses.stream()
            .filter(githubEvent -> githubEvent.getType() == GithubEventType.ISSUE)
            .toList();
        final List<GithubEvent> pullRequestEvents = responses.stream()
            .filter(githubEvent -> githubEvent.getType() == GithubEventType.PULL_REQUEST)
            .toList();

        System.out.println(commitEvents.size());
        System.out.println(issueEvents.size());
        System.out.println(pullRequestEvents.size());

        return new StatisticsResponse(
            TotalConstant.getMessage(commitEvents.size() + issueEvents.size() + pullRequestEvents.size()),
            CommitConstant.getMessage(commitEvents.size()),
            IssueConstant.getMessage(issueEvents.size()),
            PullRequestConstant.getMessage(pullRequestEvents.size())
        );
    }
}
