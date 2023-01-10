package cg.park.board_2022.comm.auth;

import cg.park.board_2022.comm.Entity.Member;
import cg.park.board_2022.comm.service.SignService;
import cg.park.board_2022.comm.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @Autowired
    SignService signService;

    @Autowired
    Message message;

    @GetMapping("/signUp")
    public String singUp() {
        return "auth/signUp";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "auth/signIn";
    }

    @PostMapping("/signIn")
    public ResponseEntity<Message> signInMember(Member member) {
        return signService.signInMember(member);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Message> signUpMember(Member member) {
        return signService.signUpMember(member);
    }

    @GetMapping("/token")
    public ResponseEntity<Message> tokenCheck(@RequestHeader(value = "Authorization") String token) {
        return signService.tokenCheck(token);
    }

}
