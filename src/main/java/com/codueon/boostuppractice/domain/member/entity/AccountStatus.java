package com.codueon.boostuppractice.domain.member.entity;

import com.codueon.boostuppractice.global.exception.BusinessLogicException;
import com.codueon.boostuppractice.global.exception.ExceptionCode;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum AccountStatus {
    COMMON_MEMBER("common"),
    KAKAO_MEMBER("kakao"),
    GOOGLE_MEMBER("google"),
    NAVER_MEMBER("naver");

    private final String provider;

    AccountStatus(String provider) {
        this.provider = provider;
    }

    private static final Map<String, AccountStatus> accountMap =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(AccountStatus::getProvider, Function.identity())));

    public static AccountStatus findByProvider(String provider) {
        return Optional.ofNullable(accountMap.get(provider))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVALID_OAUTH2));
    }
}
