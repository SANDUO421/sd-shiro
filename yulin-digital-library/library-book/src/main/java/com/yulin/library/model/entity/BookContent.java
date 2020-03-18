package com.yulin.library.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.yulin.library.mongodb.model.entity.BaseEntity;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.entity.BaseBookContent;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BookContent extends BaseEntity {

    public interface SimpleView extends ApiResponse.BaseJsonView {}
    public interface ContentView extends SimpleView {}

    @JsonView(SimpleView.class)
    private String bookId;
    @JsonView(SimpleView.class)
    private String title;
    @JsonView(ContentView.class)
    private String content;
    @JsonView(SimpleView.class)
    private String beforeId;
    @JsonView(SimpleView.class)
    private String nextId;
    @JsonView(SimpleView.class)
    private Long sortNumber;
    @JsonView(SimpleView.class)
    private String[] contentPath;

    public void setBookContent(BaseBookContent baseBookContent){
        this.title=baseBookContent.getTitle();
        this.content=baseBookContent.getContent();
        this.sortNumber=baseBookContent.getSortNumber();
    }

}
