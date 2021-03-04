package commands;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface ICommand {
    String execute(HttpServletRequest request) throws SQLException;
}
