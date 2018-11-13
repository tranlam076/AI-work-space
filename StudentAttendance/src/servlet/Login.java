package servlet;

import static business.Constants.*;
import data.DataUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject condition = new JSONObject();
            condition.put(EMAIL, request.getParameter(EMAIL));
            condition.put(PASSWORD, request.getParameter(PASSWORD));
            JSONObject user = DataUtils.getOne(condition, USER);
            JSONObject resp = new JSONObject();
            if (user != null) {
                resp.put(STATE, SUCCESS);
                resp.put(DATA, user);
            } else {
                resp.put(STATE, FAIL);
            }
            out.print(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
