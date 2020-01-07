package crt.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import crt.math.Vector3;
import crt.objects.geometry.Plane;
import crt.objects.geometry.Sphere;
import crt.objects.materials.Material;
import crt.trace.Camera;
import crt.trace.Scene;
import crt.trace.Tracer;

public class Launcher {

	static int width = Settings.DRAW_WIDTH;
	static int height = Settings.DRAW_HEIGHT;
	static int render_width = Settings.RENDER_WIDTH;
	static int render_height = Settings.DRAW_HEIGHT;
	static int targetFPS = Settings.TARGET_FPS;
	
	static int room_size = 2;
	
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setTitle("CTR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setEnabled(true);
		
		InputController inputController = new InputController();
		
		frame.addKeyListener(inputController);
		
		Tracer tracer = new Tracer(render_width, render_height);
		Camera camera = new Camera(new Vector3(6, 6, -60));
		Scene scene = new Scene();
		
		Material redMaterial = new Material(255, 0, 0, 0.1f, 0f, 0.1f);
		Material greenMaterial = new Material(0, 255, 0, 0.1f, 0f, 0.1f);
		Material blueMaterial = new Material(0, 0, 255, 0.1f, 0.5f, 0.1f);
		Material whiteMaterial = new Material(255, 255, 255, 0.1f, 0f, 0.1f);
		Material spaceMirrorMaterial = new Material(0, 0, 0, 0.1f, 1f, 0.1f);
		
		Sphere sphere = new Sphere(new Vector3(6, 6, 0), 1f, spaceMirrorMaterial);
		
		Plane plane = new Plane(new Vector3(0,0,0), new Vector3(0,1,0), whiteMaterial);
		Plane plane2 = new Plane(new Vector3(0,0,0), new Vector3(1,0,0), redMaterial);
		Plane plane3 = new Plane(new Vector3(6*room_size,0,0), new Vector3(1,0,0), greenMaterial);
		Plane plane5 = new Plane(new Vector3(0,6*room_size,0), new Vector3(0,1,0), whiteMaterial);
		Plane plane6 = new Plane(new Vector3(0,0,6*room_size), new Vector3(0,0,1), whiteMaterial);
		
		scene.addGeometricObject(sphere);
		
		scene.addGeometricObject(plane);
		scene.addGeometricObject(plane2);
		scene.addGeometricObject(plane3);
		scene.addGeometricObject(plane5);
		scene.addGeometricObject(plane6);
		
		Graphics g = frame.getGraphics();
		
		long startTime = System.currentTimeMillis();
		
		while(true) {
			long currentTime = System.currentTimeMillis();
			if(currentTime - startTime >= 1000/targetFPS) {
				camera.update(inputController);
				Image image = tracer.renderScene(camera, scene, Settings.THREAD_SPLIT);
				g.clearRect(0, 0, width, height);
				g.drawImage(image, 0, 0, width, height, null);
				currentTime = System.currentTimeMillis();
				long fpsTime = currentTime-startTime;
				int fps = (int) (1000/fpsTime);
				System.out.println("FPS:"+fps);
				frame.setTitle("CTR" + Settings.VERSION + " FPS:" + fps + " Threads:" + Settings.THREAD_SPLIT*Settings.THREAD_SPLIT + " Max_Bounce:" + Settings.MAX_BOUNCE);
				startTime = System.currentTimeMillis();
			}
		}
	}
	
}
