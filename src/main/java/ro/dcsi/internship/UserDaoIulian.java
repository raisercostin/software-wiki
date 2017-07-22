package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.*;

public class UserDaoIulian implements UserDao
{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void writeUsers(String file, TheUser... users)
	{
		try
		{	
			//add users to a list
			List<TheUser> userList = new ArrayList<TheUser>();
			for (TheUser obj : users)
			{
				userList.add(obj);
			}
			
			String fileName = file + ".csv";
			Writer writer = new FileWriter(fileName);
			
			//writing to a .csv
			StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
			beanToCsv.write(userList);
			writer.close();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		catch (CsvDataTypeMismatchException e)
		{
			throw new RuntimeException(e);
		}
		catch (CsvRequiredFieldEmptyException e)
		{
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TheUser> readUsers(String file)
	{
		try
		{
			String fileName = file + ".csv";
		
			List<TheUser> userList = new CsvToBeanBuilder(new FileReader(fileName)).withType(TheUser.class).build().parse();
			return userList;
		}
		catch(IllegalStateException e)
		{
			throw new RuntimeException(e);
		}
		catch(FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

}
