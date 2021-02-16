package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class FoodType extends EntityBase {
    Long foodId;
    String Name;
    Integer Protein = 0;
    Integer Fats = 0;
    Integer Carbs = 0;
    Integer Calories = 0;

    public FoodType(Long id, String name) {
        foodId = id;
        Name = name;
    }

    @Override
    public String getTableName() {
        return "foodType";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() +
                " (Name, Protein, Fats, Carbs, Calories) VALUES(?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET Name=?, Protein=?, Fats=?," +
                "Carbs=?, Calories=? WHERE " + getIdName() + "=?";
    }

    @Override
    public String getIdName() {
        return "foodId";
    }

    @Override
    public void parse(ResultSet resultSet) throws SQLException {
        foodId = resultSet.getLong(getIdName());
        Name = resultSet.getString("Name");
        Protein = resultSet.getInt("Protein");
        Fats = resultSet.getInt("Fats");
        Carbs = resultSet.getInt("Carbs");
        Calories = resultSet.getInt("Calories");
    }

    @Override
    public void serialize(PreparedStatement statement, boolean withId) throws SQLException {
        statement.setString(1, Name);
        statement.setInt(2, Protein);
        statement.setInt(3, Fats);
        statement.setInt(4, Carbs);
        statement.setInt(5, Calories);
        if (withId) {
            statement.setLong(6, foodId);
        }
    }
}