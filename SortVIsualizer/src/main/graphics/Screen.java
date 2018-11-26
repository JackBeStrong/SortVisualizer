package main.graphics;

import java.util.Random;

import main.sort.BubbleSorter;
import main.sort.InsertionSorter;
import main.sort.MergeSorter;
import main.sort.SelectionSorter;
import main.sort.Sorter;

public class Screen implements Runnable {

	private Sorter[] sorters;
	private Sorter currentSorter;
	public int[] pixels;
	public int[] array;
	private int width;
	private int height;
	private int xSize = 5;
	private int ySize = 2;

	public Screen(int width, int height) {
		this.pixels = new int[width * height];
		this.width = width;
		this.height = height;
		array = new int[256];
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
		shuffle(array);
		sorters = new Sorter[4];
		sorters[0] = new BubbleSorter(array);
		sorters[1] = new InsertionSorter(array);
		sorters[2] = new MergeSorter(array);
		sorters[3] = new SelectionSorter(array);
	}

	public void render() {

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = 0x000000;
			}

		}
		for (int i = 0; i < array.length; i++) {
			renderBar(i, array[i]);

		}
	}

	public synchronized void update() {

		for (int i = 0; i < sorters.length; i++) {
			currentSorter = sorters[i];
			currentSorter.update();
			if (i != sorters.length - 1) {
				shuffle(array);
			}
		}
	}

	private void renderBar(int location, int size) {
		int xStart = (location - 1) * xSize;
		int xFinish = location * xSize;
		int yStart = height - size * ySize;
		int yFinish = height;
		for (int y = yStart; y < yFinish; y++) {
			for (int x = xStart; x < xFinish - 1; x++) {
				pixels[x + y * width] = 0xFF6500 + size - 1;
			}
		}
	}

	private void shuffle(int[] array) {

		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int nextRandomIndex = random.nextInt(i + 1);
			int temp = array[nextRandomIndex];
			array[nextRandomIndex] = array[i];
			array[i] = temp;
		}
	}

	// get the name of current sorter
	public String currentSorterName() {
		return currentSorter.name;
	}

	@Override
	public void run() {
		update();
	}

}
