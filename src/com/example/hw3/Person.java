////////////////////////////////////////////////////////////////////////////////
//                                                                            //
// This program will provide a basic framework for managing contact info of   //
// the user.                                                                  //
//                                                                            //
// Name: Nagabharan Nagendran                                                 //
// Net ID: nxn141730                                                          //
// Date created: 11.03.2014                                                   //
// Purpose: Assignment                                                        //
// Class: CS6301.022 User Interface Design                                    //
////////////////////////////////////////////////////////////////////////////////


package com.example.hw3;

import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable {

	private String fname, lname, phone, email;
	private static final long serialVersionUID = 19101992;

	//Getter and Setter functions
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person(String fname, String lname, String phone, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
	}

	String getName() {
		return fname + " " + lname;
	}

	@Override
	public int compareTo(Person another) {
		// TODO Auto-generated method stub
		return getName().compareTo(another.getName());
	}

}
