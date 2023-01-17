package cg.park.board_2022.comm.auth;

import cg.park.board_2022.comm.Entity.Member;
import cg.park.board_2022.comm.utils.HttpRequestHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MemberSession {

    private static String MEMBER_SESSION_NAME = "PCG_MEMBER";

    private HttpServletRequest request = null;
    private HttpSession session = null;

    private String empty = "";
    private Member member = null;

    public MemberSession() {
        this.request = HttpRequestHelper.getCurrentRequest();

        this.session = request.getSession();

        if(session.getAttribute(MemberSession.MEMBER_SESSION_NAME) != null) {
            member = (Member) session.getAttribute(MemberSession.MEMBER_SESSION_NAME);
        }

    }

    /**
     * 생성자
     *
     * @param request HttpServletRequest
     */
    public MemberSession(HttpServletRequest request) {
        this.request = request;

        this.session = request.getSession();

        if(session.getAttribute(MemberSession.MEMBER_SESSION_NAME) != null) {
            member   =   (Member) session.getAttribute(MemberSession.MEMBER_SESSION_NAME);
        }
    }

    public static MemberSession getCurrentInstance() {
        return new MemberSession(HttpRequestHelper.getCurrentRequest());
    }

    /**
     * 현재 로그인 정보 인스턴스를 반환.
     *
     * @param request HttpServletRequest
     * @return AdminSession Instance
     */
    public static MemberSession getCurrentInstance(HttpServletRequest request) {
        return new MemberSession(request);
    }

    public void login(Member member) {
        session.setMaxInactiveInterval(30 * 60);
        session.setAttribute(MemberSession.MEMBER_SESSION_NAME, member);
    }

    public void logout() {
        session.invalidate();
    }

    /**
     * 로그인 여부를 반환
     *
     * @return 로그인 여부
     */
    public boolean isLogin() {
        return member != null;
    }

    public Member getMember() {
        if(!isLogin()) {
            return null;
        }

        return member;
    }

    public String getMemberId() {
        if(!isLogin()) {
            return empty;
        }
        return member.getMemberId();
    }

    public String getName() {
        if(!isLogin()) {
            return empty;
        }

        return member.getName();
    }

//    public String getIp() {
//        if(!isLogin()) {
//            return empty;
//        }
//
//        return member.getIp();
//    }

//    public String getLoginSessionKey() {
//        if(!isLogin()) {
//            return empty;
//        }
//
//        return member.getLoginSessionKey();
//    }

    public void setMemberSession(Member admin) {
        session.setAttribute(MemberSession.MEMBER_SESSION_NAME, admin);
    }
}
