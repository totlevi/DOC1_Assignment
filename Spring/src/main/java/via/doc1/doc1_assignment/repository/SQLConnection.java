package via.doc1.doc1_assignment.repository;

import via.doc1.doc1_assignment.DTOs.StoryDTO;
import via.doc1.doc1_assignment.repository.interfaces.SQLConnectionInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLConnection implements SQLConnectionInterface {
    private static SQLConnection instance;

    protected SQLConnection() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static SQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new SQLConnection();
        }
        return instance;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=doc1",
                "postgres", "password");
    }
    @Override
    public void saveStory(StoryDTO dto) throws Exception {
        try(Connection connection = getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO story(id, header, content, date)"
                                                                                    + "VALUES (?,?,?,?)")){
                statement.setString(1,dto.getId());
                statement.setString(2,dto.getHeader());
                statement.setString(3,dto.getContent());
                statement.setTimestamp(4,dto.getDate());
                statement.executeUpdate();
            }catch (SQLException e)
            {
                throw new Exception(e.getMessage());
            }
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteStory(String id) throws Exception {
        try(Connection connection = getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM story WHERE id=?"))
            {
                statement.setString(1,id);
                statement.executeUpdate();
            }catch (Exception e)
            {
                throw new Exception(e.getMessage());
            }
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<StoryDTO> getStories() throws Exception {
        List<StoryDTO> stories = new ArrayList<>();
        try(Connection connection = getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM story")){
                ResultSet result = statement.executeQuery();
                while (result.next())
                {
                    String id = result.getString("id");
                    String h = result.getString("header");
                    String c = result.getString("content");
                    Timestamp t = result.getTimestamp("date");
                    StoryDTO story = new StoryDTO(id,h,c,t);
                    stories.add(story);
                }
            }catch (SQLException e)
            {
                throw new Exception(e.getMessage());
            }
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
        return stories;
    }

    @Override
    public StoryDTO getStory(String id) {
        StoryDTO story = null;
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM story WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        String i = result.getString("id");
                        String h = result.getString("header");
                        String c = result.getString("content");
                        Timestamp t = result.getTimestamp("date");
                        story = new StoryDTO(i, h, c, t);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error querying the story with id " + id, e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the connection", e);
        }
        return story;
    }

    @Override
    public void editStory(StoryDTO dto) throws Exception {
        try(Connection connection = getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("UPDATE story SET header=?, content=?, date=?")){
                statement.setString(1,dto.getHeader());
                statement.setString(2,dto.getContent());
                statement.setTimestamp(3,dto.getDate());
                statement.executeUpdate();
            }catch (SQLException e)
            {
                throw new Exception(e.getMessage());
            }
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
}
