package crt.objects.geometry;

import crt.math.Vector3;

public class Ray {

	public Vector3 pos,dir;
	
	public Ray(Vector3 pos, Vector3 dir) {
		this.pos = pos;
		this.dir = dir;
	}
	
}
