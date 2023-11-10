package wiki.vero.solgit.domain;

public enum TotalConstant {

    ZERO_COMMIT("여기에는 아무 일도 일어나지 않았습니다 👀"),
    ONE_COMMIT("잔디 심기 성공! 🚀"),
    MORE_THAN_TWO_COMMIT("프로 정원사시네요. 오늘 %d개의 잔디를 심었어요 🌱");

    private final String message;

    TotalConstant(String message) {
        this.message = message;
    }

    public static String getMessage(final int totalCount) {
        if (totalCount == 0) {
            return ZERO_COMMIT.message;
        }
        if (totalCount == 1) {
            return ONE_COMMIT.message;
        }
        return String.format(MORE_THAN_TWO_COMMIT.message, totalCount);
    }
}
