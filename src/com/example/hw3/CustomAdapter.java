////////////////////////////////////////////////////////////////////////////////
//                                                                            //
// This program will a contact by filling in the name and phone number. A	  //
// random color is assigned to each item in the list.						  // 
//                                                                            //
// Name: Nagabharan Nagendran                                                 //
// Net ID: nxn141730                                                          //
// Date created: 11.03.2014                                                   //
// Purpose: Assignment                                                        //
// Class: CS6301.022 User Interface Design                                    //
////////////////////////////////////////////////////////////////////////////////

package com.example.hw3;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Person> {

	String[] S = { "#F44336", "#607D8B", "#9C27B0", "#673AB7", "#4CAF50",
			"#2196F3", "#03A9F4", "#009688", "#795548", "#FF5722" };

	public CustomAdapter(Context context, ArrayList<Person> users) {
		super(context, 0, users);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Person p = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.listadapter, parent, false);
		}

		//Assigning random color for list item
		Random rnd = new Random();
		int color = Color.parseColor(S[rnd.nextInt(10)]);
		convertView.setBackgroundColor(color);

		TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
		TextView tvphNumber = (TextView) convertView.findViewById(R.id.tvPhone);

		tvName.setText(p.getFname() + " " + p.getLname());
		tvphNumber.setText(p.getPhone());

		return convertView;
	}
}