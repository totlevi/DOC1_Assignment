package via.doc1.doc1_assignment.repository.interfaces;

import via.doc1.doc1_assignment.DTOs.StoryDTO;

import java.util.List;

public interface SQLConnectionInterface {
    void saveStory(StoryDTO dto) throws Exception;
    void deleteStory(String id) throws Exception;
    List<StoryDTO> getStories() throws Exception;
    StoryDTO getStory(String id);
    void editStory(StoryDTO dto) throws Exception;
}
