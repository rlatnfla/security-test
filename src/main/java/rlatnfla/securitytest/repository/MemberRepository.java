package rlatnfla.securitytest.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rlatnfla.securitytest.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> getMemberByUsername(String username);
}
