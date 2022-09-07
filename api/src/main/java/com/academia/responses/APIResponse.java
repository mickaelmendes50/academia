package com.academia.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class APIResponse<T> {
    @Getter
    @Setter
    private T data;

    @Getter
    @Setter
    private List<String> errors;

    public APIResponse() {
        this.errors = new ArrayList<String>();
    }
}
