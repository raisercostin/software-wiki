package ro.dcsi.internship;

import static org.junit.Assert.*;

import ro.dcsi.internship.CSVUtils;
import org.hamcrest.core.IsNull;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Test {

	@org.junit.Test
	public void test_faraGhilimele() {

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
	public void test_ghilimele() {

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
