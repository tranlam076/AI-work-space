package servlet;

import data.DataUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;

public class Utils {

    public static JSONObject getAuthFailResp() {
        JSONObject authFailResp = new JSONObject();
        authFailResp.put("state", "Authentication Fail");
        return authFailResp;
    }

    public static JSONObject getSysFailResp() {
        JSONObject sysFailResp = new JSONObject();
        sysFailResp.put("state", "System error");
        return sysFailResp;
    }

    public static JSONObject getSuccessResp() {
        JSONObject successResp = new JSONObject();
        successResp.put("state", "success");
        return successResp;
    }
    
    public static JSONObject getFailResp() {
        JSONObject successResp = new JSONObject();
        successResp.put("state", "duplicate code");
        return successResp;
    }

    public static String getCookie(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
            }
        }
        return "";
    }

    public static JSONObject getUser(HttpServletRequest request) throws IOException {
        JSONObject condition = new JSONObject();
        condition.put("email", Utils.getCookie(request, "email"));
        condition.put("password", Utils.getCookie(request, "password"));
        JSONObject user = DataUtils.getOne(condition, "user");
        return user;
    }
    
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.name"));
    }
}
