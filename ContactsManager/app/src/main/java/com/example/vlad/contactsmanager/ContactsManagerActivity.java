package com.example.vlad.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        Listener listener = new Listener();
        Button save_button = (Button) findViewById(R.id.save);
        Button cancel_button = (Button) findViewById(R.id.cancel);
        Button show_additional_field = (Button) findViewById(R.id.additional_fields);
        Button hide_additional_field = (Button) findViewById(R.id.hide_additional_fields);

        save_button.setOnClickListener(listener);
        cancel_button.setOnClickListener(listener);
        show_additional_field.setOnClickListener(listener);
        hide_additional_field.setOnClickListener(listener);

    }
    class Listener implements View.OnClickListener{

        public void onClick(View v){
            int id = v.getId();
            if(id == R.id.hide_additional_fields){
                Button hide_additional_field = (Button) findViewById(R.id.hide_additional_fields);
                Button show_additional_field = (Button) findViewById(R.id.additional_fields);
                LinearLayout ll = (LinearLayout)findViewById(R.id.optional);

                ll.setVisibility(LinearLayout.GONE);
                hide_additional_field.setVisibility(Button.GONE);
                show_additional_field.setVisibility(Button.VISIBLE);
            }

            if(id == R.id.additional_fields){
                Button hide_additional_field = (Button) findViewById(R.id.hide_additional_fields);
                Button show_additional_field = (Button) findViewById(R.id.additional_fields);
                LinearLayout ll = (LinearLayout)findViewById(R.id.optional);

                ll.setVisibility(LinearLayout.VISIBLE);
                show_additional_field.setVisibility(Button.GONE);
                hide_additional_field.setVisibility(Button.VISIBLE);
            }

            if(id == R.id.save){
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                EditText editText = (EditText)findViewById(R.id.name);
                String name = editText.getText().toString();

                editText = (EditText)findViewById(R.id.number);
                String phone = editText.getText().toString();

                editText = (EditText)findViewById(R.id.email);
                String email = editText.getText().toString();

                editText = (EditText)findViewById(R.id.address);
                String address = editText.getText().toString();

                editText = (EditText)findViewById(R.id.jobTitle);
                String jobTitle = editText.getText().toString();

                editText = (EditText)findViewById(R.id.company);
                String company = editText.getText().toString();

                editText = (EditText)findViewById(R.id.website);
                String website = editText.getText().toString();

                editText = (EditText)findViewById(R.id.im);
                String im = editText.getText().toString();
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (jobTitle != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                
                startActivity(intent);
            }

        }
    }
}
