package cg.park.board_2022.api.controllers;

import cg.park.board_2022.comm.annotation.RequireLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiBoardController {

    @RequireLogin
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/loginTest")
    public String LoginTest() {
        return "loign plz";
    }

}
