package crt.objects.geometry;

import crt.math.MathUtils;
import crt.math.Vector3;
import crt.trace.Intersect;

public abstract class LightObject {

	public int r,g,b;
	public Vector3 pos;
	
	public LightObject(int r, int g,int b, Vector3 pos) {
		this.r = MathUtils.clamp(r, 0, 255);
		this.g = MathUtils.clamp(g, 0, 255);
		this.b = MathUtils.clamp(b, 0, 255);
		this.pos = pos;
	}
	
	public abstract Intersect intersect(Ray ray);
	
}
