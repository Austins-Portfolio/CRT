package crt.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import crt.math.Vector3;
import crt.objects.geometry.Sphere;
import crt.objects.materials.Material;
import crt.trace.Camera;
import crt.trace.Scene;
import crt.trace.Tracer;

public class Launcher {

	static int width = Settings.RENDER_WIDTH;
	static int height = Settings.RENDER_HEIGHT;
	static int targetFPS = Settings.TARGET_FPS;
	
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
		
		Tracer tracer = new Tracer(width, height);
		Camera camera = new Camera(new Vector3(3, 3, -10));
		Scene scene = new Scene();
		
		Material redMaterial = new Material(255, 0, 0, 0.1f, 0.1f, 0.1f);
		Material greenMaterial = new Material(0, 255, 0, 0.1f, 0.1f, 0.1f);
		Material blueMaterial = new Material(0, 0, 255, 0.1f, 0.5f, 0.1f);
		
		Sphere sphere = new Sphere(new Vector3(6, 9f, 0), 1f, greenMaterial);
		Sphere sphere5 = new Sphere(new Vector3(6, 3f, 0), 1f, greenMaterial);
		//Sphere sphere4 = new Sphere(new Vector3(9, 6f, 0), 1f, redMaterial);
		Sphere sphere2 = new Sphere(new Vector3(9, 6f, 0), 3f, blueMaterial);
		Sphere sphere3 = new Sphere(new Vector3(0, 6f, 0), 1f, redMaterial);
		
		scene.addGeometricObject(sphere);
		scene.addGeometricObject(sphere2);
		scene.addGeometricObject(sphere3);
		//scene.addGeometricObject(sphere4);
		scene.addGeometricObject(sphere5);
		
		Graphics g = frame.getGraphics();
		
		long startTime = System.currentTimeMillis();
		
		while(true) {
			long currentTime = System.currentTimeMillis();
			if(currentTime - startTime >= 1000/targetFPS) {
				camera.update(inputController);
				Image image = tracer.renderScene(camera, scene);
				g.drawImage(image, 0, 0, width, height, null);
				long fpsTime = currentTime-startTime;
				int fps = (int) (1000/fpsTime);
				//System.out.println("FPS:"+fps);
				
				startTime = System.currentTimeMillis();
			}
		}
	}
	
}
