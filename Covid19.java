package hashes;

import java.io.FileReader; 
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

public class Covid19 {
	// declaring array object for AVLTrees
	AVLTrees[] arr = new AVLTrees[230];
	int count = 0;
	String[] country = new String[300];
	int count1 = 0;
	String[] datee = new String[1000];
	// creating object for hashlist
	HashMap1 h = new HashMap1(230);

	public void countrylist() throws FileNotFoundException {
		String filename = "F:/covid_19_data.csv";
		Scanner sc = new Scanner(new File(filename));
		// sets the delimiter pattern
		sc.nextLine();
		String[] inter;
		while (sc.hasNext()) // returns a boolean value
		{
			String data;
			data = sc.nextLine();
			String data1[] = null;
			// since it's a csv file, delimiter is ,
			data1 = data.split(",");
			inter = data1[0].split("/|-");
			String datet = inter[0] + inter[1] + inter[2];
			// this will store all the countries in the country array
			if (Arrays.asList(country).contains(data1[1]) == false) {
				country[count++] = data1[1];
			}
			if (Arrays.asList(datee).contains(datet) == false) {

				datee[count1++] = datet;
			}

		}
		sc.close();
	}

	// this method is to
	public void insertall() throws IOException {
		countrylist();
		// this will create multiple AVL trees with nodes as date
		AVLTrees[] arrr = ConTrees(country);
		System.out.println("AVLTrees of countries are created");
		for (int i = 0; i < count; i++) {
			h.put(arrr[i].root.data, country[i]);
		}
	}

	public void getalld(String country1) {
		int comparison = h.get(country1);
		int check = 0;
		for (int i = 0; i < count; i++) {
			// if the current root node is equal to the root node of the given country
			if (comparison == arr[i].root.data) {
				check = i;
			}
		}

		for (int i = 0; i < count1; i++) {
			int hash = datee[i].hashCode() + country1.hashCode();
			TNode l;
			int con = 0;

			l = arr[check].find(hash);
			if (l == null) {
				System.out.println("No entry on the date: " + datee[i].substring(0,2)+"-"+datee[i].substring(2,4)+"-"+datee[i].substring(4,6));
			} else {
				con = con + 1;
				System.out.println("In " + country1 + " on " + l.date);
				System.out.println("The number of confirmed cases are " + l.confirmed);
				System.out.println("The number of deaths in are " + l.dead);
				System.out.println("The number of recovered cases in are " + l.recovered);
			}
		}
	}
	public void getall(){
		int tot_con = 0;
		int tot_dead = 0;
		int tot_rec = 0;
		for(int j=0;j<count1;j++){
			String datet=datee[j];
		// to split the date according to / or -
		TNode l;
		int con = 0;
		
		for (int i = 0; i < count; i++) {
			int hash = country[i].hashCode() + datet.hashCode();
			l = arr[i].find(hash);
			if (l == null) {
			} else {
				con = con + 1;
				System.out.println("In " + country[i] + " on " + datee[j].substring(0,2)+"-"+datee[j].substring(2,4)+"-"+datee[j].substring(4,6));
				tot_con = tot_con + l.confirmed;
				System.out.println("The number of confirmed cases: " + l.confirmed);
				tot_dead = tot_dead + l.dead;
				System.out.println("The number of deaths: " + l.dead);
				tot_rec = tot_rec + l.recovered;
				System.out.println("The number of recovered cases: " + l.recovered);
			}
		}
		}

	}

	public void getallc(String date) {
		int tot_con = 0;
		int tot_dead = 0;
		int tot_rec = 0;
		String[] inter;
		// to split the date according to / or -
		inter = date.split("/|-");
		TNode l;
		int con = 0;
		String datet = inter[0] + inter[1] + inter[2];
		for (int i = 0; i < count; i++) {
			int hash = country[i].hashCode() + datet.hashCode();
			l = arr[i].find(hash);
			if (l == null) {
				System.out.println("No entries found in " + country[i] + " on " + date);
			} else {
				con = con + 1;
				System.out.println("In " + country[i] + " on " + date);
				tot_con = tot_con + l.confirmed;
				System.out.println("The number of confirmed cases: " + l.confirmed);
				tot_dead = tot_dead + l.dead;
				System.out.println("The number of deaths: " + l.dead);
				tot_rec = tot_rec + l.recovered;
				System.out.println("The number of recovered cases: " + l.recovered);
			}
		}
		System.out.println("The number of countries with entries on " + date + ": " + con);
		System.out.println("Total number on " + date +":");
		System.out.println("Confirmed cases: " + tot_con + "\n" + "Recovered cases: " + tot_rec + "\n"
				+ "Deaths: " + tot_dead);
	}

	public void getdata(String Country, String date) throws IOException {

		// this will put all the keys and value in the hash list

		String[] inter;
		// to split the date according to / or -
		inter = date.split("/|-");
		TNode l;
		String datet = inter[0] + inter[1] + inter[2];
		// adding hashcode of date and country
		int hash = Country.hashCode() + datet.hashCode();
		// this will return the key which is root node for the country(value)
		int comparison = h.get(Country);
		for (int i = 0; i < count; i++) {
			// if the current root node is equal to the root node of the given country
			if (comparison == arr[i].root.data) {
				// find will return the node for the hashcode of country and date
				l = arr[i].find(hash);
				if (l == null) {
					System.out.println("No entries found on that "+date);
					break;
				}
				System.out
						.println("The number of confirmed cases in " + Country + " on " + date + " are " + l.confirmed);
				System.out.println("The number of deaths in " + Country + " on " + date + " are " + l.dead);
				System.out
						.println("The number of recovered cases in " + Country + " on " + date + " are " + l.recovered);
			}
		}

	}

	// sets the delimiter pattern
	public AVLTrees[] ConTrees(String country[]) throws IOException {
		String line;
		// inserting data in AVLtrees of all countries using loop
		for (int i = 0; i < count; i++) {
			String filename = "F:/covid_19_data.csv";
			BufferedReader br1 = new BufferedReader(new FileReader(filename));
			br1.readLine();
			arr[i] = new AVLTrees();
			while ((line = br1.readLine()) != null) // returns a boolean value
			{
				String data[] = new String[5];
				data = line.split(",");
				if (country[i].equals(data[1])) {
					String inter[] = data[0].split("/|-");
					String inter1 = inter[0] + inter[1] + inter[2];
					// inserting addition of hashcode of date and country as node in the trees
					arr[i].insert(inter1.hashCode() + data[1].hashCode(), Integer.parseInt(data[2]),
							Integer.parseInt(data[3]), Integer.parseInt(data[4]), data[0]);
				}

			}
			br1.close();
		}

		return arr;
	}
}
