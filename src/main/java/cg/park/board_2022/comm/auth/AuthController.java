package cg.park.board_2022.comm.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

}
