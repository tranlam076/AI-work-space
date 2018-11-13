package servlet;

import static business.Constants.DATA_IMAGE;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.JSONArray;
import org.json.JSONObject;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 100,
        maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 100 * 5)
public class UploadImage extends HttpServlet {

    public static final String SE = File.separator;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            for (Part part : request.getParts()) {
                //
                String class_code = part.getName();
                String folderPath = getServletContext().getRealPath("") + "images";
                createIfNotExist(folderPath);
                long currentTime = System.currentTimeMillis();
                String filePath = folderPath + SE + class_code + "_" + currentTime + ".jpg";
                String filePath2 = DATA_IMAGE + class_code + "_" + currentTime + ".jpg";
                //
                BufferedImage image = ImageIO.read(part.getInputStream());
                BufferedImage img = deepCopy(image);
                ImageIO.write(image, "jpg", new File(filePath));
                ImageIO.write(img, "jpg", new File(filePath2));
                JSONArray face_list = new JSONArray(getFaces(filePath));
                JSONObject resp = new JSONObject();
                resp.put("state", "success");
                resp.put("data", face_list);
                out.println(resp);
                break;
            }
        } catch (IOException | ServletException e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public static void createIfNotExist(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private String getFaces(String path) {
        try {
            String str;
            try (Socket socket = new Socket("localhost", 6969);
                    DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                    DataInputStream din = new DataInputStream(socket.getInputStream())) {
                dout.writeUTF(path);
                dout.flush();
                str = din.readUTF();
            }
            return str;
        } catch (IOException e) {
        }
        return null;
    }

    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
