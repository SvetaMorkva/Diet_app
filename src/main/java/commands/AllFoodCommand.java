package commands;

import service.FoodService;
import javax.servlet.http.HttpServletRequest;

public class AllFoodCommand extends CommandBase {
    private final FoodService service = new FoodService();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("allFoods", service.getAll());
        return ALL_FOODTYPES_PAGE;
    }
}
