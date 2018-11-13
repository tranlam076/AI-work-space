package servlet;

import static business.Constants.DATA_DL;
import data.DataUtils;
import static data.DataUtils.contentOf;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static servlet.UploadImage.createIfNotExist;
import static servlet.UploadImage.SE;

public class Teacher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject user = Utils.getUser(request);
            if (user == null || !user.getString("role").equals("teacher")) {
                out.print(Utils.getAuthFailResp());
                return;
            }
            String type = request.getParameter("type");
            switch (type) {
                case "get_all_classes":
                    JSONObject condition = new JSONObject();
                    condition.put("teacher_email", user.get("email"));
                    JSONArray classList = DataUtils.getList(condition, "class");
                    JSONObject resp = Utils.getSuccessResp();
                    resp.put("data", classList);
                    out.print(resp);
                    break;
                case "get_all_class_field":
                    resp = Utils.getSuccessResp();
                    resp.put("data", DataUtils.getListField("class"));
                    out.print(resp);
                    break;
                case "get_all_student_field":
                    resp = Utils.getSuccessResp();
                    resp.put("data", DataUtils.getListField("student"));
                    out.print(resp);
                    break;
                case "create_a_class":
                    JSONObject obj = new JSONObject(request.getParameter("obj"));
                    JSONObject cond = new JSONObject();
                    cond.put("code", obj.getString("code"));
                    if (DataUtils.getOne(cond, "class") != null) {
                        resp = Utils.getFailResp();
                        out.print(resp);
                    } else if (obj.getString("teacher_email").equals(user.getString("email"))) {
                        DataUtils.insert(obj, "class");
                        resp = Utils.getSuccessResp();
                        out.print(resp);
                    } else {
                        out.print(Utils.getAuthFailResp());
                    }
                    break;
                case "edit_a_class":
                    obj = new JSONObject(request.getParameter("obj"));
                    condition = new JSONObject(request.getParameter("condition"));
                    if (obj.getString("teacher_email").equals(user.getString("email"))
                            && condition.getString("teacher_email").equals(user.getString("email"))) {
                        DataUtils.edit(condition, obj, "class");
                        resp = Utils.getSuccessResp();
                        out.print(resp);
                    } else {
                        out.print(Utils.getAuthFailResp());
                    }
                    break;
                case "delete_a_class":
                    condition = new JSONObject(request.getParameter("condition"));
                    if (condition.getString("teacher_email").equals(user.getString("email"))) {
                        DataUtils.delete(condition, "class");
                        resp = Utils.getSuccessResp();
                        out.print(resp);
                    } else {
                        out.print(Utils.getAuthFailResp());
                    }
                    break;
                case "list_all_students_in_class":
                    JSONObject classObj = new JSONObject(request.getParameter("obj"));
                    condition = new JSONObject();
                    condition.put("class_code", classObj.getString("code"));
                    if (classObj.getString("teacher_email").equals(user.getString("email"))) {
                        JSONArray listStudent = DataUtils.getList(condition, "student");
                        resp = Utils.getSuccessResp();
                        resp.put("data", listStudent);
                        out.print(resp);
                    } else {
                        out.print(Utils.getAuthFailResp());
                    }
                    break;
                case "add_student_to_class":
                    classObj = new JSONObject(request.getParameter("classObj"));
                    JSONObject studentObj = new JSONObject(request.getParameter("studentObj"));
                    cond = new JSONObject();
                    cond.put("code", studentObj.getString("code"));
                    cond.put("class_code", classObj.getString("code"));
                    if (DataUtils.getOne(cond, "student") != null) {
                        resp = Utils.getFailResp();
                        out.print(resp);
                    } else if (classObj.getString("teacher_email").equals(user.getString("email"))) {
                        DataUtils.insert(studentObj, "student");
                        resp = Utils.getSuccessResp();
                        out.print(resp);
                    } else {
                        out.print(Utils.getAuthFailResp());
                    }
                    break;
                case "delete_a_student_in_class":
                    condition = new JSONObject(request.getParameter("condition"));
                    cond = new JSONObject();
                    cond.put("code", condition.getString("class_code"));
                    classObj = DataUtils.getOne(cond, "class");
                    if (classObj.getString("teacher_email").equals(user.getString("email"))) {
                        DataUtils.delete(condition, "student");
                        resp = Utils.getSuccessResp();
                        out.print(resp);
                    } else {
                        out.print(Utils.getAuthFailResp());
                    }
                    break;
                case "get_all_images_in_class":
                    String folderPath = getServletContext().getRealPath("") + "images";
                    createIfNotExist(folderPath);
                    String class_code = request.getParameter("class_code");
                    resp = Utils.getSuccessResp();
                    JSONArray data = new JSONArray();
                    File[] f = (new File(folderPath)).listFiles();
                    for (File file : f) {
                        if (file != null
                                && file.getName().endsWith(".jpg")
                                && file.getName().startsWith(class_code + "_")) {
                            data.put("images/" + file.getName());
                        }
                    }
                    resp.put("data", data);
                    out.print(resp);
                    break;
                case "get_all_rectangles_in_image":
                    String filePath = DATA_DL
                            + "Detecion_Recognition_Result"
                            + SE + request.getParameter("result_file")
                            + ".json";
                    try {
                        resp = Utils.getSuccessResp();
                        data = new JSONArray(contentOf(filePath));
                        resp.put("data", data);
                        out.print(resp);
                    } catch (IOException | JSONException e) {
                        out.print(Utils.getSysFailResp());
                    }
                    break;
                case "change_faces_mark":
                    filePath = DATA_DL
                            + "Detecion_Recognition_Result"
                            + SE + request.getParameter("result_file")
                            + ".json";
                    try {
                        resp = Utils.getSuccessResp();
                        DataUtils.save(new JSONArray(request.getParameter("json_str")),
                                 filePath);
                        out.print(resp);
                    } catch (IOException | JSONException e) {
                        out.print(Utils.getSysFailResp());
                    }
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
