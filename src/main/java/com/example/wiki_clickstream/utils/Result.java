package com.example.wiki_clickstream.utils;

import lombok.Data;

@Data
public class Result<T> {
    private int status;
    private String message;
    private T data;

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "Success");
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "Success", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> error(int status, String message) {
        return new Result<>(status, message, null);
    }

    public static <T> Result<T> error(int status, String message, T data) {
        return new Result<>(status, message, data);
    }
}

