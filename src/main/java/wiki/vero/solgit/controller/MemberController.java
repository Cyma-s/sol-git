package wiki.vero.solgit.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.vero.solgit.application.MemberService;
import wiki.vero.solgit.application.dto.LoginRequest;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody LoginRequest loginRequest) {
        final Long userId = memberService.signup(loginRequest);

        return ResponseEntity.created(URI.create("/user/" + userId)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        memberService.login(loginRequest.token());

        return ResponseEntity.ok().build();
    }
}
