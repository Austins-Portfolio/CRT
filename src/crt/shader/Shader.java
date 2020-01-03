package crt.shader;

import java.awt.Color;
import java.util.ArrayList;

import crt.math.MathUtils;
import crt.math.Vector3;
import crt.trace.Intersect;

public class Shader {

	public static int genColorWhite(Intersect intersect) {
		if(intersect!=null) {
			Color color = new Color(255, 255, 255);
			return color.getRGB();
		}
		return 0;
	}
	
	public static int genColor(Intersect intersect) {
		if(intersect!=null) {
			Color color = new Color(intersect.material.r, intersect.material.g, intersect.material.b);
			return color.getRGB();
		}
		return 0;
	}
	
	static float d = 2;
	public static int genColorShadeDistance(Intersect intersect) {
		if(intersect!=null) {
			float r = MathUtils.clamp(intersect.material.r/(intersect.distance/d),0,255);
			float g = MathUtils.clamp(intersect.material.g/(intersect.distance/d),0,255);
			float b = MathUtils.clamp(intersect.material.b/(intersect.distance/d),0,255);
			int ir = (int) r;
			int ig = (int) g;
			int ib = (int) b;
			Color color = new Color(ir, ig, ib);
			return color.getRGB();
		}
		return 0;
	}
	
	public static int genColorMultiBounce(Intersect intersect) {
		if(intersect!=null) {
			ArrayList<Intersect> intersects = new ArrayList<Intersect>();
			intersect.unpack(intersects);
			float finalR = 0;
			float finalG = 0;
			float finalB = 0;
			for(int i = (intersects.size()-1);i >= 0;i--) {
				Intersect myIntersect = intersects.get(i);
				float r = myIntersect.material.r;
				float g = myIntersect.material.g;
				float b = myIntersect.material.b;
				float ref = myIntersect.material.reflective;
				r = MathUtils.clamp(r*(1-ref), 0, 255);
				g = MathUtils.clamp(g*(1-ref), 0, 255);
				b = MathUtils.clamp(b*(1-ref), 0, 255);
				float tempR = MathUtils.clamp(finalR*ref, 0, 255);
				float tempG = MathUtils.clamp(finalG*ref, 0, 255);
				float tempB = MathUtils.clamp(finalB*ref, 0, 255);
				finalR = r + tempR;
				finalG = g + tempG;
				finalB = b + tempB;
			}
			finalR = MathUtils.clamp(finalR, 0, 255);
			finalG = MathUtils.clamp(finalG, 0, 255);
			finalB = MathUtils.clamp(finalB, 0, 255);
			Color color = new Color((int)finalR, (int)finalG, (int)finalB);
			return color.getRGB();
		}
		return new Color(255,255,255).getRGB();
	}
	
	static float d2 = 10;
	public static int genColorMultiBounceShadeDistance(Intersect intersect) {
		if(intersect!=null) {
			ArrayList<Intersect> intersects = new ArrayList<Intersect>();
			intersect.unpack(intersects);
			float finalR = 0;
			float finalG = 0;
			float finalB = 0;
			for(int i = (intersects.size()-1);i >= 0;i--) {
				Intersect myIntersect = intersects.get(i);
				float r = myIntersect.material.r;
				float g = myIntersect.material.g;
				float b = myIntersect.material.b;
				float ref = myIntersect.material.reflective;
				r = MathUtils.clamp(r*(1-ref), 0, 255);
				g = MathUtils.clamp(g*(1-ref), 0, 255);
				b = MathUtils.clamp(b*(1-ref), 0, 255);
				float tempR = MathUtils.clamp(finalR*ref, 0, 255);
				float tempG = MathUtils.clamp(finalG*ref, 0, 255);
				float tempB = MathUtils.clamp(finalB*ref, 0, 255);
				finalR = r + tempR;
				finalG = g + tempG;
				finalB = b + tempB;
			}
			finalR = MathUtils.clamp(finalR/(intersect.distance/d2), 0, 255);
			finalG = MathUtils.clamp(finalG/(intersect.distance/d2), 0, 255);
			finalB = MathUtils.clamp(finalB/(intersect.distance/d2), 0, 255);
			Color color = new Color((int)finalR, (int)finalG, (int)finalB);
			return color.getRGB();
		}
		return 0;
	}
	
	public static int genColorShadeNormal(Intersect intersect) {
		if(intersect!=null) {
			float dot = intersect.normal.dot(new Vector3(0,1,0));
			float r = MathUtils.clamp(dot*255,0,255);
			float g = MathUtils.clamp(dot*255,0,255);
			float b = MathUtils.clamp(dot*255,0,255);
			int ir = (int) r;
			int ig = (int) g;
			int ib = (int) b;
			Color color = new Color(ir, ig, ib);
			return color.getRGB();
		}
		return 0;
	}
	
}
