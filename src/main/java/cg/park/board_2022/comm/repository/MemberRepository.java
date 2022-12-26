package cg.park.board_2022.comm.repository;

import cg.park.board_2022.comm.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberId(String memberId);
}
