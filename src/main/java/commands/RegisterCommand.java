package commands;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand extends CommandBase {
    private final UserService service = new UserService();

    private String redirect(String login, Long id, HttpServletRequest request) {
        request.getSession().setAttribute("userId", id);
        request.getSession().setAttribute("login", login);
        action = CommandAction.Redirect;
        request.getSession().setAttribute("errorMsg", null);
        return WELCOME_REDIRECT;
    }

    private String error(HttpServletRequest request) {
        var error = "Invalid data or user already exist!";
        request.getSession().setAttribute("errorMsg", error);
        return REGISTER_PAGE;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login == null) {
            request.getSession().setAttribute("errorMsg", null);
            return REGISTER_PAGE;
        }
        if (service.isUserExist(login)) {
            System.out.println("user exist");
            return REGISTER_PAGE;
        }
        String password = request.getParameter("password");
        String weight = request.getParameter("weight");
        String height = request.getParameter("height");

        var id = service.addUser(login, password, weight, height);

        return (id == null) ? error(request) : redirect(login, id, request);
    }
}