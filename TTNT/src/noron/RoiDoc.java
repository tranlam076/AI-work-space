package noron;

public class RoiDoc {
	public static void main(String[] args) {
		new RoiDoc();
	}
	
	public RoiDoc () {
		double x= 5;
		double alpha = 0.1;
		for (int i =0; i<100; i++) {
			x= x - alpha*f1(x);
			System.out.println(x + " - " + f(x));
		}
	}
	
	
	double f1 (double x) {
		double d = 1e-8;
		return (f(x+d)-f(x))/d;
	}
	
	double f (double x) {
		return((x-1)*(x-1));
	}
}
