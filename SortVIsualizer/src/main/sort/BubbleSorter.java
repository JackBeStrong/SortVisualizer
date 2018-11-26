package main.sort;


public class BubbleSorter extends Sorter {
	
	private int delay = 1;
	
	public BubbleSorter(int[] array) {
		super(array);
		name = "Bubble Sort";
	}

	@Override
	public void update() {
		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			for (int j = length - 1; j > i; j--) {
				playSound(array[j],delay);
				if (array[j - 1] > array[j]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
	}

}
