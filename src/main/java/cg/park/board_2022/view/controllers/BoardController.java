package cg.park.board_2022.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/")
    public String main() {
        return indexView();
    }

    @GetMapping("/index")
    public String index() {
        return indexView();
    }

    public String indexView() {
        return "index";
    }

}
