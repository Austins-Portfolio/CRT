package crt.math;

public class Vector3 {

	public float x,y,z;
	
	public Vector3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector3 vec3) {
		this.x = vec3.x;
		this.y = vec3.y;
		this.z = vec3.z;
	}
	
	public Vector3 add(Vector3 vec3) {
		return new Vector3(x + vec3.x, y + vec3.y, z + vec3.z);
	}
	
	public Vector3 sub(Vector3 vec3) {
		return new Vector3(x - vec3.x, y - vec3.y, z - vec3.z);
	}
	
	public Vector3 mul(float f) {
		return new Vector3(x * f, y * f, z * f);
	}
	
	public Vector3 div(float f) {
		return new Vector3(x / f, y / f, z / f);
	}
	
	public float mag() {
		return (float) Math.sqrt((x*x)+(y*y)+(z*z));
	}
	
	public Vector3 normalize() {
		return div(mag());
	}
	
	public float distance(Vector3 vec3) {
		return (float) Math.sqrt(((vec3.x - x)*(vec3.x - x)) + ((vec3.y - y)*(vec3.y - y)) + ((vec3.z - z)*(vec3.z - z)));
	}
	
	public float dot(Vector3 vec3) {
		return ((x*vec3.x)+(y*vec3.y)+(z*vec3.z));
	}
	
	public Vector3 cross(Vector3 vec3) {
		return new Vector3((y*vec3.z)-(z*vec3.y),(z*vec3.x)-(x*vec3.z),(x*vec3.y)-(y*vec3.x));
	}
	
	public Vector3 scalarProjection(Vector3 vec3) {
		Vector3 b = vec3.normalize();
		return b.mul(dot(b));
	}
	
	public void print() {
		System.out.println("Vector3: " + x + "," + y + "," + z);
	}
}
