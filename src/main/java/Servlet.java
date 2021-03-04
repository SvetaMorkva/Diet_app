import javax.servlet.http.*;

import commands.CommandHelper;
import lombok.SneakyThrows;

public class Servlet extends HttpServlet {
    private CommandHelper commandHelper = null;

    @Override
    public void init() {
        commandHelper = CommandHelper.createInstance();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @SneakyThrows
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (commandHelper == null) {
            System.out.println("ERROR: Couldn't create command helper");
            return;
        }
        var commandType = commandHelper.getCommandType(request);
        if (commandType == null) {
            System.out.println("ERROR: Invalid command received");
            return;
        }
        var page = commandType.execute(request);
        var dispatcher = request.getRequestDispatcher(page);
        switch (commandType.getActionType()) {
            case Forward -> dispatcher.forward(request, response);
            case Include -> dispatcher.include(request, response);
            case Redirect -> response.sendRedirect(page);
        }
    }
}
