package cg.park.board_2022.comm.service;

import cg.park.board_2022.comm.Entity.Member;
import cg.park.board_2022.comm.auth.JwtProvider;
import cg.park.board_2022.comm.auth.MemberSession;
import cg.park.board_2022.comm.repository.MemberRepository;
import cg.park.board_2022.comm.utils.CookieUtil;
import cg.park.board_2022.comm.utils.HttpRequestHelper;
import cg.park.board_2022.comm.utils.Message;
import cg.park.board_2022.comm.utils.Param;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Service
public class SignService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    Message message;

    @Autowired
    JwtProvider jwt;

    @Autowired
    CookieUtil cookieUtil;

    public Param signCheck(Member member) {
        return (!isMember(memberCheck(member)))
                ? new Param().fail()
                : memberCheck(memberRepository.findByMemberId(member.getMemberId()));
    }

    private boolean isMember(Param param) {
        return (null == param || "".equals(param.get("code")))
                ? false
                : param.code().startsWith("S");
    }

    private Param memberCheck(Member member) {
        return (null == member)
                ? new Param().fail()
                : memberIdPwNullCheck(member);
    }

    private Param memberIdPwNullCheck(Member member) {
        return (isEmptyIdPw(member))
                ? new Param().fail()
                : new Param().success().set("data", member);
    }

    private boolean isEmptyIdPw(Member member) {
        return "".equals(member.getMemberId()) || "".equals(member.getPassword());
    }

    public Param signUp(Member member) {
        try {
            memberRepository.save(member);
            return new Param().fail();
        }
        catch (Exception e) {
            return new Param("code", "F002");
        }
    }

    public boolean duplicateMember(String memberId) {
        return null != memberRepository.findByMemberId(memberId);
    }

    public ResponseEntity<Message> signInMember(Member member) {
        // Null 체크
        // ID 체크
        // PW 체크

        // 아이디로 계정 조회
        // Member member = memberService.getMember(memberId);

        // 계정 활성화 유무 체크
        // 파라미터 비밀번호 암호화 후 DB 비밀번호와 일치 체크
        // 패스워드 변경 6개월 지나면 변경 안내 팝업
        // 세션에 정보 담기

        // 로그인 로그 쌓기

//        MemberSession memberSession = MemberSession.getCurrentInstance(request);
//
//        Member login = new Member();
//        login.setMemberId(member.getMemberId());
//        login.setName(member.getName());
//        login.setLastLoginDate(member.getLastLoginDate());
//        login.setLoginSessionKey(loginSessionKey);
//        login.setIp(HttpRequestHelper.getRequestIp());
//        memberSession.login(login);


       Param loginCheck = signCheck(member);
        if (!loginCheck.code().startsWith("S"))
            return new ResponseEntity<>(message.miss(), HttpStatus.OK);

        try {
            loginCheck.set("token", jwt.create(member.getMemberId()));
            return new ResponseEntity<>(message.success(loginCheck), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(message.fail(loginCheck), HttpStatus.OK);
        }
    }

    public ResponseEntity<Message> signUpMember(Member member) {
        if (!duplicateMember(member.getMemberId()))
            return new ResponseEntity<>(message.miss(), HttpStatus.OK);

        return new ResponseEntity<>(message.successAndFile(signUp(member)), HttpStatus.OK);
    }

    public ResponseEntity<Message> tokenCheck(String token) {
        try {
            Claims claims = jwt.parseJwtToken(token);
            Param param = new Param().set("claims", claims);
            return new ResponseEntity<>(message.success(param), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(message.notAuth(), HttpStatus.OK);
        }
    }

    // 로그아웃
    @RequestMapping("/logout")
    public String logout() throws Exception {
        HttpServletRequest request = HttpRequestHelper.getCurrentRequest();

        MemberSession memberSession = MemberSession.getCurrentInstance(request);

        memberSession.logout();

        return "redirect:/login";
    }

    public void cookieAddToken (String token) {
//        cookieUtil.setCookie(HttpRequestHelper.getCurrentResponse(), MemberConsts.COOKIE_APP_WIDGET_TOKEN, token, null, 120 * 86400);
    }


}
