package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import main.graphics.Screen;

public class Main implements Runnable {

	public static int width = 1280;
	public static int height = 600;

	private JFrame frame;
	private Canvas canvas;
	private boolean running = false;
	private BufferStrategy bs;
	private BufferedImage image;
	private int[] pixels;
	private Screen screen;

	public Main() {
		frame = new JFrame("Sort Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		canvas = new Canvas();
		canvas.setSize(width, height);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		initialize();
	}

	@Override
	public void run() {
		while (running) {
			render();
		}
	}

	private void initialize() {
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) (image.getRaster().getDataBuffer()))
				.getData();
		screen = new Screen(width, height);
		running = true;
		Thread renderingThread = new Thread(this);
		Thread updateThread = new Thread(screen);
		renderingThread.start();
		updateThread.start();
	}

	private synchronized void render() {
		Graphics g = bs.getDrawGraphics();

		screen.render();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		g.drawImage(image, 0, 0, width, height, null);
		g.setColor(Color.white);
		g.drawString(screen.currentSorterName(), 5, 25);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Main();
	}

	// quality of life methods
	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
