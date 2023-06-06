package com.codueon.boostuppractice.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {
    @Getter
    @NoArgsConstructor
    public static class Join {
        private String email;
        private String password;
        private String nickname;

        @Builder
        public Join(String email, String password, String nickname) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
        }
    }
}
