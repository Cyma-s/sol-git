package wiki.vero.solgit.domain;

import java.util.Arrays;

public enum GithubEventType {

    ISSUE("IssuesEvent"),
    PULL_REQUEST("PullRequestEvent"),
    COMMIT("PushEvent"),
    ELSE("Else");

    private final String event;

    GithubEventType(final String event) {
        this.event = event;
    }

    public static GithubEventType from(final String event) {
        return Arrays.stream(values())
            .filter(githubEventType -> githubEventType.event.equals(event))
            .findFirst()
            .orElse(ELSE);
    }
}
