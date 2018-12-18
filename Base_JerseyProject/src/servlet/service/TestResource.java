package servlet.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestResource {

	@Path("abc")
	@GET
	public Response test() {
		JwtHelper jwtLibrary = new JwtHelper();
		String token = jwtLibrary.getToken("123445555", "username1");
		System.out.println(token);
		String result = jwtLibrary.verifyToken(token);
		System.out.println(result);
		return Response.ok(result).build();
	}
}
