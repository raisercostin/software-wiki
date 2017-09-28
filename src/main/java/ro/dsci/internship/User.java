package ro.dsci.internship;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;





public class User
	{
		private String nume;

		private String prenume;

		private String job;

		private String locatie;



		// constructor

		public User(String nume, String prenume, String job, String locatie)
			{

				setNume(nume);
				setPrenume(prenume);
				setJob(job);
				setLocatie(locatie);

			}// end constructor



		// getteri si setteri
		public void setNume(String nume)
			{

				this.nume = nume;
			}



		public void setPrenume(String prenume)
			{

				this.prenume = prenume;
			}



		public void setJob(String job)
			{

				this.job = job;
			}



		public void setLocatie(String locatie)
			{

				this.locatie = locatie;
			}



		public String getNume()
			{

				return this.nume;
			}



		public String getPrenume()
			{

				return this.prenume;
			}



		public String getJob()
			{

				return this.job;
			}



		public String getLocatie()
			{

				return this.locatie;
			}
		// end getteri si setteri



		// read
		public String read()
			{

				String numeFisier = "fisier.csv";
				File obiectFisier = new File(numeFisier);
				// cream un obiect de tip file pe care l pasam in constructorul lui scanner

				StringBuffer sb = new StringBuffer();
				try
					{
						Scanner ccz = new Scanner(obiectFisier);
						// cream un obiect de tip scanner ptr a lua inputul/fisierul csv
						while (ccz.hasNext()) // cat timp inca mai citeste ceva din fisier
							{
								// afisam ce a citit
								String citit = ccz.nextLine();
								sb.append(citit).append(" \n");
								System.out.println(sb.toString());
							}

					} catch (Exception e)

					{
						e.printStackTrace();
					}
				return sb.toString();
			}// end read



		public void write(String filePath)
			{

				String Nume = getNume();
				String Prenume = getPrenume();
				String Job = getJob();
				String Locatie = getLocatie();

				try
					{
						FileWriter a = new FileWriter(filePath, true);
						BufferedWriter b = new BufferedWriter(a);
						PrintWriter c = new PrintWriter(b);

						// scriem intr un fisier
						// punand la sfarsitul fisierului ce am scris/nu stergand tot si scriind peste
						// punem ce vrem sa scriem intr-un buffer si
						// facem un obiect cu care sa putem println-ui ce trebuie
						c.println(Nume + " , " + Prenume + ", " + Job + "," + Locatie);
						c.close();

					}

				catch (Exception e)
					{
						throw new RuntimeException(e);
					}

			}// end write
	}// end class User
