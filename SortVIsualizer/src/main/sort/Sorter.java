package main.sort;

public abstract class Sorter {
	
	public int[] array;
	
	public Sorter(int[] array) {
		this.array = array;
	}

	public abstract void update();
	
	
}
