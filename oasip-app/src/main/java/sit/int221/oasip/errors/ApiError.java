package sit.int221.oasip.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiError {
    private Date timestamp;
    private int status;
    private String message;
    private Map<String, String> details;
    private String path;

    public ApiError(int status, String message, String path) {
        this.timestamp = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
