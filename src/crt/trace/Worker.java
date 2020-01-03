package crt.trace;

import java.awt.image.BufferedImage;

import crt.shader.Shader;

public class Worker extends Thread{
	
	Camera camera;
	Scene scene;
	BufferedImage image;
	int startX, startY, width, height;

	public Worker(Camera camera, Scene scene, BufferedImage image, int startX, int startY, int width, int height) {
		this.camera = camera;
		this.scene = scene;
		this.image = image;
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void run() {
		for(int x = startX; x < width; x++) {
			for(int y = startY; y < height; y++) {
				Intersect intersect = scene.intersect(camera.generateCameraRay(width, height, x, y), 1);
				int color = Shader.genColorMultiBounceShadeDistance(intersect);
				image.setRGB(x, y, color);
			}
		}
	}

}
