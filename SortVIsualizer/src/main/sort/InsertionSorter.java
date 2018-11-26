package main.sort;

public class InsertionSorter extends Sorter {

	public InsertionSorter(int[] array) {
		super(array);
		name = "Insertion Sort";
	}

	@Override
	public void update() {

		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			playSound(key, 1);
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				playSound(array[j],1);
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
