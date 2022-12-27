package cg.park.board_2022.comm.aop;

import cg.park.board_2022.comm.annotation.RequireLogin;
import com.sun.istack.NotNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    @Around("execution(* cg.park.board_2022..controllers.*.*(..))")
    @Around("@annotation(cg.park.board_2022.comm.annotation.RequireLogin)")
    public Object around(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        String type = joinPoint.getSignature().toShortString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.getParameterMap();


//        ModelAndView mv = new ModelAndView();
//        String token = request.getHeader("Authorization");
//        if (null == token) return


//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("redirect:/auth/login?nextUrl=" + URLEncoder.encode(url, "UTF-8"));

        logger.info("SESS_GUID = {}, ===================START===================");
        logger.info("SESS_GUID = {}, @Around : {}, param : {} ", type);
//        logger.info("SESS_GUID = {}, ===================START===================", sessGuid);
//        logger.info("SESS_GUID = {}, @Around : {}, param : {} ", sessGuid, type, logUtil.mapToStr(request.getParameterMap()));
        return joinPoint.proceed();
    }


}
