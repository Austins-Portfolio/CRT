package crt.trace;

import crt.math.Quaternion;
import crt.math.Vector3;
import crt.objects.geometry.Ray;

public class Camera {

	Vector3 pos;
	Quaternion rotation;
	float eye_distance;
	
	public Camera(Vector3 pos) {
		this.pos = pos;
		this.rotation = new Quaternion(0.0f, 0, 0, 1f);
		this.eye_distance = 1.1f;
	}
	
	public void move(Vector3 dir, float amount) {
		pos = pos.add(dir.mul(amount));
	}
	
	public void rotate(Vector3 axis, float theta) {
		rotation = rotation.mul(new Quaternion(theta, axis)).normalize();
	}
	
	public Ray generateCameraRay(int width, int height, int x, int y) {
		float mulX = (x - width*0.5F) / width;
		float mulY = (height*0.5f - y) / height;
		Vector3 forward = getForwardVector();
		Vector3 up = getUpVector();
		Vector3 right = getRightVector();
		right = right.mul(mulX);
		up = up.mul(mulY);
		forward = forward.add(pos);
		right = right.add(up);
		right = right.add(forward);
		//Vector3 point = right.mul(mulX).add(up.mul(mulY)).add(pos.add(forward));
		Vector3 point = right;
		Vector3 dir = point.sub(pos);
		dir = dir.normalize();
		return new Ray(pos, dir);
	}
	
	public Vector3 getForwardVector() {
		return new Vector3(0, 0, 1).mul(rotation);
	}
	
	public Vector3 getUpVector() {
		return new Vector3(0, 1, 0).mul(rotation);
	}
	
	public Vector3 getRightVector() {
		return new Vector3(1, 0, 0).mul(rotation);
	}
}
