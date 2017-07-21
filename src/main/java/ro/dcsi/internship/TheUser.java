package ro.dcsi.internship;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;

@SuppressWarnings("serial")
public class TheUser implements Serializable
{
	//private String id;
	@CsvBindByName
	private String name;
	//private String email;
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public TheUser(String name)
  {
	  this.name = name;
  }
  
	public TheUser()
  {
	  this.name = "NO_NAME";
  }
}
