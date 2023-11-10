package wiki.vero.solgit.domain;

import java.util.Arrays;

public enum GithubEvent {

    ISSUE("IssuesEvent"),
    PULL_REQUEST("PullRequestEvent"),
    COMMIT("PushEvent")
    ;

    private final String event;

    GithubEvent(final String event) {
        this.event = event;
    }

    public static GithubEvent from(final String event) {
        return Arrays.stream(values())
                .filter(githubEvent -> githubEvent.event.equals(event))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found event: " + event));
    }
}
