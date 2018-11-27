package main.sort;

public class InsertionSorter extends Sorter {
	
	private int delay = 1;
	
	public InsertionSorter(int[] array) {
		super(array);
		name = "Insertion sort. Delay = " + delay + "ms";
	}

	@Override
	public void update() {

		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			playSound(key, delay);
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				playSound(array[j],delay);
				int temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
				j--;
			}
		}
		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			playSound(key, 5);

		}
	}

}
