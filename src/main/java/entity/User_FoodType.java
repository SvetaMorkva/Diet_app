package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class User_FoodType extends EntityBase {
    Long User;
    Long FoodType;
    Long Id;

    public User_FoodType(Long user, Long foodType) {
        User = user;
        FoodType = foodType;
    }

    @Override
    public String getTableName() {
        return "User_FoodType";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (User, FoodType) VALUES(?,?)";
    }

    @Override
    public void parse(ResultSet resultSet) throws SQLException {
        Id = resultSet.getLong("User_FoodTypeId");
        User = resultSet.getLong("User");
        FoodType = resultSet.getLong("FoodType");
    }

    @Override
    public void serialize(PreparedStatement statement, boolean withId) throws SQLException {
        statement.setLong(1, User);
        statement.setLong(2, FoodType);
    }
}