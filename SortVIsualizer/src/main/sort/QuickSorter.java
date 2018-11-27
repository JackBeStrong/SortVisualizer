package main.sort;

public class QuickSorter extends Sorter {

	private int delay = 5;

	public QuickSorter(int[] array) {
		super(array);
		name = "Quick sort. Delay = " + delay + "ms";
	}

	@Override
	public void update() {
		sort(0, array.length - 1);
	}

	public void sort(int start, int finish) {

		if (array.length > 0) {

			int key = array[(start + finish) / 2];
			int left = start;
			int right = finish;
			while (left < right) {

				while (array[left] < key) {
					playSound(array[left],delay);
					left++;
				}
				while (array[right] > key) {
					playSound(array[right],delay);
					right--;
				}
				if (left <= right) {
					int temp = array[left];
					playSound(array[left],delay);
					array[left] = array[right];
					playSound(array[right],delay);
					array[right] = temp;
					left++;
					right--;
				}
			}
			if (right > start) {
				sort(start, right);
			}
			if (finish > left) {
				sort(left, finish);
			}
		}

	}

}
