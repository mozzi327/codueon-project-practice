package com.codueon.boostuppractice.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 도메인 Dto 클래스
 * @author mozzi327
 */
public class MemberDto {
    /**
     * 일반 회원가입 이너 클래스
     * @author mozzi327
     */
    @Getter
    public static class Join {
        private final String email;
        private final String password;
        private final String nickname;

        @Builder
        public Join(String email, String password, String nickname) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
        }
    }
}
