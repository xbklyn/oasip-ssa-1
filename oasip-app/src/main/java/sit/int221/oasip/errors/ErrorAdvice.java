package sit.int221.oasip.errors;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleFileExceed(MaxUploadSizeExceededException ex, HttpServletRequest request){
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File Exceeded.");
        return ResponseEntity.status(400).body("File size exceeded.");
    }

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

    //Manually create ResponseEntity returning 400 Bad Request
    public ApiError getResponseEntity(String field , String errorMes , HttpServletRequest req){
        ApiError error = new ApiError(400, "Validation Failed", req.getServletPath());

        Map<String , String> details = new HashMap<>();
        details.put( field, errorMes);

        error.setDetails(details);
        return error;
    }

    public ApiError getAllErrors(Map<String,String> validations , HttpServletRequest req){
        ApiError errors = new ApiError(400, "Validation Failed", req.getServletPath());
        errors.setDetails(validations);
        return errors;
    }
}
