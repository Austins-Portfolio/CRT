package crt.trace;

import java.util.ArrayList;

import crt.main.Settings;
import crt.math.Vector3;
import crt.objects.geometry.Ray;
import crt.objects.materials.Material;

public class Intersect {
	
	public Ray ray;
	public float distance;
	public Vector3 point,normal;
	public Material material;
	public Intersect intersect;
	public int bounce;
	
	int max_bounce = Settings.MAX_BOUNCE;
	
	public Intersect(Ray ray, float distance,Vector3 point, Vector3 normal, Material material, int bounce) {
		this.ray = ray;
		this.distance = distance;
		this.point = point;
		this.normal = normal;
		this.material = material;
		this.bounce = bounce;
	}
	
	public void bounce(Scene scene) {
		if(material.reflective !=0.0 || material.refractive !=0.0) {
			if(bounce < max_bounce) {
				intersect = scene.intersect(new Ray(point,ray.dir.reflect(normal)), bounce+=1);
				if(intersect!=null) {
					intersect.bounce(scene);
				}
				bounce++;
			}
		}
	}
	
	public ArrayList<Intersect> unpack(ArrayList<Intersect> Intersects) {
		if(Intersects == null) {
			Intersects = new ArrayList<Intersect>();
		}
		Intersects.add(this);
		if(intersect!=null) {
			intersect.unpack(Intersects);
		}
		return Intersects;
	}
	
}
