package wiki.vero.solgit.domain;

public enum CommitConstant {

    ZERO_COMMIT("이런, 오늘은 커밋을 하지 않으셨네요 🥺 조금 더 힘내 봐요!"),
    ONE_COMMIT("1일 1 커밋 달성! 🫡"),
    MORE_THAN_TWO_COMMIT("굉장해요! %d번이나 커밋을 하셨네요 😄");

    private final String message;

    CommitConstant(String message) {
        this.message = message;
    }

    public static String getMessage(final int commitCount) {
        if (commitCount == 0) {
            return ZERO_COMMIT.message;
        }
        if (commitCount == 1) {
            return ONE_COMMIT.message;
        }
        return String.format(MORE_THAN_TWO_COMMIT.message, commitCount);
    }
}
