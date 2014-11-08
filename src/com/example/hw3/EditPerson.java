////////////////////////////////////////////////////////////////////////////////
//                                                                            //
// This program will allow the user to edit a current contact after displaying//
// it in the specified fields.The action bar has button for deleting and      // 
// updating the contact.													  //
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditPerson extends Activity {

	private PersonList p;
	private int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		// Coloring the action bar according to material design standards
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4081")));

		// Getting the details from other activity
		p = (PersonList) getIntent().getSerializableExtra("19101992");
		if (p == null) {
			p = new PersonList();
		}
		pos = getIntent().getExtras().getInt("pos");
		Person temp = p.get(pos);

		// Initializing all the editing fields
		EditText fname = (EditText) findViewById(R.id.FName);
		EditText lname = (EditText) findViewById(R.id.LName);
		EditText phone = (EditText) findViewById(R.id.Phone);
		EditText email = (EditText) findViewById(R.id.Email);

		// Setting all the details
		fname.setText(temp.getFname());
		lname.setText(temp.getLname());
		phone.setText(temp.getPhone());
		email.setText(temp.getEmail());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.editmenu, menu);
		return true;
	}

	// Providing functionality to the button on the action bar
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_add) {
			saveContact();
		}
		if (id == R.id.action_delete) {

			final Intent mIntent = new Intent(this, MainActivity.class);
			final Bundle mBundle = new Bundle();

			// creates a dialog box that displays confirmation for deletion
			new AlertDialog.Builder(this)
					.setTitle("Delete entry")
					.setMessage("Are you sure you want to delete this entry?")
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with delete
									p.remove(pos);
									mBundle.putSerializable("19101992", p);
									mIntent.putExtras(mBundle);

									startActivity(mIntent);
									finish();

								}
							})
					.setNegativeButton(android.R.string.no,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// do nothing
									mBundle.putSerializable("19101992", p);
									mIntent.putExtras(mBundle);

									startActivity(mIntent);
									finish();

								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Saves the user details to the bundle for passing to other activities
	public void saveContact() {
		EditText fname = (EditText) findViewById(R.id.FName);
		EditText lname = (EditText) findViewById(R.id.LName);
		EditText phone = (EditText) findViewById(R.id.Phone);
		EditText email = (EditText) findViewById(R.id.Email);

		Intent mIntent = new Intent(this, MainActivity.class);
		Bundle mBundle = new Bundle();

		String t = fname.getText().toString();
		if ((t == "") || (t == " ") || (t.isEmpty()==true)) {
			Toast.makeText(getApplicationContext(),
					"Enter First Name and then save", Toast.LENGTH_LONG).show();
		} else {
			p.set(new Person(fname.getText().toString(), lname.getText()
					.toString(), phone.getText().toString(), email.getText()
					.toString()), pos);
			mBundle.putSerializable("19101992", p);
			mIntent.putExtras(mBundle);

			startActivity(mIntent);
			finish();
		}
	}
}
