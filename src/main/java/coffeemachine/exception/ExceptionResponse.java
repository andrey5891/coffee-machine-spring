package coffeemachine.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ExceptionResponse {
    private final Date timestamp;
    private final String message;
    private final String detail;
}