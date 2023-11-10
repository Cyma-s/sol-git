package wiki.vero.solgit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class GithubRepository {

    @Id
    private Long id;
    private String name;
    private String url;

    public static GithubRepository forSave(final String name, final String url) {
        return new GithubRepository(null, name, url);
    }
}
