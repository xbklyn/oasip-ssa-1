package sit.int221.oasip.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PutCategoryDTO {

    @NotNull
    private Integer categoryId;

    @NotNull
    private String eventCategoryName;

    @NotNull
    private Integer eventCategoryDuration;

    private String eventCategoryDescription;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

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
