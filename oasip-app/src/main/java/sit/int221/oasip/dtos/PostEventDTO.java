package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class PostEventDTO {
    @NotNull
    private String bookingName;
    @NotNull
    @Email
    private String bookingEmail;
    @NotNull
    private Date eventStartTime;
    @NotNull
    private Integer categoryId;
    private String eventNotes;
}
