package com.study.auth.security.service;

import com.study.auth.entity.AuthMemberEntity;
import com.study.auth.entity.AuthMemberRole;
import com.study.auth.repository.AuthMemberRepository;
import com.study.auth.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final AuthMemberRepository authMemberRepository;
    private final PasswordEncoder passwordEncoder;

    public OAuth2UserDetailsService(AuthMemberRepository authMemberRepository, PasswordEncoder passwordEncoder) {
        this.authMemberRepository = authMemberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        log.info("---------------------------------------");
        log.info("userRequest:" + oAuth2UserRequest);

        String clientName = oAuth2UserRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info(oAuth2UserRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        log.info("=======================================");
        oAuth2User.getAttributes().forEach((key, value) -> {
            log.info(key + " : " + value);  // sub, picture, email, email_verified, EMAIL 등이 출력
        });

        String email = null;

        if(clientName.equals("Google")) {   // 구글을 이용하는 경우
            email = oAuth2User.getAttribute("email");
        }

        AuthMemberEntity authMemberEntity = saveSocialMember(email);

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                authMemberEntity.getEmail(),
                authMemberEntity.getPassword(),
                true,
                authMemberEntity.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        authMemberDTO.setName(authMemberEntity.getName());

        return authMemberDTO;
    }

    private AuthMemberEntity saveSocialMember(String email) {
        // 기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만 함
        Optional<AuthMemberEntity> result = authMemberRepository.findByEmail(email, true);

        if(result.isPresent()) {
            return result.get();
        }

        // 없는 경우에는 추가 패스워드는 1234, 이름은 이메일 주소로 처리
        AuthMemberEntity authMemberEntity = AuthMemberEntity.builder().email(email)
                .name(email)
                .password(passwordEncoder.encode("1234"))
                .fromSocial(true)
                .build();

        // @Builder.Default 없으면 여기서 nullPointException 에러 발생
        authMemberEntity.addMemberRole(AuthMemberRole.USER);

        authMemberRepository.save(authMemberEntity);

        return authMemberEntity;
    }
}
