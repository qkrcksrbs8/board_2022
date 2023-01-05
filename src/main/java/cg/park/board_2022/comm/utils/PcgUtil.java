package cg.park.board_2022.comm.utils;

import cg.park.board_2022.comm.auth.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class PcgUtil {

    @Autowired
    JwtProvider jwt;

    public String convertStr(String str) {
        return (null == str) ? "" : str;
    }

    public boolean isValidatedToken() {
        try {
            jwt.parseJwtToken(userToken());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public String userToken() {
        return convertStr((((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getHeader("Authorization"));
    }

}
