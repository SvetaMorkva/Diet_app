package commands;

import entity.FoodType;
import service.FoodService;

import javax.servlet.http.HttpServletRequest;

public class FoodTypeCommand extends CommandBase {
    private final FoodService service = new FoodService();
    private FoodType foodType;

    private String createFoodType(HttpServletRequest request) {
        var login = (String) request.getSession().getAttribute("login");
        service.createFoodType(foodType, login);
        action = CommandAction.Redirect;
        return USERFOOD_REDIRECT;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String protein = request.getParameter("protein");
        String fats = request.getParameter("fats");
        String carbs = request.getParameter("carbs");
        String calories = request.getParameter("calories");

        foodType = new FoodType(name, protein, fats, carbs, calories);
        System.out.println("");

        return foodType.isValid() ? createFoodType(request) : FOODTYPE_PAGE;
    }
}
