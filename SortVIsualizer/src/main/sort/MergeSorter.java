package main.sort;

public class MergeSorter extends Sorter {
	
	private int delay = 5;
	
	public MergeSorter(int[] array) {
		super(array);
		name = "Merge sort. Delay = " + delay + "ms";
	}

	public int[] sort(int[] array, int start, int finish) {

		if (start >= finish) {
			return array;
		}
		else {
			int divide = (start + finish) / 2;
			sort(array, start, divide);
			sort(array, divide + 1, finish);
			return merge(array, start, divide, finish);
		}

	}

	public int[] merge(int[] array, int start, int divide, int finish) {

		int[] left = new int[divide - start + 1];
		int[] right = new int[finish - divide];
		for (int i = 0; i < left.length; i++) {
			left[i] = array[start + i];
		}

		for (int i = 0; i < right.length; i++) {
			right[i] = array[divide + 1 + i];
		}

		int leftIndex = 0;
		int rightIndex = 0;

		for (int i = start; i <= finish; i++) {
			if (leftIndex >= left.length && rightIndex >= right.length) {
				break;
			}
			if (leftIndex >= left.length && rightIndex < right.length) {
				array[i] = right[rightIndex];
				playSound(right[rightIndex], delay);
				rightIndex++;
				continue;
			}
			if (leftIndex < left.length && rightIndex >= right.length) {
				array[i] = left[leftIndex];
				playSound(left[leftIndex], delay);
				leftIndex++;
				continue;
			}

			if (left[leftIndex] <= right[rightIndex]) {
				array[i] = left[leftIndex];
				playSound(left[leftIndex], delay);
				leftIndex++;
				continue;
			}
			if (left[leftIndex] > right[rightIndex]) {
				array[i] = right[rightIndex];
				playSound(right[rightIndex], delay);
				rightIndex++;
				continue;
			}

		}
		return array;

	}

	@Override
	public void update() {
		sort(array, 0, array.length - 1);
	}
}
