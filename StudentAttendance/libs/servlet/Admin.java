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

public class Admin extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			JSONObject resp = new JSONObject();
			JSONObject condition;
			JSONObject user = Utils.getUser(request);
			if (!user.getString("role").equals("admin")) {

			}
			String type = request.getParameter("type");
			switch (type) {
			case "get_list_table":
				resp.put(STATE, SUCCESS);
				resp.put(DATA, DataUtils.getListTable());
				out.print(resp);
				break;
			case "get_list_field":
				resp.put(STATE, SUCCESS);
				String tableName = request.getParameter("table_name");
				resp.put(DATA, DataUtils.getListField(tableName));
				out.print(resp);
				break;
			case "search":
				resp.put(STATE, SUCCESS);
				tableName = request.getParameter("table_name");
				String field = request.getParameter("field");
				String keyword = request.getParameter("keyword");
				if (keyword.equals("")) {
					resp.put(DATA, DataUtils.getAll(tableName));
				} else {
					condition = new JSONObject();
					condition.put(field, keyword);
					resp.put(DATA, DataUtils.getList(condition, tableName));
				}
				out.print(resp);
				break;
			case "delete":
				resp.put(STATE, SUCCESS);
				tableName = request.getParameter("table_name");
				condition = new JSONObject(request.getParameter("condition"));
				DataUtils.delete(condition, tableName);
				resp.put(STATE, SUCCESS);
				out.print(resp);
				break;
			case "insert":
				resp.put(STATE, SUCCESS);
				tableName = request.getParameter("table_name");
				JSONObject obj = new JSONObject(request.getParameter("obj"));
				DataUtils.insert(obj, tableName);
				resp.put(STATE, SUCCESS);
				out.print(resp);
				break;
			case "edit":
				resp.put(STATE, SUCCESS);
				tableName = request.getParameter("table_name");
				condition = new JSONObject(request.getParameter("condition"));
				obj = new JSONObject(request.getParameter("obj"));
				DataUtils.edit(condition, obj, tableName);
				resp.put(STATE, SUCCESS);
				out.print(resp);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
