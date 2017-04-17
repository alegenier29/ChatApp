package com.yoval.community.chatapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.yoval.community.model.ChatMessage;
import com.yoval.community.model.Service;

import java.util.Date;

import static com.yoval.community.chatapp.R.id.image;

public class Post extends AppCompatActivity {

    private FirebaseListAdapter<Service> adapter;
    private String tableName = null;
    private String serviceKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparations_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_post_repairs);
        ListView listOfServices = (ListView) findViewById(R.id.postList_reparations);
        setSupportActionBar(toolbar);


        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        tableName = Tools.findCategory(bundle); //Table name: exemple (reparation)
        // Load service list
        displayServicesList(tableName);

        listOfServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // DO something
                showDetailService(position);

            }


        });

    }

    private void showDetailService(int position) {

        Intent intent = new Intent(this, ServiceDetail.class);
        intent.putExtra("serviceKey", serviceKey);
        intent.putExtra("category", tableName);
        startActivity(intent);
    }

    private void displayServicesList(final String table) {
        ListView listOfServices = (ListView) findViewById(R.id.postList_reparations);
        adapter = new FirebaseListAdapter<Service>(this, Service.class,
                R.layout.postrow, FirebaseDatabase.getInstance().getReference("services/" + table)) {


            @Override
            protected void populateView(View v, Service model, int position) {
                // Get references to the views of Postrow.xml

                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                if (v == null) {
                    v = layoutInflater.inflate(R.layout.postrow, null);
                }

                TextView actualDate = (TextView) v.findViewById(R.id.service_date_published);
                ImageView actualImage= (ImageView) v.findViewById(R.id.avatar);
                TextView actualTitle = (TextView) v.findViewById(R.id.textUp);
                TextView descriptions = (TextView) v.findViewById(R.id.textDown);
                actualImage.setImageResource(R.drawable.avatar);
                // Set their text
                actualTitle.setText(model.getTitle());
                descriptions.setText(model.getDetail());
                actualDate.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getPublishedDate()));

                serviceKey = model.getServiceId();

            }
        };

        listOfServices.setAdapter(adapter);
    }


}
