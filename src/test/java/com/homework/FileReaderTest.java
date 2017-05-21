package com.homework;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void doATest() throws IOException {
		String destination = "HundredUsers.csv";
		String csvFile = "HundredUsers.csv";
		File file1 = new File(destination);
		File file2 = new File(csvFile);
		BufferedReader buffReader = new BufferedReader(new FileReader(destination));
		Scanner scanner = new Scanner(csvFile);
	}
}