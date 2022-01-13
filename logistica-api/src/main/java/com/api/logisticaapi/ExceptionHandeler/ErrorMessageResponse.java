package com.api.logisticaapi.ExceptionHandeler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {

    private Integer HttpStatus;
    private String message;
    private LocalDateTime dateTime;
    private List<Argument> arguments;

    @AllArgsConstructor
    @Getter
    public static class Argument {
        private String field;
        private String message;
    }
}
