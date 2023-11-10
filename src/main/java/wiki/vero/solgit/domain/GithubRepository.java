package wiki.vero.solgit.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GithubRepository {

    private Long id;
    private String name;
    private String url;

    public static GithubRepository from(final String name, final String url) {
        return new GithubRepository(null, name, url);
    }
}
