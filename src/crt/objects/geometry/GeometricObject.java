package crt.objects.geometry;

import crt.trace.Intersect;

public abstract class GeometricObject {

	public abstract Intersect intersect(Ray ray, int bounce);
	
	public abstract boolean intersectLight(Ray ray);
	
}
