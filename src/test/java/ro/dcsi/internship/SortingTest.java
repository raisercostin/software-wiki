package ro.dcsi.internship;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class SortingTest {

	@Test
	public void test() {

		int[] result = Sorter.sort(5, 2, 5, 7, 2, 5);
		Assert.assertNotNull(result);
		Assert.assertEquals(6, result.length);
	}

}
