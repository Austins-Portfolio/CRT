package crt.objects.geometry;

import crt.math.Vector3;
import crt.objects.materials.Material;
import crt.trace.Intersect;

public class Sphere extends GeometricObject{
	
	Vector3 pos;
	float radius;
	Material material;
	
	public Sphere(Vector3 pos, float radius, Material material) {
		this.pos = pos;
		this.radius = radius;
		this.material = material;
	}

	@Override
	public Intersect intersect(Ray ray, int bounce) {
		
		float t = pos.sub(ray.pos).dot(ray.dir);
			
		Vector3 newCenter = ray.pos.add(ray.dir.mul(t));
		
		float dis = pos.sub(newCenter).mag();
		if(dis < radius) {
			float x =(float) Math.sqrt((radius*radius)-(dis*dis));
			float t1 = t - x;
			float t2 = t + x;
			
			if(t1 < t2) {
				return new Intersect(ray, t1, ray.pos.add(ray.dir.mul(t1)), ray.pos.add(ray.dir.mul(t1)).sub(pos).normalize(), material, bounce);
			}else {
				return new Intersect(ray, t2, ray.pos.add(ray.dir.mul(t2)), ray.pos.add(ray.dir.mul(t2)).sub(pos).normalize(), material, bounce);
			}
		}
		
		return null;
	}

	@Override
	public boolean intersectLight(Ray ray) {
		// TODO Auto-generated method stub
		return false;
	}

}
