package wiki.vero.solgit.domain;

public enum PullRequestConstant {

    ZERO_PULL_REQUEST("조용하게 지나가는 하루네요 💬"),
    ONE_PULL_REQUEST("오늘의 PR 완료! 🥳"),
    MORE_THAN_TWO_PULL_REQUEST("%d번 PR을 올리시다니 부지런하시네요 😆"),
    ;

    private final String message;

    PullRequestConstant(String message) {
        this.message = message;
    }

    public static String getMessage(final int pullRequestCount) {
        if (pullRequestCount == 0) {
            return ZERO_PULL_REQUEST.message;
        }
        if (pullRequestCount == 1) {
            return ONE_PULL_REQUEST.message;
        }
        return String.format(MORE_THAN_TWO_PULL_REQUEST.message, pullRequestCount);
    }
}
