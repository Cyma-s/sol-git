package wiki.vero.solgit.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.vero.solgit.application.dto.LoginRequest;
import wiki.vero.solgit.domain.Member;
import wiki.vero.solgit.domain.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signup(LoginRequest loginRequest) {
        final Member member = Member.forSave(loginRequest.nickname(), loginRequest.token());

        return memberRepository.save(member).getId();
    }

    public void login(String token) {
        memberRepository.findByGithubToken(token)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }
}
