package com.github.yjgbg.jpaplussample.controller.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@JsonInclude(content = JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class R<A> {
    int code;
    String message;
    A data;
    public static R<Void> code(int code) {
        return new R<>(code,null,null);
    }

    public R<Void> withMessage(String message) {
        return new R<>(code,message,null);
    }

    public <B> R<B> withData(B data) {
        return new R<>(code,null,data);
    }
}
