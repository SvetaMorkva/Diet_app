package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class User extends EntityBase {
    Long UserId;
    String Login;
    String PasswordHash;
    Integer Weight = 0;
    Integer Height = 0;
    Integer RightsId = 1;

    public User(String login, String pass) {
        Login = login;
        PasswordHash = pass;
    }

    public User(String login, String pass, Integer weight, Integer height) {
        Login = login;
        PasswordHash = pass;
        Weight = weight;
        Height = height;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() +
                " (LoginName, PasswordHash, Weight, Height, RightsID) VALUES(?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET LoginName=?, PasswordHash=?, Weight=?," +
                " Height=?, RightsID=? WHERE " + getIdName() + "=?";
    }

    @Override
    public String getIdName() {
        return "UserID";
    }

    @Override
    public Long getId() {
        return UserId;
    }

    @Override
    public void parse(ResultSet resultSet) throws SQLException {
        UserId = resultSet.getLong(getIdName());
        Login = resultSet.getString("LoginName");
        PasswordHash = resultSet.getString("PasswordHash");
        Weight = resultSet.getInt("Weight");
        Height = resultSet.getInt("Height");
        RightsId = resultSet.getInt("RightsID");
    }

    @Override
    public void serialize(PreparedStatement statement, boolean withId) throws SQLException {
        statement.setString(1, Login);
        statement.setString(2, PasswordHash);
        statement.setInt(3, Weight);
        statement.setInt(4, Height);
        statement.setInt(5, RightsId);
        if (withId) {
            statement.setLong(6, UserId);
        }
    }
}