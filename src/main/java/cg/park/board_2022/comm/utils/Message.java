package cg.park.board_2022.comm.utils;

import org.springframework.stereotype.Component;

@Component
public class Message extends Param {

    public Message () {}
    public Message (String code) {
        if (null == code) {
            nullPoint();
        }
        else if (code.startsWith("S")) {
            success();
        }
        else if (code.startsWith("F")) {
            fail();
        }
        else if (code.startsWith("P")) {
            miss();
        }
    }

    public Message success() {
        super.set("code", "S001").set("message", "정상 처리되었습니다.");
        return this;
    }
    public Message success(Param param) {
        super.putAll(param);
        super.set("code", "S001").set("message", "정상 처리되었습니다.");
        return this;
    }

    public Message fail() {
        super.set("code", "F001").set("message", "처리 중 오류가 발생했습니다.");
        return this;
    }
    public Message fail(Param param) {
        super.putAll(param);
        super.set("code", "F001").set("message", "처리 중 오류가 발생했습니다.");
        return this;
    }

    public Message miss() {
        super.set("code", "P001").set("message", "파라미터를 확인해주세요.");
        return this;
    }
    public Message miss(Param param) {
        super.putAll(param);
        super.set("code", "P001").set("message", "파라미터를 확인해주세요.");
        return this;
    }

    public Message nullPoint() {
        super.set("code", "N001").set("message", "메시지 코드 값이 없습니다.");
        return this;
    }
    public Message nullPoint(Param param) {
        super.putAll(param);
        super.set("code", "N001").set("message", "메시지 코드 값이 없습니다.");
        return this;
    }

    public Message notAuth() {
        super.set("code", "P101").set("message", "유효하지 않은 토큰입니다.");
        return this;
    }

    public Message successAndFile(Param param) {
        return param.code().startsWith("S") ? success(param) : fail();
    }

}
