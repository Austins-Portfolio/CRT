package crt.trace;

import java.awt.Image;
import java.awt.image.BufferedImage;

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
				Intersect intersect = scene.intersect(camera.generateCameraRay(width, height, x, y), 0);
				int color = Shader.genColor(intersect);
				image.setRGB(x, y, color);
			}
		}
		
		return image;
	}
	
}
