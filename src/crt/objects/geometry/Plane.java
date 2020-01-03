package crt.objects.geometry;

import crt.math.Vector3;
import crt.objects.materials.Material;
import crt.trace.Intersect;

public class Plane extends GeometricObject{
	
	Vector3 pos,normal;
	Material material;
	
	public Plane(Vector3 pos, Vector3 normal, Material material) {
		this.pos = pos;
		this.normal = normal;
		this.material = material;
	}

	@Override
	public Intersect intersect(Ray ray, int bounce) {
		
		float dDOTn = ray.dir.dot(normal);
		
		if(dDOTn != 0) {
			float t =  (1 / dDOTn) * (pos.dot(normal) - ray.pos.dot(normal));
			
			Intersect intersect = new Intersect(ray, t, ray.pos.add(ray.dir.mul(t)), normal, material, bounce);
			return intersect;
		}
		
		return null;
	}

}
