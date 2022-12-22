package cg.park.board_2022.comm.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@ControllerAdvice
public class ControllerAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);
    @ExceptionHandler({RequireLoginException.class})
    public ModelAndView badRequestError(HttpServletRequest request, HttpServletResponse response, RequireLoginException e, HandlerMethod handlerMethod) throws UnsupportedEncodingException, JsonProcessingException {
        UrlPathHelper helper = new UrlPathHelper();
//        String url = helper.getOriginatingRequestUri(request) + (StringUtils.isNotEmpty(helper.getOriginatingQueryString(request))? "?" + helper.getOriginatingQueryString(request) : "");
        String requestedWithHeader = request.getHeader("X-Requested-With");
//        if (StringUtils.isNotEmpty(e.getMessage()) && "-9404".equals(e.getMessage())) {
//            if (e.isRequireLogin()) {
//                if ("XMLHttpRequest".equals(requestedWithHeader)) {
//                    Message message = new Message(false, "동일 아이디로 중복 로그인 하여 자동으로 로그아웃 되었습니다.", new Param("FAIL", "LoginFail"));
//                    ObjectMapper mapper = new ObjectMapper();
//                    return Utils.sendMessageAjax(response, mapper.writeValueAsString(message));
//                } else {
//                    return Utils.sendMessage(response, "동일 아이디로 중복 로그인 하여 자동으로 로그아웃 되었습니다.", "/auth/login?returnUrl=" + url);
//                }
//            } else {
//                if ("XMLHttpRequest".equals(requestedWithHeader)) {
//                    Message message = new Message(false, "동일 아이디로 중복 로그인 하여 자동으로 로그아웃 되었습니다.", new Param("FAIL", "LoginFail"));
//                    ObjectMapper mapper = new ObjectMapper();
//                    return Utils.sendMessageAjax(response, mapper.writeValueAsString(message));
//                } else {
//                    return Utils.sendMessage(response, "동일 아이디로 중복 로그인 하여 자동으로 로그아웃 되었습니다.", url);
//                }
//            }
//        }

//        if ("XMLHttpRequest".equals(requestedWithHeader)) {
//            Message message = new Message(false, "로그인 후 이용가능합니다.", new Param("FAIL", "LoginFail"));
//            ObjectMapper mapper = new ObjectMapper();
//            return Utils.sendMessageAjax(response, mapper.writeValueAsString(message));
//        }
        ModelAndView mv = new ModelAndView();

//        mv.setViewName("redirect:/auth/login?nextUrl=" + URLEncoder.encode(url, "UTF-8"));
        return mv;
    }

}
