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
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Quaternion(float w, Vector3 vec3) {
		float theta = (float) Math.toRadians(w);
		this.w = (float) Math.cos(theta/2.0f);
		this.x = (float) (vec3.x*Math.sin(theta/2.0f));
		this.y = (float) (vec3.y*Math.sin(theta/2.0f));
		this.z = (float) (vec3.z*Math.sin(theta/2.0f));
	}
	
	public Quaternion mul(Quaternion q) {
		float nW = w * q.w - x * q.x - y * q.y - z * q.z;
		float nX = w * q.x + x * q.w + y * q.z - z * q.y;
		float nY = w * q.y - x * q.z + y * q.w + z * q.x;
		float nZ = w * q.z + x * q.y - y * q.x + z * q.w;
		return new Quaternion(nW, nX, nY, nZ);
	}
	
	public Quaternion mul(Vector3 vec3)
	{
		float nW = -x * vec3.x - y * vec3.y - z * vec3.z;
		float nX = w * vec3.x + y * vec3.z - z * vec3.y;
		float nY = w * vec3.y + z * vec3.x - x * vec3.z;
		float nZ = w * vec3.z + x * vec3.y - y * vec3.x;
		return new Quaternion(nW, nX, nY, nZ);
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
	
	public void print() {
		System.out.println("Quaternion: " + w + "," + x + "," + y + "," + z);
	}
	
}
