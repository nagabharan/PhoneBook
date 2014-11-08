////////////////////////////////////////////////////////////////////////////////
//                                                                            //
// This program will perform File IO using the file mycontacts.txt            //
// Using the readfromFile() and writeToFile() function we write				  // 
// and read data from the file.                                               //
//                                                                            //
// Name: Nagabharan Nagendran                                                 //
// Net ID: nxn141730                                                          //
// Date created: 11.03.2014                                                   //
// Purpose: Assignment                                                        //
// Class: CS6301.022 User Interface Design                                    //
////////////////////////////////////////////////////////////////////////////////


package com.example.hw3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;

public class PersonList implements Serializable {
	private static final long serialVersionUID = 19101992;
	ArrayList<Person> p;

	public PersonList() {
		p = new ArrayList<Person>();
	}

	public ArrayList<Person> getList() {
		return this.p;
	}

	public void add(Person entry) {
		p.add(entry);
		sort();
	}

	//Reading all the contacts from the file
	public void readFromFile(File d) {
		String line = "";
		File f = new File(d, "mycontacts.txt");
		try {
			FileInputStream fis = new FileInputStream(f);
			DataInputStream in = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				String[] s = line.split(";");
				p.add(new Person(s[0], s[1], s[2], s[3]));
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//Writing all the contacts to the file
	public void writeToFile(File d) {
		String line = "";
		File f = new File(d, "mycontacts.txt");

		try {
			FileOutputStream fis = new FileOutputStream(f, false);
			for (Person i : p) {
				line = i.getFname() + ";" + i.getLname() + ";" + i.getPhone()
						+ ";" + i.getEmail() + "\n";
				fis.write(line.getBytes());
			}
			Log.e("Wrote to file", line);
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void remove(int i) {
		p.remove(i);
	}

	//Sorting all the contacts
	private void sort() {
		Collections.sort(p);
	}

	public Person get(int i) {
		return p.get(i);
	}

	public void set(Person c, int i) {
		this.p.set(i, c);
	}
}
