package sit.int221.oasip.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PutCategoryDTO {

    @NotNull
    private String eventCategoryName;

    @NotNull
    private Integer eventCategoryDuration;

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
}
