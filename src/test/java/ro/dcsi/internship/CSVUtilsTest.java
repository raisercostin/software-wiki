package ro.dcsi.internship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.hamcrest.core.IsNull;

public class CSVUtilsTest {

	@org.junit.Test
	public void testFaraGhilimele() {
		String line = "Radu,Tudorache,18,roman,Romania";
		List<String> result = CSVUtils.parseLine(line);

		assertThat(result, IsNull.notNullValue());
		assertThat(result.size(), is(5));
		assertThat(result.get(0), is("Radu"));
		assertThat(result.get(1), is("Tudorache"));
		assertThat(result.get(2), is("18"));
		assertThat(result.get(3), is("roman"));
		assertThat(result.get(4), is("Romania"));

	}

	@org.junit.Test
	public void testGhilimele() {
		String line = "\"Radu\",\"Tudorache\",\"18\",\"roman\",\"Romania";
		List<String> result = CSVUtils.parseLine(line);

		assertThat(result, IsNull.notNullValue());
		assertThat(result.size(), is(5));
		assertThat(result.get(0), is("Radu"));
		assertThat(result.get(1), is("Tudorache"));
		assertThat(result.get(2), is("18"));
		assertThat(result.get(3), is("roman"));
		assertThat(result.get(4), is("Romania"));

	}

}
