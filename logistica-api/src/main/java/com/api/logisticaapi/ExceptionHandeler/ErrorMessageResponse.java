package com.api.logisticaapi.ExceptionHandeler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageResponse {

    private Integer HttpStatus;
    private String message;
    private OffsetDateTime dateTime;
    private List<Argument> arguments;

    @AllArgsConstructor
    @Getter
    public static class Argument {
        private String field;
        private String message;
    }
}
