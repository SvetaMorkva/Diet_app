package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandBase implements ICommand {
    protected CommandAction action = CommandAction.Forward;
    protected String MAIN_PAGE = "main.jsp";
    protected String LOGIN_PAGE = "login.jsp";
    protected String REGISTER_PAGE = "register.jsp";
    protected String WELCOME_PAGE = "welcome.jsp";
    protected String FOODTYPE_PAGE = "foodType.jsp";
    protected String USER_FOODTYPES_PAGE = "usersFoodTypes.jsp";
    protected String ALL_FOODTYPES_PAGE = "allFoodTypes.jsp";
    protected String WELCOME_REDIRECT = "/welcome";
    protected String USERFOOD_REDIRECT = "/userFoodTypes";

    public CommandAction getActionType() { return action; }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("errorMsg", null);
            session.invalidate();
        }
        return MAIN_PAGE;
    }
}
