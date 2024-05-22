package via.doc1.doc1_assignment.logic;

import via.doc1.doc1_assignment.DTOs.StoryDTO;
import via.doc1.doc1_assignment.logic.interfaces.StoryLogicInterface;
import via.doc1.doc1_assignment.repository.interfaces.SQLConnectionInterface;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class StoryLogic implements StoryLogicInterface {
    private SQLConnectionInterface connection;

    public StoryLogic(SQLConnectionInterface connection) {
        this.connection = connection;
    }

    @Override
    public void saveStory(String header, String content) throws Exception {
        System.out.println("Saving in logic");
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        String id = "STORY-"+ UUID.randomUUID().toString();
        StoryDTO dto = new StoryDTO(id,header,content,timestamp);
        connection.saveStory(dto);
    }

    @Override
    public void deleteStory(String id) throws Exception{
        StoryDTO story = connection.getStory(id);
        if(story==null)
        {
            throw new Exception("No such story with id "+id);
        }
        connection.deleteStory(id);
    }

    @Override
    public synchronized List<StoryDTO> getStories() throws Exception {
        List<StoryDTO>stories = connection.getStories();
        if(stories.isEmpty())
        {
            throw new Exception("No stories stored in the database");
        }
        return stories;
    }

    @Override
    public void editStory(String id, String header, String content) throws Exception {
        StoryDTO story = connection.getStory(id);
        if(story==null)
        {
            throw new Exception("No such story with id "+id);
        }

        StoryDTO dto=null;
        if(header==null && content==null) {
            throw new Exception("No changes to edit");
        }
        if(header==null && content!=null) {
            dto = new StoryDTO(story.getId(),story.getHeader(),content,Timestamp.valueOf(LocalDateTime.now()));
        }
        if(header!=null && content!=null) {
            dto = new StoryDTO(story.getId(),header,content,Timestamp.valueOf(LocalDateTime.now()));
        }
        connection.editStory(dto);
    }
}
