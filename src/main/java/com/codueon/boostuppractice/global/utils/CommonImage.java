package com.codueon.boostuppractice.global.utils;

/**
 * 이미지 필드 상속용 클래스
 * @author mozzi327
 */
public class CommonImage {
    private String originFileName;
    private String fileName;
    private String filePath;
    private Long fileSize;

    public CommonImage(String originFileName, String fileName, String filePath, Long fileSize) {
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
