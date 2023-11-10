package wiki.vero.solgit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiki.vero.solgit.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
