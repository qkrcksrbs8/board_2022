package cg.park.board_2022.comm.auth;

import cg.park.board_2022.comm.Entity.Member;
import cg.park.board_2022.comm.service.SignService;
import cg.park.board_2022.comm.utils.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @Autowired
    SignService signService;

    @GetMapping("/signUp")
    public String singUp() {
        return "auth/signUp";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "auth/signIn";
    }

    @PostMapping("/signIn")
    public String signInMember(Member member) {

        Param loginCheck = signService.signCheck(member);

        return "auth/signIn";
    }

    @PostMapping("/signUp")
    public ResponseEntity<Param> signUpMember(Member member) {
        Param param = signService.signUp(member);
        if ("1".equals(param.code())) {
            return new ResponseEntity<>(param, HttpStatus.OK);
        }
        return new ResponseEntity<>(param, HttpStatus.OK);
    }

}
