package commands;

import service.UserService;
import javax.servlet.http.HttpServletRequest;

public class LoginCommand extends CommandBase {
    private final UserService service = new UserService();

    private String redirect(String login, Long id, HttpServletRequest request) {
        request.getSession().setAttribute("userId", id);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("errorMsg", null);
        action = CommandAction.Redirect;
        return WELCOME_REDIRECT;
    }
    private String error(HttpServletRequest request) {
        var error = "Invalid login or password!";
        request.getSession().setAttribute("errorMsg", error);
        return LOGIN_PAGE;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login == null) {
            request.getSession().setAttribute("errorMsg", null);
            return LOGIN_PAGE;
        }
        String password = request.getParameter("password");
        var id = service.findUser(login, password);

        return id == null ? error(request) : redirect(login, id, request);
    }
}
