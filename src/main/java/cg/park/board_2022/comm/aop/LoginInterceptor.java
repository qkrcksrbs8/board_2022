package cg.park.board_2022.comm.aop;

import cg.park.board_2022.comm.annotation.RequireLogin;
import cg.park.board_2022.comm.auth.JwtProvider;
import cg.park.board_2022.comm.utils.Message;
import cg.park.board_2022.comm.utils.Param;
import cg.park.board_2022.comm.utils.PcgModelAndView;
import com.sun.istack.NotNull;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LoginInterceptor {

    Logger logger = LoggerFactory.getLogger(super.getClass());

    @Autowired
    Message message;

    @Autowired
    JwtProvider jwt;

//    @Around("execution(* cg.park.board_2022..controllers.*.*(..))")
    @Around("@annotation(cg.park.board_2022.comm.annotation.RequireLogin)")
    public Object around(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        String type = joinPoint.getSignature().toShortString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.getParameterMap();


        PcgModelAndView mv = new PcgModelAndView();
        String token = request.getHeader("Authorization");
        if (null == token) return mv.set("redirect:/api/loginTest");


        try {
            Claims claims = jwt.parseJwtToken(token);
            Param param = new Param().set("claims", claims);
//            return new ResponseEntity<>(message.success(param), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(message.notAuth(), HttpStatus.OK);
        }

//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("redirect:/auth/login?nextUrl=" + URLEncoder.encode(url, "UTF-8"));

        logger.info("SESS_GUID = {}, ===================START===================");
        logger.info("SESS_GUID = {}, @Around : {}, param : {} ", type);
//        logger.info("SESS_GUID = {}, ===================START===================", sessGuid);
//        logger.info("SESS_GUID = {}, @Around : {}, param : {} ", sessGuid, type, logUtil.mapToStr(request.getParameterMap()));
        return joinPoint.proceed();
    }


}
