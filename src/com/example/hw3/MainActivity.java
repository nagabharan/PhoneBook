////////////////////////////////////////////////////////////////////////////////
//                                                                            //
// This program will create a list of all contacts present in the mycontacts  //
// text file after reading from it. It provides button on action bar to add a // 
// new contact.We use a custom adapter for displaying all the contact details.//
//                                                                            //
// Name: Nagabharan Nagendran                                                 //
// Net ID: nxn141730                                                          //
// Date created: 11.03.2014                                                   //
// Purpose: Assignment                                                        //
// Class: CS6301.022 User Interface Design                                    //
////////////////////////////////////////////////////////////////////////////////


package com.example.hw3;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	private PersonList c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Coloring the action bar according to material design standards
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
		
		lv = (ListView) findViewById(R.id.listView1);

		//Getting old entries from the file
		c = (PersonList) getIntent().getSerializableExtra("19101992");
		if (c == null) {
			c = new PersonList();
			c.readFromFile(getFilesDir());
		}

		//Initializing the contact list
		CustomAdapter adapter = new CustomAdapter(this, c.getList());
		lv.setAdapter(adapter);

		//When a contact is selected that contact can be edited
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this, EditPerson.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("19101992", c);
				intent.putExtra("pos", position);
				intent.putExtras(mBundle);

				startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//Providing function for adding contacts
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_add) {
			Intent intent = new Intent(this, AddPerson.class);
			Bundle mBundle = new Bundle();
			mBundle.putSerializable("19101992", c);
			intent.putExtras(mBundle);

			startActivity(intent);

		}
		return super.onOptionsItemSelected(item);
	}

	//Writing all the contacts to the file when the application goes out of scope
	@Override
	public void onPause() {
		super.onPause();
		Log.e("Writing to file onPause", getFilesDir().toString());
		c.writeToFile(getFilesDir());
	}
}
