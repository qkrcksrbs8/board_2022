package cg.park.board_2022.comm.aop;

import cg.park.board_2022.comm.auth.JwtProvider;
import com.sun.istack.NotNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LoginInterceptor {

    @Autowired
    JwtProvider jwt;

    @Around("@annotation(cg.park.board_2022.comm.annotation.RequireLogin)")
    public Object around(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        return (!isLogin()) ? "/auth/signIn" : joinPoint.proceed();
    }

    private boolean isLogin() {
        try {
            jwt.parseJwtToken(userToken());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String userToken() {
        return (((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getHeader("Authorization");
    }

}
