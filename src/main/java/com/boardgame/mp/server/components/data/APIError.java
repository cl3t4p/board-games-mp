package com.boardgame.mp.server.components.data;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class APIError {
    HttpStatus status;
    LocalDateTime timestamp;
    String message;
    String debugMessage;
    List<String> description = new ArrayList<>();

    public APIError(HttpStatus status, String message, Exception exception,String... description ) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.debugMessage = exception.getMessage();
        this.description.addAll(Arrays.asList(description));
    }


}
