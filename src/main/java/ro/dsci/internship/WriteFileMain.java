package ro.dsci.internship;

public class WriteFileMain {

	public static void main(String[] args) {
		
		writefile z = new writefile();
		z.openFile();
		z.addUsers();
		z.closeFile();

	}

}
