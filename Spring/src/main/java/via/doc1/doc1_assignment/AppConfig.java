package via.doc1.doc1_assignment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import via.doc1.doc1_assignment.logic.StoryLogic;
import via.doc1.doc1_assignment.logic.interfaces.StoryLogicInterface;
import via.doc1.doc1_assignment.repository.SQLConnection;
import via.doc1.doc1_assignment.repository.interfaces.SQLConnectionInterface;

import java.sql.SQLException;

@Configuration
public class AppConfig {
    @Bean
    public SQLConnectionInterface sqlConnectionInterface() throws SQLException {
        return SQLConnection.getInstance();
    }

    @Bean
    public StoryLogicInterface storyLogicInterface() throws SQLException {
        return new StoryLogic(sqlConnectionInterface());
    }
}
