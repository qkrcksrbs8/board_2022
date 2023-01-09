package cg.park.board_2022.comm.aop;


import cg.park.board_2022.comm.utils.PcgUtil;
import com.sun.istack.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ParamLogAop {

    @Autowired
    PcgUtil pcgUtil;

    Logger logger = LoggerFactory.getLogger(this.getClass());



    //    @Around("execution(* cg.park.springlotto..controllers.*.*(..)) || execution(* cg.park.springlotto..services.*.*(..))")
    @Around("execution(* cg.park.board_2022..controllers.*.*(..))")
    public Object around(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        String type = joinPoint.getSignature().toShortString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        logger.info("SSID = {}, ===================START===================", pcgUtil.requestedSessionId());
        logger.info("SSID = {}, @Around : {}, param : {} ", pcgUtil.requestedSessionId(), type, pcgUtil.mapToStr(request.getParameterMap()));
        return joinPoint.proceed();
    }

    //    @Before("execution(* cg.park.springlotto..controllers.*.*(..)) || execution(* cg.park.springlotto..services.*.*(..))")
    @Before("execution(* cg.park.board_2022..controllers.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String type = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (args.length == 0 || args[0] instanceof HttpServletRequest){
            logger.info("SSID = {}, @Before : {}, param : {}", pcgUtil.requestedSessionId(), type, pcgUtil.mapToStr(request.getParameterMap()));
        }else{
            logger.info("SSID = {}, @Before : {}, param : {}", pcgUtil.requestedSessionId(), type, pcgUtil.setParamParse(args[0].toString(), "="));
        }
    }

    //    @AfterReturning(pointcut = "execution(* cg.park.springlotto..controllers.*.*(..)) || execution(* cg.park.springlotto..services.*.*(..))", returning="retValue")
    @AfterReturning(pointcut = "execution(* cg.park.board_2022..controllers.*.*(..))", returning="retValue")
    public void after(JoinPoint joinPoint, Object retValue) {
        String type = joinPoint.getSignature().toShortString();
        logger.info("SSID = {}, @After : {}, result : {}", pcgUtil.requestedSessionId(), type, retValue);
        logger.info("SSID = {}, ===================E N D===================", pcgUtil.requestedSessionId());
    }

    //    @AfterThrowing(pointcut = "execution(* cg.park.springlotto..controllers.*.*(..)) || execution(* cg.park.springlotto..services.*.*(..))", throwing = "ex")
    @AfterThrowing(pointcut = "execution(* cg.park.board_2022..controllers.*.*(..))", throwing = "ex")
    public void afterThrowingAnException(JoinPoint joinPoint, Exception ex) {
        logger.info("SSID = {}", pcgUtil.requestedSessionId());
        logger.info("SSID = {}, ===================E N D===================", pcgUtil.requestedSessionId());
    }

}
