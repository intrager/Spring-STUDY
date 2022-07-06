package com.study.auth.security.service;


import com.study.auth.entity.AuthMemberEntity;
import com.study.auth.repository.AuthMemberRepository;
import com.study.auth.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 빈으로 등록되면 스프링 시큐리티에서 자동으로 UserDetailsService로 인식하기 때문에
 * configure(AuthenticationManagerBuilder auth) 부분을 사용하지 않도록 수정
 * 혹은
 * AuthUserDetailsService를 주입받아서 구성하는 코드를 작성할 수 있음
 */
@Log4j2
@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final AuthMemberRepository authMemberRepository;

    public AuthUserDetailsService(AuthMemberRepository authMemberRepository) {
        this.authMemberRepository = authMemberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("AuthUserDetailsService loadUserByUsername " + username);

        Optional<AuthMemberEntity> result = authMemberRepository.findByEmail(username, false);

        if(result.isEmpty()) {  // isPresent()는 왜 안 되는겨
            throw new UsernameNotFoundException("Check Email or Social");
        }

        AuthMemberEntity authMemberEntity = result.get();

        log.info("---------------------------------");
        log.info(authMemberEntity);

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                authMemberEntity.getEmail(),
                authMemberEntity.getPassword(),
                authMemberEntity.isFromSocial(),
                authMemberEntity.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toSet())
        );

        authMemberDTO.setName(authMemberEntity.getName());
        authMemberDTO.setFromSocial(authMemberEntity.isFromSocial());

        return authMemberDTO;
    }
}
