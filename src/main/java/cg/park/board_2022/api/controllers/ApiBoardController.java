package cg.park.board_2022.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiBoardController {

    @GetMapping("/test")
    public String index() {
        return "test";
    }
}
