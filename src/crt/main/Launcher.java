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

	static int width = 1024;
	static int height = 1024;
	
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setTitle("CTR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setEnabled(true);
		
		Tracer tracer = new Tracer(width, height);
		Camera camer = new Camera(new Vector3(0, -2, -10));
		Scene scene = new Scene();
		
		Material redMaterial = new Material(255, 0, 0, 0.1f, 0.1f, 0.1f);
		Material greenMaterial = new Material(0, 255, 0, 0.1f, 0.1f, 0.1f);
		Material blueMaterial = new Material(0, 0, 255, 0.1f, 0.1f, 0.1f);
		
		Sphere sphere = new Sphere(new Vector3(0, 0f, 3), 1f, greenMaterial);
		Sphere sphere2 = new Sphere(new Vector3(6, -5f, 4), 3f, blueMaterial);
		Sphere sphere3 = new Sphere(new Vector3(2, 0f, 3), 1f, redMaterial);
		
		scene.addGeometricObject(sphere);
		scene.addGeometricObject(sphere2);
		scene.addGeometricObject(sphere3);
		
		Image image = tracer.renderScene(camer, scene);
		
		Graphics g = frame.getGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		
		System.out.println("Done!");
	}
	
}
