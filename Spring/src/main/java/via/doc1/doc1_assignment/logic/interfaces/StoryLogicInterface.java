package via.doc1.doc1_assignment.logic.interfaces;

import via.doc1.doc1_assignment.DTOs.StoryDTO;

import java.util.List;

public interface StoryLogicInterface {
    void saveStory(String header, String content) throws Exception;
    void deleteStory(String id) throws Exception;
    List<StoryDTO> getStories() throws Exception;
    void editStory(String id, String header, String content) throws Exception;
}
