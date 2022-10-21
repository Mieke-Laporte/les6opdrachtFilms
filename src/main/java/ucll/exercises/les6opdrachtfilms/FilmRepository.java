package ucll.exercises.les6opdrachtfilms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {
    private final String DB_URL = "jdbc:mysql://localhost/dbfilm";
    private final String USERNAME = "root";
    private final String PASSWORD = "Mieke";

    public boolean rCreateMovie(String title, int year, int rating, String description) {
        String updatePositionSql = "INSERT INTO films VALUES (?, ?, ?, ?)";
        Film film = new Film();
        film.setTitle(title);
        film.setYear(year);
        film.setRating(rating);
        film.setDescription(description);
        try (Connection myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = myConnection.prepareStatement(updatePositionSql)) {
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setInt(2, film.getYear());
            preparedStatement.setInt(3, film.getRating());
            preparedStatement.setString(4, film.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Film> rGetAllMovies() {
        List<Film> films = new ArrayList<>();
        try (Connection myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement myStatement = myConnection.createStatement(); ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM films")) {
            while (myResultSet.next()) {
                Film film = new Film();
                film.setTitle(myResultSet.getString("Title"));
                film.setYear(myResultSet.getInt("Year"));
                film.setRating(myResultSet.getInt("Rating"));
                film.setDescription(myResultSet.getString("Description"));
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    public Film rGetByTitle(String title) {
        Film film = new Film();
        String query = "SELECT * FROM films WHERE Title = ?";
        try (Connection myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = myConnection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            ResultSet myResultSet = preparedStatement.executeQuery();
            myResultSet.next();
            film.setTitle(myResultSet.getString("Title"));
            film.setYear(myResultSet.getInt("Year"));
            film.setRating(myResultSet.getInt("Rating"));
            film.setDescription(myResultSet.getString("Description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    public boolean rUpdateMovie(String title, int year, int rating, String description) {
        String updatePositionSQL = "UPDATE films SET Year = ?, Rating = ?, Description = ? WHERE Title = ?";
        try (Connection myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = myConnection.prepareStatement(updatePositionSQL)) {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, rating);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
