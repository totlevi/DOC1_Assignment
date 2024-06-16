package via.doc1.doc1_assignment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.doc1.doc1_assignment.DTOs.StoryCreationDTO;
import via.doc1.doc1_assignment.DTOs.StoryDTO;
import via.doc1.doc1_assignment.logic.interfaces.StoryLogicInterface;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StoryController {
    private final StoryLogicInterface logic;

    public StoryController(StoryLogicInterface logic) {
        this.logic = logic;
    }

    @PostMapping("/stories")
    public synchronized ResponseEntity<String> postNewStory(@RequestBody StoryCreationDTO dto) {
        try {
            System.out.println("Request received");
            logic.saveStory(dto.getHeader(), dto.getContent());
            return ResponseEntity.ok("Story has been posted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/stories/")
    public synchronized ResponseEntity<List<StoryDTO>> getAllStories() {
        try {
            List<StoryDTO> stories = logic.getStories();
            return ResponseEntity.ok(stories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/stories/{id}")
    public synchronized ResponseEntity<String> editStory(@PathVariable String id,
                                                         @RequestParam(required = false) String header,
                                                         @RequestParam(required = false) String content) {
        try {
            logic.editStory(id, header, content);
            System.out.println(content);
            return ResponseEntity.ok("Story has been edited");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/stories/{id}")
    public synchronized ResponseEntity<String> deleteStory(@PathVariable String id) {
        try {
            logic.deleteStory(id);
            return ResponseEntity.ok("Story has been deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
