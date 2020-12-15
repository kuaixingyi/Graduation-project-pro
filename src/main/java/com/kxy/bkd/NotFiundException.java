package com.kxy.bkd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//异常处理
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFiundException extends RuntimeException{
    public NotFiundException() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public NotFiundException(String message) {
        super(message);
    }

    public NotFiundException(String message, Throwable cause) {
        super(message, cause);
    }
}
