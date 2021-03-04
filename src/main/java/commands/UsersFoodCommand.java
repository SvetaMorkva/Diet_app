package commands;

import service.FoodService;
import javax.servlet.http.HttpServletRequest;

public class UsersFoodCommand extends CommandBase {
    private final FoodService service = new FoodService();

    @Override
    public String execute(HttpServletRequest request) {
        var id = (Long) request.getSession().getAttribute("userId");
        request.getSession().setAttribute("userFoods", service.getUserFoodType(id));
        return USER_FOODTYPES_PAGE;
    }
}