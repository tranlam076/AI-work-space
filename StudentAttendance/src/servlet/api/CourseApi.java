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

import data.service.CourseService;
import data.service.StudentCourseService;
import data.service.StudentService;
import data.service.UserService;
import model.Course;
import model.Image;
import model.Student;
import model.StudentCourse;
import model.User;
import util.Constants;
import util.StringUtils;

@Path("/courses")
public class CourseApi {

	private CourseService courseService;
	private StudentService studentService;
	private UserService userService;
	private StudentCourseService studentCourseService;
	
	public CourseApi() {
		courseService = new CourseService();
		studentService = new StudentService();
		studentCourseService = new StudentCourseService();
		userService = new UserService();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCourseById(@PathParam("id") String id) {
		return "{\"test\":\"" + id + "\"}";
	}
	
	@Path("{id}/students")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourseStudents(@PathParam("id") String id) {
		List<Student> studentList = courseService.getStudents(id);
		return Response.ok().entity(studentList).build();
	}
	
	@Path("{id}/students")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourseStudent(
			@PathParam("id") String id, 
			@FormParam("name") String studentName) {
		Course course = courseService.get(id);
		if (course == null) {
			return Response.status(404).build();
		}
		String studentId = Constants.id_prefix.STUDENT + StringUtils.random(6);
		while(studentService.get(studentId) != null) {
			studentId = Constants.id_prefix.STUDENT + StringUtils.random(6);;
		}
		String userID = Constants.id_prefix.USER + StringUtils.random(6);;
		while(studentService.get(studentId) != null) {
			studentId = Constants.id_prefix.USER + StringUtils.random(6);;
		}
		User user = new User(userID, studentId, "123456", "student");
		userService.add(user);
		Student student = new Student(studentId, userID, studentName);
		studentService.add(student);
		StudentCourse studentCourse = new StudentCourse(studentId, id);
		studentCourseService.add(studentCourse);
		String result = String.format(
				"{"
				+ "`status`:`success`,"
				+ "`student`: {"
				+ "`id` : `%s`,"
				+ "`name`: `%s`"
				+ "}"
				+ "}", 
				studentId, studentName).replace("`", "\"");
		return Response.ok().entity(result).build();
	}
	
	@Path("{id}/images")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourseImages(@PathParam("id") String id) {
		List<Image> imageList = courseService.getImages(id);
		return Response.ok().entity(imageList).build();
	}
}
