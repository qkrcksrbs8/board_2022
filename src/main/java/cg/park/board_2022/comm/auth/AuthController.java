package cg.park.board_2022.comm.auth;

import cg.park.board_2022.comm.Entity.Member;
import cg.park.board_2022.comm.service.SignService;
import cg.park.board_2022.comm.utils.Message;
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
    public ResponseEntity<Param> signInMember(Member member) {

        Param loginCheck = signService.signCheck(member);
        if (!loginCheck.code().startsWith("S"))
            return new ResponseEntity<>(message.miss(), HttpStatus.OK);

        return new ResponseEntity<>(message.success(loginCheck), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Param> signUpMember(Member member) {

        if (!signService.duplicateMember(member.getMemberId()))
            return new ResponseEntity<>(new Param("code", "-1").set("message", "이미 존재하는 아이디입니다."), HttpStatus.OK);

        Param param = signService.signUp(member);
        if (param.code().startsWith("S")) {
            return new ResponseEntity<>(message.success(param), HttpStatus.OK);
        }
        return new ResponseEntity<>(message.fail(), HttpStatus.OK);
    }

}
