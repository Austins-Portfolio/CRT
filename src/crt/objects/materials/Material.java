package crt.objects.materials;

import crt.math.MathUtils;

public class Material {
	
	public int r,g,b;
	public float roughness,reflective,refractive;
	
	public Material(int r, int g, int b, float roughness, float reflective, float refractive) {
		this.r = MathUtils.clamp(r, 0, 255);
		this.g = MathUtils.clamp(g, 0, 255);
		this.b = MathUtils.clamp(b, 0, 255);
		this.roughness = MathUtils.clamp(roughness, 0, 1);
		this.reflective = MathUtils.clamp(reflective, 0, 1);
		this.refractive = MathUtils.clamp(refractive, 0, 1);
		
	}
	
}
