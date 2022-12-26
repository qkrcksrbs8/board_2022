package cg.park.board_2022.comm.service;

import cg.park.board_2022.comm.Entity.Member;
import cg.park.board_2022.comm.repository.MemberRepository;
import cg.park.board_2022.comm.utils.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignService {

    @Autowired
    MemberRepository memberRepository;

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
        if ("-1".equals(param.get("code"))) return false;
        return true;
    }

    private Param memberCheck(Member member) {
        if (null == member) return new Param("code", "-1");
        if ("".equals(member.getMemberId()) || "".equals(member.getPassword())) return new Param("code", "-1");
        return new Param("code", "1").set("member", member);
    }

    public Param signUp(Member member) {
        try {
            memberRepository.save(member);
            return new Param("code", "1");
        }
        catch (Exception e) {
            return new Param("code", "-2");
        }
    }
}
