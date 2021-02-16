package entity;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityBase implements Serializable{
    public EntityBase() {}

    public String getTableName() { return ""; }
    public String getInsertQuery() { return ""; }
    public String getUpdateQuery() { return ""; }
    public String getIdName() { return ""; }
    public Long getId() { return 0L; }

    public void parse(ResultSet resultSet) throws SQLException {}
    public void serialize(PreparedStatement statement,
                          boolean withId) throws SQLException {}
}
