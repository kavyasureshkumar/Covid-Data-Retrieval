package hashes;

import java.io.IOException; 
import java.util.Scanner;

public class CovidData {
	public static void main(String[] args) throws IOException {
		// to get the input from the user for date and country
		Covid19 c = new Covid19();
		// this function will insert the data into AVLtrees and hashlist
		final long startTimeI = System.currentTimeMillis(); // start time of insertion
		c.insertall();
		final long endTimeI = System.currentTimeMillis(); // end time of insertion
		System.out.println("Total creation time: " + (endTimeI - startTimeI) / 1000.0);
		System.out.println("If details are required for all countries/date, enter 'All'");
		Scanner sc = new Scanner(System.in);
		System.out.println("If not, enter the name of a specific country: ");
		String coun = sc.nextLine();
		System.out.println("Enter the date for which details are required in dd-mm-yy: ");
		String date = sc.nextLine();
		String a = "All";
		final long startTime = System.currentTimeMillis(); // start time of getdata
		if (coun.equals(a) && !date.contentEquals(a)) //checks if country = "All"
		{
			//gets data for all countries given a date
			c.getallc(date);
		} else if (date.equals(a) && !coun.contentEquals(a)) //checks if date = "All"
		{
			c.getalld(coun);
		} else if (coun.equals(a) && date.equals(a)) //checks if both = "All"
		{
			c.getall();
		} else {
			c.getdata(coun, date);
		}
		final long endTime = System.currentTimeMillis(); // after getdata is executed
		System.out.println("Total execution time: " + (endTime - startTime) / 1000.0);
		// the time the program runs
		sc.close();
	}
}
