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

	static int width = 512;
	static int height = 512;
	
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setTitle("CTR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setEnabled(true);
		
		Tracer tracer = new Tracer(width, height);
		Camera camer = new Camera(new Vector3(0, 0, -10));
		Scene scene = new Scene();
		
		Material redMaterial = new Material(255, 0, 0, 0.1f, 0.1f, 0.1f);
		Material greenMaterial = new Material(0, 255, 0, 0.1f, 0.1f, 0.1f);
		Material blueMaterial = new Material(0, 0, 255, 0.1f, 0.1f, 0.1f);
		
		Sphere sphere = new Sphere(new Vector3(3, 0f, 0), 1f, greenMaterial);
		Sphere sphere2 = new Sphere(new Vector3(0, 0f, 0), 3f, blueMaterial);
		Sphere sphere3 = new Sphere(new Vector3(-3, 0f, 0), 1f, redMaterial);
		
		scene.addGeometricObject(sphere);
		scene.addGeometricObject(sphere2);
		scene.addGeometricObject(sphere3);
		
		Graphics g = frame.getGraphics();
		
		while(true) {
		Image image = tracer.renderScene(camer, scene);
		g.drawImage(image, 0, 0, width, height, null);
		
		System.out.println("Done!");
		}
	}
	
}
