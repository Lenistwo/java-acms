package pl.xisp.shared.exception;

import lombok.Getter;

public class CustomException extends RuntimeException {
    @Getter private final String code;

    public CustomException(String code) {
        this.code = code;
    }
}
