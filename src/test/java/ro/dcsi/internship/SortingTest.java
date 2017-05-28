package ro.dcsi.internship;

 
import org.junit.Assert;
import org.junit.Test;

public class SortingTest {
	@Test
	public void test(){
	int[] x = Sorter.sort(5,2,5,6,77);
		Assert.assertNotNull(x);
		for(int i =0; i<x.length-1; i++){
			Assert.assertTrue(x[i]<=x[i+1]);
		}
	
	}

}