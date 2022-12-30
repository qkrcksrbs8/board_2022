package cg.park.board_2022.comm.utils;

import org.springframework.web.servlet.ModelAndView;

public class PcgModelAndView extends ModelAndView {

    public PcgModelAndView () {}
    public PcgModelAndView set(String key, Object obj) {
        super.addObject(key, obj);
        return this;
    }
    public PcgModelAndView set(String viewName) {
        super.setViewName(viewName);
        return this;
    }
}
