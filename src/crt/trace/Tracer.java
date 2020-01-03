package crt.trace;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import crt.shader.Shader;

public class Tracer {

	int width,height;
	
	public Tracer(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Image renderScene(Camera camera, Scene scene) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Intersect intersect = scene.intersect(camera.generateCameraRay(width, height, x, y), 1);
				int color = Shader.genColorMultiBounceShadeDistance(intersect);
				image.setRGB(x, y, color);
			}
		}
		
		return image;
	}
	
	public Image renderScene(Camera camera, Scene scene, int split) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		ArrayList<Thread> Workers = new ArrayList<Thread>();
		
		for(int x = 0; x < width; x+=width/split) {
			for(int y = 0; y < height; y+=height/split) {
				Worker thread = new Worker(camera, scene, image, x, y, width, height);
				thread.start();
				Workers.add(thread);
			}
		}
		
		for(int i = 0;i < Workers.size();i++) {
			try {
				Workers.get(i).join();
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		return image;
	}
	
}
