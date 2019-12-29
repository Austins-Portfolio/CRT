package crt.main;

import crt.math.Vector3;

public class Launcher {

	public static void main(String args[]) {
		Vector3 vec1 = new Vector3(1,5,7);
		Vector3 vec2 = new Vector3(1,5,7);
		Vector3 vecCrossed = vec1.cross(vec2);
		float vecDotted = vec1.dot(vec2);
		vecCrossed.print();
		System.out.println(vecDotted);
		Vector3 vecProjected = vec1.scalarProjection(vec2);
		vecProjected.print();
	}
	
}
