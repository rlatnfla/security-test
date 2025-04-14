package rlatnfla.securitytest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rlatnfla.securitytest.dto.LoginUserDetails;
import rlatnfla.securitytest.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.getMemberByEmail(email)
            .map(member -> new LoginUserDetails(member.getEmail(), member.getPassword()))
            .orElseThrow(() -> new UsernameNotFoundException("user is not exist"));
    }
}