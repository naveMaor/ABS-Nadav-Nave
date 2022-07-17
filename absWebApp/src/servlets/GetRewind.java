package servlets;

import com.google.gson.Gson;
import engine.Engine;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ServletUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetRewind", urlPatterns = "/Rewind")
public class GetRewind extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Engine systemEngine = ServletUtils.getSystemEngine(getServletContext());

        Boolean rewind = systemEngine.getDatabase().isRewind();

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(rewind);

        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse);
            out.flush();
        }
        System.out.println("request URI is: " + request.getRequestURI());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
