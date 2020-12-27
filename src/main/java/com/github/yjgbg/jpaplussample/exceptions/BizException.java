package com.github.yjgbg.jpaplussample.exceptions;

import lombok.Getter;

import java.util.function.Supplier;

public class BizException extends RuntimeException implements Supplier<BizException> {
    @Getter
    private final int status;
    public BizException(int status,String message) {
        super(message,null,false,false);
        this.status = status;
    }

    @Override
    public BizException get() {
        return this;
    }
}
