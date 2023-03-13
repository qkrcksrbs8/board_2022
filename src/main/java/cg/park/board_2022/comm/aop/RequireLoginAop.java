package cg.park.board_2022.comm.aop;

import cg.park.board_2022.comm.utils.PcgUtil;
import com.sun.istack.NotNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequireLoginAop {

    @Autowired
    PcgUtil pcgUtil;

    @Around("@annotation(cg.park.board_2022.comm.annotation.RequireLogin)")
    public Object around(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        // login log
        return (!pcgUtil.isValidatedToken()) ? "/auth/signIn" : joinPoint.proceed();
    }

}
