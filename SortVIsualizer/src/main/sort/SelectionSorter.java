package main.sort;

public class SelectionSorter extends Sorter {
	
	private int delay = 5;
	
	public SelectionSorter(int[] array) {
		super(array);
	}


	@Override
	public void update() {
		for (int i = 0; i < array.length - 1; i++) {
			int min = array[i];
			playSound(min,delay);
			int minIndex = i;
			for (int j = i; j < array.length; j++) {
				int current = array[j];
				playSound(current,delay);
				if (current < min) {
					min = current;
					minIndex = j;
				}
			}
			int temp = array[i];
			array[i] = min;
			array[minIndex] = temp;
		}
	}

}
