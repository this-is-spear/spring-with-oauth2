package hello.springwithoauth2.auth;

import hello.springwithoauth2.conifg.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final HttpSession session;

    @GetMapping("/")
    public ResponseEntity<SessionUser> index() {
        SessionUser user = (SessionUser) session.getAttribute("user");
        return ResponseEntity.ok(user);
    }
}
