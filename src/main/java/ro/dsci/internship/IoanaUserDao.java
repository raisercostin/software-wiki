package ro.dsci.internship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//Use GabrielUserDao instead
@Deprecated
public class IoanaUserDao implements UserDao {
	@Override
	public List<User> readUsers(String locatie) {
		List<User> lista = new ArrayList<User>();
		try (FileReader fisier = new FileReader(locatie); BufferedReader br = new BufferedReader(fisier)) {
			// ptr prima linie ,cu numele coloanelor
			String line = br.readLine();
			String[] numeColoane = line.split(",");

			// ar trebui sa si dea seama a cata coloana e cu
			// numele,prenumele,email ca
			// sa le pun invers numerele jos la add.
			int nrId = 0;
			int nrUsername = 0;
			int nrFirstname = 0;
			int nrLastname = 0;
			int nrEmail = 0;

			for (int i = 0; i < numeColoane.length; i++) {
				if (numeColoane[i].equals("Id")) {
					nrId = i;
					System.out.println("Id e pe  a" + nrId + "a coloana");
				}
				if (numeColoane[i].equals("Name")) {
					nrUsername = i;
					System.out.println("numele e pe  a" + nrUsername + "a coloana");
				}
				if (numeColoane[i].equals("Firstname")) {
					nrFirstname = i;
					System.out.println("prenumele e pe  a" + nrFirstname + "a coloana");
				}
				if (numeColoane[i].equals("Lastname")) {
					nrLastname = i;
					System.out.println("prenumele e pe  a" + nrLastname + "a coloana");
				}
				if (numeColoane[i].equals("Email")) {
					nrEmail = i;
					System.out.println("email e pe  a" + nrEmail + "a coloana");
				}
			}

			// ptr urmatoarele linii, cu atributele

			line = br.readLine();
			while (line != null) {
				String[] atribute = line.split(",");
				User usernou = new User(atribute[nrId], atribute[nrUsername], atribute[nrFirstname],
						atribute[nrLastname], atribute[nrEmail]);
				lista.add(usernou);
				line = br.readLine();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		for (int i = 0; i < lista.size(); i++) {

			System.out.println(lista.get(i));
		}
		return lista;
	}

	// interfata medota 2
	@Override
	public void writeUsers(List<User> users, String locatie) {
		try {
			FileWriter a = new FileWriter(locatie, true);
			BufferedWriter b = new BufferedWriter(a);
			PrintWriter c = new PrintWriter(b);
			// c.println();
			User usernou = users.get(0);
			String atribute[] = usernou.toString().split(",");

			c.println(
					atribute[0] + " , " + atribute[1] + ", " + atribute[2] + " , " + atribute[3] + " , " + atribute[4]);
			c.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}