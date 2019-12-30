package crt.trace;

import crt.math.Vector3;
import crt.objects.geometry.Ray;
import crt.objects.materials.Material;

public class Intersect {
	
	Ray ray;
	float distance;
	Vector3 point,normal;
	Material materail;
	Intersect intersect;
	int bounce;
	
	int max_bounce = 0;
	
	public Intersect(Ray ray, float distance,Vector3 point, Vector3 normal, Material materail, int bounce) {
		this.ray = ray;
		this.distance = distance;
		this.point = point;
		this.normal = normal;
		this.materail = materail;
		this.bounce = bounce;
	}
	
	public void bounce(Scene scene) {
		if(bounce < max_bounce) {
			intersect = scene.intersect(new Ray(point,ray.dir.reflect(normal)));
			if(intersect!=null) {
				intersect.bounce(scene);
			}
		}
	}
	
	
}
