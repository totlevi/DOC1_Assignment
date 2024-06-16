package via.doc1.doc1_assignment.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StoryCreationDTO {
    String header;
    String content;

    @JsonCreator
    public StoryCreationDTO(
            @JsonProperty("header") String header,
            @JsonProperty("content") String content) {
        this.header = header;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
