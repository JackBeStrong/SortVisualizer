package main.sort;

import java.util.Stack;

import main.Main;

public class ShellSorter extends Sorter {

	private int delay = 5;

	public ShellSorter(int[] array) {
		super(array);
		name = "Shell sort. Delay = " + delay + "ms";
	}

	@Override
	public void update() {
		Stack<Integer> gaps = new Stack<Integer>();

		int gap = 1;
		int k = 0;

		gaps.push(gap);

		while (gap < array.length) {
			gap = (int) Math.pow(4, k + 1) + (int) Math.pow(2, k) * 3 + 1;
			if (gap < array.length) {
				gaps.push(gap);
			}
			k++;
		}

		while (!gaps.empty()) {
			int currentGap = gaps.pop();
			for (int i = currentGap ; i < array.length; i++) {

				int key = array[i];
				playSound(key, delay);
				int j = i;
				while (j >= currentGap && array[j - currentGap] > key) {
					int temp = array[j - currentGap];
					playSound(array[j], delay);
					array[j - currentGap] = array[j];
					array[j] = temp;
					j -= currentGap;
				}
			}
		}
		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			playSound(key, 5);

		}
	}

}
