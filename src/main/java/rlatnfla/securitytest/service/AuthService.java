package rlatnfla.securitytest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rlatnfla.securitytest.dto.SignupRequest;
import rlatnfla.securitytest.entity.Member;
import rlatnfla.securitytest.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long createMember(SignupRequest signupRequest) {
        String encodedPassword = bCryptPasswordEncoder.encode(signupRequest.getPassword());
        signupRequest.setPassword(encodedPassword);

        Member member = Member.of(signupRequest);
        memberRepository.save(member);
        return member.getId();
    }
}
