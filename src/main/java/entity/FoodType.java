package entity;

import com.mysql.jdbc.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class FoodType extends EntityBase {
    Long foodId;
    String Name = "";
    Integer Protein = 0;
    Integer Fats = 0;
    Integer Carbs = 0;
    Integer Calories = 0;

    public FoodType(String name, String protein, String fats, String calories,
                    String carbs) {
        if (StringUtils.isNullOrEmpty(name) || StringUtils.isNullOrEmpty(protein) || StringUtils.isNullOrEmpty(fats)
                || StringUtils.isNullOrEmpty(calories) || StringUtils.isNullOrEmpty(carbs)) {
            return;
        }
        Name = name;
        Protein = Integer.parseInt(protein);
        Fats = Integer.parseInt(fats);
        Calories = Integer.parseInt(calories);
        Carbs = Integer.parseInt(carbs);
    }

    public boolean isValid() {
        return !Name.isEmpty() && Protein >= 0 && Fats >= 0 && Carbs >= 0 && Calories >= 0;
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
        return "FoodTypeID";
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