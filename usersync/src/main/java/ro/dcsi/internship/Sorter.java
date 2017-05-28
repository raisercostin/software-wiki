package ro.dcsi.internship;

import java.util.Arrays;

public class Sorter {
	public static int[] sort(int... x) {
		Arrays.sort(x);
		x[x.length-1]=-1;
		return x;
	}
}