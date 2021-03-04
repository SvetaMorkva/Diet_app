package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class CommandHelper {
    private static CommandHelper instance = null;
    private CommandHelper() {}
    private final HashMap<String, CommandBase> commandType = initMap();

    private HashMap<String, CommandBase> initMap() {
        HashMap<String, CommandBase> map = new HashMap<>();
        map.put("/", new MainPageCommand());
        map.put("/main", new MainPageCommand());
        map.put("/register", new RegisterCommand());
        map.put("/login", new LoginCommand());
        map.put("/welcome", new WelcomeCommand());
        map.put("/addFoodType", new FoodTypeCommand());
        map.put("/userFoodTypes", new UsersFoodCommand());
        map.put("/userFoodTypes_removeFood", new RemoveUserFoodCommand());
        map.put("/allFoodTypes", new AllFoodCommand());
        return map;
    }

    public static CommandHelper createInstance() {
        if (instance == null) {
            instance = new CommandHelper();
        }
        return instance;
    }

    public CommandBase getCommandType(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return commandType.get("/main");
        }
        String command = request.getRequestURI();
        String action = request.getParameter("actionType");
        if (action != null) {
            command += "_" + action;
        }
        if (!commandType.containsKey(command)) {
            System.out.println("ERROR: invalid page requested");
            return null;
        }
        return commandType.get(command);
    }
}
