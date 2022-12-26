package cg.park.board_2022.comm.service;

import cg.park.board_2022.comm.Entity.Member;
import cg.park.board_2022.comm.auth.JwtProvider;
import cg.park.board_2022.comm.repository.MemberRepository;
import cg.park.board_2022.comm.utils.Message;
import cg.park.board_2022.comm.utils.Param;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    Message message;

    @Autowired
    JwtProvider jwt;

    public Param signCheck(Member member) {
        Param memberCheck = memberCheck(member);
        if (!isMember(memberCheck)) return memberCheck;

        Member findMember = memberRepository.findByMemberId(member.getMemberId());
        Param findMemberCheck = memberCheck(findMember);
        if (!isMember(findMemberCheck)) return findMemberCheck;

        return findMemberCheck;
    }

    private boolean isMember(Param param) {
        if (null == param || "".equals(param.get("code"))) return false;
        if (!param.code().startsWith("S")) return false;
        return true;
    }

    private Param memberCheck(Member member) {
        if (null == member) return new Param("code", "F001");
        if ("".equals(member.getMemberId()) || "".equals(member.getPassword())) return new Param("code", "F001");
        return new Param("code", "S001").set("data", member);
    }

    public Param signUp(Member member) {
        try {
            memberRepository.save(member);
            return new Param("code", "S001");
        }
        catch (Exception e) {
            return new Param("code", "F002");
        }
    }

    public boolean duplicateMember(String memberId) {
        return null != memberRepository.findByMemberId(memberId);
    }

    public ResponseEntity<Message> signInMember(Member member) {
        Param loginCheck = signCheck(member);
        if (!loginCheck.code().startsWith("S"))
            return new ResponseEntity<>(message.miss(), HttpStatus.OK);

        try {
            loginCheck.set("token", jwt.create(member.getMemberId()));
            return new ResponseEntity<>(message.success(loginCheck), HttpStatus.OK);
        }
        catch (Exception e) {

        }
        return new ResponseEntity<>(message.success(loginCheck), HttpStatus.OK);
    }

    public ResponseEntity<Message> signUpMember(Member member) {
        if (!duplicateMember(member.getMemberId()))
            return new ResponseEntity<>(message.miss(), HttpStatus.OK);

        Param param = signUp(member);
        if (param.code().startsWith("S")) {
            return new ResponseEntity<>(message.success(param), HttpStatus.OK);
        }
        return new ResponseEntity<>(message.fail(), HttpStatus.OK);
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

}
