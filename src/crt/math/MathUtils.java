package crt.math;

public class MathUtils {
	
	public static int clamp(int n, int min, int max) {
		if(n > max) {
			return max;
		}
		if(n < min) {
			return min;
		}
		return n;
	}

	public static float clamp(float n, float min, float max) {
		if(n > max) {
			return max;
		}
		if(n < min) {
			return min;
		}
		return n;
	}
	
}
