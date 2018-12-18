 package util;

public class Constants {

	public static String DATA_PATH = "C:\\HippoTech\\StudentAttendance\\db\\sa.db";
	public static String IMAGE_PATH = "C:\\HippoTech\\StudentAttendance\\db\\image\\";
	public static String IMAGE_DATA_PATH = "C:\\HippoTech\\StudentAttendance\\db\\image_data\\";
	public static String FACE_DATA_SET = "C:\\HippoTech\\StudentAttendance\\db\\face_dataset\\";
	
//	public static String DATA_PATH = "/home/devteam/db/sa.db";
//	public static String IMAGE_PATH = "/home/devteam/db/image/";
//	public static String IMAGE_DATA_PATH = "/home/devteam/db/image_data/";
//	public static String FACE_DATA_SET = "/home/devteam/db/face_dataset/";

	
	public static interface url {
		String ROOT = "/StudentAttendance";
//		String DEFAULT = ROOT;
	    String HOME = ROOT + "/home";
		String LOGIN = ROOT + "/login";
		String SIGN_UP = ROOT + "/signup";
		String TEACHER = ROOT + "/teacher";
		String ADMIN = ROOT + "/admin";
		String TEACHER_CAMERA = TEACHER + "/camera";
		String TEACHER_FILE = TEACHER + "/file";
		String STUDENT = ROOT + "/student";
	}
	
	public static interface id_prefix {
		String COURSE = "c";
		String IMAGE = "i";
		String STUDENT = "s";
		String USER = "u";
	}
	
	public static interface filename {
//		String RESULTS = "results.csv";
//		String IDS = "ids.txt";
//		String TEMPLATE = "template.txt";
//		String TEMPLATE_ORIGIN = "template_origin.txt";
//		String CDR_CLC_JAPAN = "CDR_CLC_JAPAN.txt";
//		String CDR_CLC_HTDN = "CDR_CLC_HTDN.txt";
//		String GOALS_CLC_JAPAN = "GOALS_CLC_JAPAN.txt";
//		String GOALS_CLC_HTDN = "GOALS_CLC_HTDN.txt";
	}
	
}
