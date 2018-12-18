package servlet.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.service.StudentService;
import model.Course;
import model.Student;

@Path("/students")
public class StudentApi {

	private StudentService studentService;
	
	public StudentApi() {
		studentService = new StudentService();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentById(@PathParam("id") String id) {
		Student student = studentService.get(id);
		if (student == null) {
			return Response.status(404).build();
		}
		return Response.ok().entity(student).build();
	}
	
	@Path("{id}/courses")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeacherCourses(@PathParam("id") String id) {
		Student student = studentService.get(id);
		if (student == null) {
			return Response.status(404).build();
		}
		List<Course> courseList = studentService.getCourses(id);
		return Response.ok().entity(courseList).build();
	}
}
