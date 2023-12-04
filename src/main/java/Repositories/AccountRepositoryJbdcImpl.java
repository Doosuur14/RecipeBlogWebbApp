package Repositories;

import Models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountRepositoryJbdcImpl implements AccountRepository {
    private final Connection connection;


    private static final String SQL_INSERT_USER =
            "INSERT INTO usersaccounts (firstname, lastname, username, password, role) " +
                    "VALUES (?, ?, ?, ?, " +
                    "   CASE " +
                    "      WHEN (SELECT COUNT(*) FROM usersaccounts) = 0 THEN 'admin' " +
                    "      ELSE 'user' " +
                    "   END " +
                    ")";


    private static final String SQL_UPDATE_PROFILE =
            "UPDATE user_profile SET bio = ?, favfoodcategory = ?, profilephoto = ? WHERE username = ?";


//    private static final String SQL_SAVE_PROFILE =
//         "INSERT INTO users_profiles(userProfilePhoto, bio, favFoodCategory)" + "VALUES(?,?,?)";



    public AccountRepositoryJbdcImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(User user) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
        System.out.println("Successful!");


    }


    @Override
    public boolean login(String username, String password, User user) throws SQLException {
        String sql = "SELECT password FROM usersaccounts WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("password");
                    if (BCrypt.checkpw(password, storedHashedPassword)) {
                        setUserDetails(username, user);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void setUserDetails(String username, User user) throws SQLException {
        String userDetailsSql = "SELECT firstname, lastname FROM usersaccounts WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(userDetailsSql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user.setFirstName(resultSet.getString("firstname"));
                    user.setLastName(resultSet.getString("lastname"));
                }
            }
        }
    }


    @Override
    public boolean findUUID(UUID uuid) throws SQLException {
        String sql = "SELECT COUNT(*) FROM uuid_user WHERE uuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, uuid);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UUID addUUID(String username, User user) throws SQLException {
        String sqlUser = "SELECT id FROM usersaccounts WHERE username = ?";
        String insertSqlUuid = "INSERT INTO uuid_user(id, uuid) VALUES(?, ?)";

        UUID uuid = UUID.randomUUID();

        PreparedStatement preparedStatement1 = connection.prepareStatement(sqlUser);
        PreparedStatement preparedStatement2 = connection.prepareStatement(insertSqlUuid);

        preparedStatement1.setString(1, user.getUsername());
        int id = 0;
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()){
            id = resultSet.getInt("id");
        }

        preparedStatement2.setInt(1,id);
        preparedStatement2.setObject(2, uuid);
        preparedStatement2.executeUpdate();
        return uuid;
    }

    @Override

    public void updateProfile(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PROFILE)) {
            preparedStatement.setString(1, user.getUserProfile().getBio());
            preparedStatement.setString(2, user.getUserProfile().getFavFoodCategory());
            preparedStatement.setString(3, user.getUserProfile().getFileInfo().getStorageFileName());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
