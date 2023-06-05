package com.codueon.boostuppractice.domain.member.entity;

import com.codueon.boostuppractice.global.utils.CommonImage;
import lombok.Builder;
import lombok.Getter;

/**
 * 사용자 이미지 Entity 클래스
 *
 * @author mozzi327
 */
@Getter
public class MemberImage extends CommonImage {

    @Builder
    public MemberImage(String originFileName, String fileName, String filePath, Long fileSize) {
        super(originFileName, fileName, filePath, fileSize);
    }
}
