package crt.shader;

import java.awt.Color;
import crt.trace.Intersect;

public class Shader {

	public static int genColor(Intersect intersect) {
		if(intersect!=null) {
			Color color = new Color(intersect.material.r, intersect.material.g, intersect.material.b);
			return color.getRGB();
		}
		return 0;
	}
	
}
