package servlet.api;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import data.service.AdminService;
import model.Course;
import model.Image;
import model.Role;
import model.Student;
import model.StudentCourse;
import model.StudentImage;
import model.Teacher;
import model.User;
import util.DetectorUtils;

@Path("/admin")
public class AdminApi {

	private AdminService adminService;

	public AdminApi() {
		adminService = new AdminService();
	}
	
	@Path("{train}")
	@GET
	public Response training() {
		DetectorUtils.train();
		return Response.ok().build();
	}

	@Path("{tables}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTables() {
		List<String> tableList = adminService.getTables();
		if (tableList == null) {
			return Response.status(404).build();
		}
		return Response.ok().entity(tableList).build();
	}

	@Path("tables/{tableName}/columns")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeacherCourses(@PathParam("tableName") String tableName) {
		List<String> columnList = adminService.getTableColumns(tableName);
		if (columnList == null) {
			return Response.status(404).build();
		}
		return Response.ok().entity(columnList).build();
	}
	
	@Path("tables/list-student-id")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListStudentId() {
		List<String> studentIdList = adminService.getTableColumns();
		if (studentIdList == null) {
			return Response.status(404).build();
		}
		return Response.ok().entity(studentIdList).build();
	}

	@Path("tables/search")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@FormParam("tableName") String tableName, @FormParam("field") String field,
			@FormParam("keyWord") String keyWord) {
		Gson gson = new Gson();
		switch (tableName) {
		case "user":
			List<User> userList = adminService.getTableUser(field, keyWord);
			return Response.ok().entity(gson.toJson(userList)).build();
		case "role":
			List<Role> roleList = adminService.getTableRole(field, keyWord);
			return Response.ok().entity(gson.toJson(roleList)).build();
		case "course":
			List<Course> courseList = adminService.getTableCourse(field, keyWord);
			return Response.ok().entity(gson.toJson(courseList)).build();
		case "image":
			List<Image> imageList = adminService.getTableImage(field, keyWord);
			return Response.ok().entity(gson.toJson(imageList)).build();
		case "student":
			List<Student> studentList = adminService.getTableStudent(field, keyWord);
			return Response.ok().entity(gson.toJson(studentList)).build();
		case "teacher":
			List<Teacher> teacherList = adminService.getTableTeacher(field, keyWord);
			return Response.ok().entity(gson.toJson(teacherList)).build();
		case "student_course":
			List<StudentCourse> studenCoursetList = adminService.getTableStudentCourse(field, keyWord);
			return Response.ok().entity(gson.toJson(studenCoursetList)).build();
		case "student_image":
			List<StudentImage> studentImagetList = adminService.getTableStudentImage(field, keyWord);
			return Response.ok().entity(gson.toJson(studentImagetList)).build();
		}
		return Response.ok().entity("{\"status\":\"fail\"}").build();
	}

	@Path("tables/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response editColumn(@FormParam("tableName") String tableName, @FormParam("condition") String condition,
			@FormParam("data") String newData) {
		String conditionArr[] = condition.split(",");
		String conditionFields[] = new String[conditionArr.length];
		String conditionValues[] = new String[conditionArr.length];
		for (int i = 0; i < conditionArr.length; i++) {
			conditionFields[i] = conditionArr[i].split(":::")[0];
			conditionValues[i] = conditionArr[i].split(":::")[1];
		}

		String newDateArr[] = newData.split(",");
		String newDataFields[] = new String[newDateArr.length];
		String newDataValues[] = new String[newDateArr.length];
		for (int i = 0; i < newDateArr.length; i++) {
			newDataFields[i] = newDateArr[i].split(":::")[0];
			newDataValues[i] = newDateArr[i].split(":::")[1];
		}

		boolean result = adminService.updateRecord(tableName, newDataFields, newDataValues, conditionFields,
				conditionValues);
		if (!result) {
			return Response.status(404).build();
		}
		return Response.ok().entity("{\"status\":\"success\"}").build();
	}

	@Path("tables/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteColumn(@FormParam("tableName") String tableName, @FormParam("condition") String condition) {
		String conditionArr[] = condition.split(",");
		String conditionFields[] = new String[conditionArr.length];
		String conditionValues[] = new String[conditionArr.length];
		for (int i = 0; i < conditionArr.length; i++) {
			conditionFields[i] = conditionArr[i].split(":::")[0];
			conditionValues[i] = conditionArr[i].split(":::")[1];
		}

		boolean result = adminService.deleteRecord(tableName, conditionFields, conditionValues);
		if (!result) {
			return Response.status(404).build();
		}
		return Response.ok().entity("{\"status\":\"success\"}").build();
	}

	@Path("tables/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addColumn(@FormParam("tableName") String tableName, @FormParam("data") String newData) {
		String newDateArr[] = newData.split(",");
		String newDataFields[] = new String[newDateArr.length];
		String newDataValues[] = new String[newDateArr.length];
		for (int i = 0; i < newDateArr.length; i++) {
			newDataFields[i] = newDateArr[i].split(":::")[0];
			newDataValues[i] = newDateArr[i].split(":::")[1];
		}

		boolean result = adminService.addNewRecord(tableName, newDataFields, newDataValues);
		if (!result) {
			return Response.status(404).build();
		}
		return Response.ok().entity("{\"status\":\"success\"}").build();
	}
}
