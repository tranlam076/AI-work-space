package servlet.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import data.service.ImageCropService;
import data.service.ImageService;
import model.Image;
import model.Mark;
import util.Constants;
import util.DetectorUtils;
import util.FileHandle;
import util.StringUtils;

@Path("/images")
public class ImageApi {

	@Context
	private HttpServletRequest servletRequest;

	private ImageService ImageService;

	public ImageApi() {
		ImageService = new ImageService();
	}

	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteImage(@PathParam("id") String id) {
		ImageService.delete(id);
		return Response.ok().entity("{\"status\":\"success\"}").build();
	}

	@Path("{id}/data")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@PathParam("id") String id) {
		String dataPath = Constants.IMAGE_DATA_PATH + id + ".json";
		String fileContent = FileHandle.getInstance().readFile(dataPath);
		return Response.ok().entity("{\"status\":\"success\", \"data\":\"" + StringUtils.encodeUrl(fileContent) + "\"}")
				.build();
	}

	@Path("{id}/data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addData(@PathParam("id") String id, @FormParam("data") String data) {
		String dataPath = Constants.IMAGE_DATA_PATH + id + ".json";
		FileHandle.getInstance().writeFile(dataPath, data);
		Gson gson = new Gson();
		Mark[] listMark = gson.fromJson(data, Mark[].class);
		Image img = ImageService.get(id);
		
		String fileUrl = servletRequest.getServletContext().getRealPath(img.getUrl()) + img.getName();
		HashMap<String, String> map = new ImageCropService().cropImages(fileUrl, listMark, img.getName(), img.getExt());
		for(String key : map.keySet()) {
			DetectorUtils.retrain(map.get(key), key);
		}
		
		return Response.ok().entity("{\"status\":\"success\"}").build();
	}

	@Path("{id}/data/{student_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataStudent(@PathParam("id") String id, @PathParam("student_id") String studentId) {
		String dataPath = Constants.IMAGE_DATA_PATH + id + ".json";
		List<Mark> markList = FileHandle.getInstance().getMarksData(dataPath);
		Mark studentMark = null;
		for (Mark mark : markList) {
			if (mark.getStudentCode().equals(studentId)) {
				studentMark = mark;
				break;
			}
		}
		if (studentMark == null) {
			return Response.status(404).build();
		}
		Gson gson = new Gson();
		return Response.ok().entity(gson.toJson(studentMark)).build();
	}
	
	@Path("{id}/data/{student_id}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDataStudent(@PathParam("id") String id, @PathParam("student_id") String studentId, @FormParam("data") String data) {
		try {
			System.out.println(data);
			String dataPath = Constants.IMAGE_DATA_PATH + id + ".json";
			List<Mark> newMarkList = new ArrayList<Mark>();
			List<Mark> currentMarkList = FileHandle.getInstance().getMarksData(dataPath);
			Gson gson = new Gson();
			Mark studentMark = gson.fromJson(data, Mark.class);
			for (Mark mark : currentMarkList) {
				if (mark.getStudentCode().equals(studentId)) {
					mark = studentMark;
				}
				newMarkList.add(mark);
			}
			String newData = gson.toJson(newMarkList);
			FileHandle.getInstance().writeFile(dataPath, newData);
			if (studentMark == null) {
				return Response.status(404).build();
			}
			return Response.ok().entity(gson.toJson(studentMark)).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
		
	}
}
