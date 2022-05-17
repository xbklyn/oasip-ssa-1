package sit.int221.oasip.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethidArgumentNotValidException(MethodArgumentNotValidException ex , HttpServletRequest req){
        ApiError error = new ApiError(400, "Validation Failed", req.getServletPath());
        BindingResult bindingResult = ex.getBindingResult();

        Map<String , String> details = new HashMap<>();
        bindingResult.getFieldErrors().forEach(err ->
                details.put(err.getField(), err.getDefaultMessage()));
        error.setDetails(details);
        return error;
    }
}
