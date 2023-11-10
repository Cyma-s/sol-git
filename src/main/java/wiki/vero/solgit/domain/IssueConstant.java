package wiki.vero.solgit.domain;

public enum IssueConstant {

    ZERO_ISSUE("이슈 없이 하루를 마무리 하셨네요 🔥"),
    MORE_THAN_ONE_ISSUE("🚨 %d개의 이슈 발생 🚨"),
    ;

    private final String message;

    IssueConstant(String message) {
        this.message = message;
    }

    public static String getMessage(final int issueCount) {
        if (issueCount == 0) {
            return ZERO_ISSUE.message;
        }
        return String.format(MORE_THAN_ONE_ISSUE.message, issueCount);
    }
}
