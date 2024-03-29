package crt.trace;

import java.util.ArrayList;
import crt.objects.geometry.GeometricObject;
import crt.objects.geometry.Ray;

public class Scene {

	ArrayList<GeometricObject> Objects = new ArrayList<GeometricObject>();
	
	public void addGeometricObject(GeometricObject object) {
		Objects.add(object);
	}
	
	public Intersect intersect(Ray ray,int bounce) {
		float closestHit = Float.MAX_VALUE;
		Intersect finalIntersect = null;
		for(int i = 0; i < Objects.size();i++) {
			Intersect intersect = Objects.get(i).intersect(ray, bounce);
			if(intersect!=null) {
				if(closestHit > intersect.distance && intersect.distance > 0) {
					closestHit = intersect.distance;
					finalIntersect = intersect;
				}
			}
		}
		
		
		if(finalIntersect!=null) {
			finalIntersect.bounce(this);
		}
		
		return finalIntersect;
	}
	
}
