package ro.dcsi.internship;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class SortingTest {

	@Test
	public void test() {
		int[] x = Sorter.sort(5, 2, 5, 7, 2, 5);
		Assert.assertNotNull(x);
		Assert.assertEquals(6, x.length);
		for (int i = 0; i < x.length - 1; i++) {
			Assert.assertTrue("i="+i+" "+x[i] + "<=" + x[i + 1] + " failed", x[i] <= x[i + 1]);
		}
	}
}
