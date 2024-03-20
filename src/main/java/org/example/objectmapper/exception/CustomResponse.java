package org.example.objectmapper.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomResponse {

    private String message;

    private LocalDateTime localDateTime;
}
