package crt.math;

public class Quaternion {

	public float w,x,y,z;
	
	public Quaternion() {	
		this.w = 0;
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Quaternion(float w, float x, float y, float z) {
		float theta = (float) Math.toRadians(w);
		this.w = (float) Math.cos(theta/2);
		this.x = (float) (x*Math.sin(theta/2));
		this.y = (float) (y*Math.sin(theta/2));
		this.z = (float) (z*Math.sin(theta/2));
	}
	
	public Quaternion(float w, Vector3 vec3) {
		float theta = (float) Math.toRadians(w);
		this.w = (float) Math.cos(theta/2);
		this.x = (float) (vec3.x*Math.sin(theta/2));
		this.y = (float) (vec3.y*Math.sin(theta/2));
		this.z = (float) (vec3.z*Math.sin(theta/2));
	}
	
	public Quaternion mul(Quaternion q) {
		float nW = w * q.w - x * q.x - y * q.y - z * q.z;
		Vector3 vec3 = new Vector3(w * q.x + x * q.w + y * q.z - z * q.y, w * q.y - x * q.z + y * q.w + z * q.x, w * q.z + x * q.y - y * q.x + z * q.w);
		return new Quaternion(nW,vec3);
	}
	
	public Quaternion mul(Vector3 vec3)
	{
		float nW = -x * vec3.x - y * vec3.y - z * vec3.z;
		Vector3 vec3_2 = new Vector3(w * vec3.x + y * vec3.z - z * vec3.y, w * vec3.y + z * vec3.x - x * vec3.z, w * vec3.z + x * vec3.y - y * vec3.x);
		return new Quaternion(nW,vec3_2);
	}
	
	public float mag() {
		return (float) Math.sqrt((w*w)+(x*x)+(y*y)+(z*z));
	}
	
	public Quaternion conjugate()
	{
		return new Quaternion(w, -x, -y, -z);
	}
	
	public Quaternion normalize()
	{
		float mag = mag();
		return new Quaternion(w / mag, x / mag, y / mag, z / mag);
	}
	
}
