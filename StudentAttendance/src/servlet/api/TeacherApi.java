package servlet.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.service.CourseService;
import data.service.TeacherService;
import model.Course;
import model.Teacher;
import util.Constants;
import util.StringUtils;

@Path("/teachers")
public class TeacherApi {

	private TeacherService teacherService;
	private CourseService courseService;

	public TeacherApi() {
		teacherService = new TeacherService();
		courseService = new CourseService();
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeacherById(@PathParam("id") String id) {
		Teacher teacher = teacherService.get(id);
		if (teacher == null) {
			return Response.status(404).build();
		}
		return Response.ok().entity(teacher).build();
	}

	@Path("{id}/courses")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeacherCourses(@PathParam("id") String id) {
		Teacher teacher = teacherService.get(id);
		if (teacher == null) {
			return Response.status(404).build();
		}
		List<Course> courseList = teacherService.getCourses(id);
		return Response.ok().entity(courseList).build();
	}

	@Path("{id}/courses")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTeacherCourse(@PathParam("id") String id, @FormParam("name") String courseName) {
		Teacher teacher = teacherService.get(id);
		if (teacher == null) {
			return Response.status(404).build();
		}
		String courseId = Constants.id_prefix.COURSE + StringUtils.random(6);
		while (courseService.get(courseId) != null) {
			courseId = Constants.id_prefix.COURSE + StringUtils.random(6);
		}
		Course course = new Course(courseId, id, courseName);
		if (courseService.add(course)) {
			return Response.accepted().build();
		} else {
			return Response.status(417).build();
		}
	}

	@Path("{id}/courses")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response editTeacherCourse(@PathParam("id") String id, @FormParam("name") String courseName,
			@FormParam("idClass") String courseId) {
		Teacher teacher = teacherService.get(id);
		if (teacher == null) {
			return Response.status(404).build();
		}
		Course oldcourse = new Course(courseId, id, courseName);
		Course newcourse = new Course(courseId, id, courseName);
		if (courseService.put(oldcourse, newcourse)) {
			return Response.accepted().build();

		} else {
			return Response.status(417).build();
		}
	}

	@Path("{id}/courses")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteTeacherCourse(@PathParam("id") String id, @FormParam("name") String courseName,
			@FormParam("idClass") String courseId) {
		Teacher teacher = teacherService.get(id);
		if (teacher == null) {
			return Response.status(404).build();
		}
		Course course = new Course(courseId, id, courseName);
		if (courseService.delete(course)) {
			return Response.accepted().build();
		} else {
			return Response.status(417).build();
		}
	}
}
