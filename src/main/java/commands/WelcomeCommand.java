package commands;

import javax.servlet.http.HttpServletRequest;

public class WelcomeCommand extends CommandBase{
    @Override
    public String execute(HttpServletRequest request) {
        return WELCOME_PAGE;
    }
}
