package util;

public class FloatUtility {
	
	private static double margin = 0.0001;
	
	public static void setMargin(double m){
		margin = m;
	}

	public static boolean near(double f1, double f2){
		return Math.abs(f1 - f2) < margin;
	}
	
	public static boolean near(float f1, float f2){
		return Math.abs(f1 - f2) < (float) margin;
	}

}
