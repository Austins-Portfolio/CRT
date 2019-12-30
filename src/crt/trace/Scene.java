package crt.trace;

import java.util.ArrayList;
import crt.objects.geometry.GeometricObject;
import crt.objects.geometry.Ray;

public class Scene {

	ArrayList<GeometricObject> Objects = new ArrayList<GeometricObject>();
	
	public void addGeometricObject(GeometricObject object) {
		Objects.add(object);
	}
	
	public Intersect intersect(Ray ray) {
		float closestHit = Float.MAX_VALUE;
		Intersect finalIntersect = null;
		for(int i = 0; i < Objects.size();i++) {
			Intersect intersect = Objects.get(i).intersect(ray);
			if(intersect!=null) {
				if(closestHit > intersect.distance) {
					closestHit = intersect.distance;
					finalIntersect = intersect;
				}
			}
		}
		
		finalIntersect.bounce(this);
		
		return finalIntersect;
	}
	
}
