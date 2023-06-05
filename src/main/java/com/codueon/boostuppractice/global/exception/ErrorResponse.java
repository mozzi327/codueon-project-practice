package com.codueon.boostuppractice.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Custom ErrorResponse 클래스
 *
 * @author mozzi327
 */
@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private List<Field> fieldErrors;
    private List<ConstraintViolation> violationErrors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(List<Field> fieldErrors, List<ConstraintViolation> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    /**
     * ErrorResponse 생성 of 메서드(FieldError)
     *
     * @param bindingResult 검증 오류 관련 내용을 보관하는 인터페이스
     * @return ErrorResponse
     * @author mozzi327
     */
    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(Field.of(bindingResult), null);
    }

    /**
     * ErrorResponse 생성 of 메서드(ConstraintViolationError)
     *
     * @param violations 제약 조건을 위반한 오류 내용을 보관하는 인터페이스
     * @return ErrorResponse
     * @author mozzi327
     */
    public static ErrorResponse of(Set<javax.validation.ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolation.of(violations));
    }

    /**
     * ErrorResponse 생성 of 메서드(ExceptionCode)
     *
     * @param exceptionCode 직접 정의한 ExceptionCode Enum 클래스
     * @return ErrorResponse
     * @author mozzi327
     */
    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    /**
     * ErrorResponse 생성 of 메서드(HttpException - Invalid Method, Json, Parameter... etc)
     *
     * @param httpStatus Http 상태 코드
     * @param message    Http  상태 메시지
     * @return ErrorResponse
     * @author mozzi327
     */
    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus.value(), message);
    }

    @Getter
    private static class Field {
        private final String field;
        private final Object rejectedValue;
        private final String reason;

        public Field(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<Field> of(BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(err -> new Field(
                            err.getField(),
                            err.getRejectedValue() == null ? "" : err.getRejectedValue(),
                            err.getDefaultMessage()
                    )).collect(Collectors.toList());
        }
    }

    @Getter
    private static class ConstraintViolation {
        private final String constraintViolation;
        private final String rejectedValue;
        private final String reason;

        public ConstraintViolation(String constraintViolation, String rejectedValue, String reason) {
            this.constraintViolation = constraintViolation;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolation> of(Set<javax.validation.ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(err -> new ConstraintViolation(
                            err.getPropertyPath().toString(),
                            err.getInvalidValue().toString(),
                            err.getMessage()
                    )).collect(Collectors.toList());
        }
    }
}
