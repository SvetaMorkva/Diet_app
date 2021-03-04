package commands;

import entity.FoodType;
import service.FoodService;

import javax.servlet.http.HttpServletRequest;

public class RemoveUserFoodCommand extends CommandBase {
    private final FoodService service = new FoodService();

    private String updatePage(HttpServletRequest request) {
        return new UsersFoodCommand().execute(request);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String foodId = request.getParameter("foodId");
        var userId = (Long) request.getSession().getAttribute("userId");

        return service.removeUserFoodType(userId, foodId) ? updatePage(request) : USER_FOODTYPES_PAGE;
    }
}
