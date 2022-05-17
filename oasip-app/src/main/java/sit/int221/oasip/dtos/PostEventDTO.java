package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter
public class PostEventDTO {

    @NotNull
    @Size(min = 1 , max = 100 , message = "Size must be between 1 and 100")
    private String bookingName;

    @NotNull
    @Email
    @Size(min = 1 , max = 100 , message = "Size must be between 1 and 100")
    private String bookingEmail;

    @NotNull
    private Date eventStartTime;

    @NotNull
    private Integer categoryId;

    @Size(min = 0, max = 500 , message = "Size must be less than 500")
    private String eventNotes;
}
