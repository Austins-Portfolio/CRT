package crt.trace;

import com.sun.glass.events.KeyEvent;

import crt.main.InputController;
import crt.main.Settings;
import crt.math.Quaternion;
import crt.math.Vector3;
import crt.objects.geometry.Ray;

public class Camera {

	Vector3 pos;
	Quaternion rotation;
	float eye_distance;
	float move_speed = 1f;
	float rotation_speed = 10f;
	
	public Camera(Vector3 pos) {
		this.pos = pos;
		this.rotation = new Quaternion(0.0f, 0, 0, 1f);
		this.eye_distance = 1.0f;
	}
	
	public void update(InputController inputController) {
		if(inputController.getKey(KeyEvent.VK_W)) {
			move(getForwardVector(), move_speed);
		}
		if(inputController.getKey(KeyEvent.VK_S)) {
			move(getForwardVector().negate(), move_speed);
		}
		if(inputController.getKey(KeyEvent.VK_D)) {
			move(getRightVector().negate(), move_speed);
		}
		if(inputController.getKey(KeyEvent.VK_A)) {
			move(getRightVector(), move_speed);
		}
		if(inputController.getKey(KeyEvent.VK_SPACE)) {
			move(getUpVector().negate(), move_speed);
		}
		if(inputController.getKey(KeyEvent.VK_C)) {
			move(getUpVector(), move_speed);
		}
		
		
		if(inputController.getKey(KeyEvent.VK_Q)) {
			rotate(getForwardVector(), rotation_speed);
		}
		if(inputController.getKey(KeyEvent.VK_E)) {
			rotate(getForwardVector(), -rotation_speed);
		}
		if(inputController.getKey(KeyEvent.VK_UP)) {
			rotate(getRightVector(), rotation_speed);
		}
		if(inputController.getKey(KeyEvent.VK_DOWN)) {
			rotate(getRightVector(), -rotation_speed);
		}
		if(inputController.getKey(KeyEvent.VK_LEFT)) {
			rotate(getUpVector(), rotation_speed);
		}
		if(inputController.getKey(KeyEvent.VK_RIGHT)) {
			rotate(getUpVector().negate(), rotation_speed);
		}
		
		if(inputController.getKey(KeyEvent.VK_Z)) {
			Settings.MAX_BOUNCE = Settings.MAX_BOUNCE-1;
		}
		if(inputController.getKey(KeyEvent.VK_X)) {
			Settings.MAX_BOUNCE = Settings.MAX_BOUNCE+1;
		}
		
	}
	
	public void move(Vector3 dir, float amount) {
		pos = pos.add(dir.mul(amount));
	}
	
	public void rotate(Vector3 axis, float theta) {
		Quaternion q = new Quaternion(theta, axis);
		rotation = q.mul(rotation);
		rotation = rotation.normalize();
	}
	
	public Ray generateCameraRay(int width, int height, int x, int y) {
		float mulX = (x - width*0.5F) / width;
		float mulY = (height*0.5f - y) / height;
		Vector3 forward = getForwardVector();
		Vector3 up = getUpVector();
		Vector3 right = getRightVector();
		right = right.mul(-mulX);
		up = up.mul(-mulY);
		forward = forward.add(pos);
		right = right.add(up);
		right = right.add(forward);
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
