package com.isoft.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    private String resultMessage;

    @Override
    public String toString() {
        return resultMessage;
    }
}

