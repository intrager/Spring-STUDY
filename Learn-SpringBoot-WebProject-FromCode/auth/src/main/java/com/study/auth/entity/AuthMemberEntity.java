package com.study.auth.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AuthMemberEntity extends BaseEntity{
    @Id
    private String email;

    private String password;
    private String name;
    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<AuthMemberRole> roleSet = new HashSet<>();

    public void addMemberRole(AuthMemberRole authMemberRole) {
        roleSet.add(authMemberRole);
    }

}
