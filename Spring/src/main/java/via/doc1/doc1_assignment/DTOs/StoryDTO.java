package via.doc1.doc1_assignment.DTOs;

import java.sql.Timestamp;

public class StoryDTO {
    private String id;
    private String header;
    private String content;
    private Timestamp date;

    public StoryDTO(String id, String header, String content, Timestamp date) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
