package wiki.vero.solgit.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubEventResponse(
    String id,
    String type,
    RepositoryResponse repo,
    PayloadResponse payload,

    @JsonProperty("created_at")
    String createdAt
) {

}
