package crt.objects.materials;

public class Material {
	
	int r,g,b;
	float roughness,reflective,refractive;
	
	public Material(int r, int g, int b, float roughness, float reflective, float refractive) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.roughness = roughness;
		this.reflective = reflective;
		this.refractive = refractive;
	}
	
}
