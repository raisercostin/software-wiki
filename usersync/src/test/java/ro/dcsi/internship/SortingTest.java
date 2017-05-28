package ro.dcsi.internship;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class SortingTest {
	@Test
	public void test() {
		int[] x = Sorting.sort(5, 25, 6, 4, 2, 5, 3, 2);
		Assert.assertNotNull(x);
		Assert.assertEquals(8, x.length);
		for (int i = 0; i < x.length-1; i++) {
			Assert.assertTrue(x[i]<=x[i+1]);			
		}
		Assert.assertEquals(2,x[0]);
	}
}
