package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class User {

	public String name;
	public int age;
	public double salary;
	public String details;
	
	@Override
	public String toString()
	{
		return name+" "+age+" "+salary+" "+details;
	}

}
