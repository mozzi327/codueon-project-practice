package com.codueon.boostuppractice.global.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * Controller 단위의 Global Exception 처리를 위한 클래스
 *
 * @author mozzi327
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalAdvice {

    /**
     * Field 검증 관련 오류 메시지 핸들 메서드
     *
     * @param e MethodArgumentNotValidException
     * @return ErrorResponse
     * @autor mozzi327
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.of(e.getBindingResult());
    }

    /**
     * 제약 조건 위반 관련 오류 메시지 핸들 메서드
     *
     * @param e ConstraintViolationException
     * @return ErrorResponse
     * @author mozzi327
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        return ErrorResponse.of(e.getConstraintViolations());
    }

    /**
     * Business 로직 관련 오류 메시지 핸들 메서드
     *
     * @param e BusinessLogicException
     * @return ErrorResponse
     * @author mozzi327
     */
    @ExceptionHandler
    public ErrorResponse handleBusinessLogicException(BusinessLogicException e) {
        return ErrorResponse.of(e.getExceptionCode());
    }

    /**
     * 메서드 문법 오류 메시지 핸들 메서드
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return ErrorResponse
     * @author mozzi327
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED, "메서드 문법이 틀렸습니다. 문법을 지켜주세요.");
    }

    /**
     * Json 문법 오류 메시지 핸들 메서드
     *
     * @param e HttpMessageNotReadableException
     * @return ErrorResponse
     * @author mozzi327
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "정확한 제이슨 요청을 부탁드립니다.");
    }

    /**
     * 파라미터 오류 메시지 핸들 메서드
     *
     * @param e MissingServletRequestParameterException
     * @return ErrorResponse
     * @author mozzi327
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "파라미터가 유효하지 않습니다.");
    }

    /**
     * 서버 오류 메시지 핸들 메서드(NullPointerException)
     *
     * @param e NullPointerException
     * @return ErrorResponse
     * @author mozzi327
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleNullPointException(NullPointerException e) {
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "500 Error");
    }
}
