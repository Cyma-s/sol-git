package wiki.vero.solgit.application.dto;


import java.util.List;

public record PayloadResponse(
    String pushId, int size, List<CommitResponse> commits
) {

}
