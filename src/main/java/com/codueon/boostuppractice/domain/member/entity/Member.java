package com.codueon.boostuppractice.domain.member.entity;

import com.codueon.boostuppractice.global.utils.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 회원 정보 Entity 클래스
 *
 * @author mozzi327
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditable {
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Setter
    private String password;

    @Setter
    private String nickname;

    @ElementCollection
    private List<String> roles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    @Setter
    @Embedded
    private MemberImage memberImage;

    @Builder
    public Member(Long id, String email, String password,
                  String nickname, List<String> roles, AccountStatus accountStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.roles = roles;
        this.accountStatus = accountStatus;
    }
}
