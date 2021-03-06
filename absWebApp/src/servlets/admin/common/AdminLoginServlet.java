package servlets.admin.common;

import engine.Engine;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ServletUtils;
import utils.SessionUtils;

import java.io.IOException;

import static constants.Constants.IS_ADMIN_LOGGED_IN;
import static constants.Constants.USERNAME;
import static constants.Constants.ADMIN;
@WebServlet(name = "AdminLoginResponse", urlPatterns = "/AdminLoginResponse")

public class AdminLoginServlet extends HttpServlet{

    @Override
    public void destroy() {
        synchronized (this) {
            Engine engine = ServletUtils.getAdminManger(getServletContext());
            engine.getDatabase().setAdminConnected(false);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");

        String adminNameFromSession = SessionUtils.getAdminName(request);
        if (adminNameFromSession == null) { //admin is not logged in yet

            String usernameFromParameter = request.getParameter(USERNAME);
            if (usernameFromParameter == null || usernameFromParameter.isEmpty()) {
                //no adminName in session and no adminName in parameter - not standard situation. it's a conflict

                // stands for conflict in server state
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            } else {
                //normalize the username value
                usernameFromParameter = usernameFromParameter.trim();

            /*
            One can ask why not enclose all the synchronizations inside the userManager object ?
            Well, the atomic action we need to perform here includes both the question (isUserExists) and (potentially) the insertion
            of a new user (addUser). These two actions needs to be considered atomic, and synchronizing only each one of them, solely, is not enough.
            (of course there are other more sophisticated and performable means for that (atomic objects etc) but these are not in our scope)

            The synchronized is on this instance (the servlet).
            As the servlet is singleton - it is promised that all threads will be synchronized on the very same instance (crucial here)

            A better code would be to perform only as little and as necessary things we need here inside the synchronized block and avoid
            do here other not related actions (such as response setup. this is shown here in that manner just to stress this issue
             */
                Engine engine = ServletUtils.getAdminManger(getServletContext());
                synchronized (this) {
                    if (engine.getDatabase().isAdminConnected()) {
                        String errorMessage = "Admin " + usernameFromParameter + " already Logged In. Please wait till this admin finish his work.";

                        // stands for unauthorized as there is already such user with this name
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getOutputStream().print(errorMessage);
                    } else {
                        request.getSession(true).setAttribute(ADMIN, usernameFromParameter);
                        engine.getDatabase().addAdmin(usernameFromParameter);
                        engine.getDatabase().setAdminConnected(true);
                        request.getSession(true).setAttribute(IS_ADMIN_LOGGED_IN, true);

                        //redirect the request to the chat room - in order to actually change the URL
                        System.out.println("On login, request URI is: " + request.getRequestURI());
                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                }
            }
        } else {
            //user is already logged in
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}