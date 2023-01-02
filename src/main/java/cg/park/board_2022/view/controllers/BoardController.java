package cg.park.board_2022.view.controllers;

import cg.park.board_2022.comm.annotation.RequireLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // mac 연동
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

    @GetMapping("/boards")
    public String boards() {
        return "board/boards";
    }

}
