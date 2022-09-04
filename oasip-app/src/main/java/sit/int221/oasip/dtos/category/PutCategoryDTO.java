package sit.int221.oasip.dtos.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PutCategoryDTO {

    @NotBlank
    @NotNull
    @Size(min = 1 , max = 100 , message = "Size must be between 1 and 100")
    private String eventCategoryName;

    @NotNull
    private Integer eventCategoryDuration;

    @NotNull
    private String eventCategoryDescription;

    public String getEventCategoryName() {
        return eventCategoryName;
    }

    public void setEventCategoryName(String eventCategoryName) {
        this.eventCategoryName = eventCategoryName;
    }

    public Integer getEventCategoryDuration() {
        return eventCategoryDuration;
    }

    public void setEventCategoryDuration(Integer eventCategoryDuration) {
        this.eventCategoryDuration = eventCategoryDuration;
    }

    public String getEventCategoryDescription() {
        return eventCategoryDescription;
    }

    public void setEventCategoryDescription(String eventCategoryDescription) {
        this.eventCategoryDescription = eventCategoryDescription;
    }
}
