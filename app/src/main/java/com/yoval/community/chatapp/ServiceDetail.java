package com.yoval.community.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.yoval.community.model.Service;
import com.yoval.community.model.User;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Yovanna on 2017-04-16.
 */

public class ServiceDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_servicedetail);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_servicedetail);
    setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String serviceId  = "", serviceCategory ="";
        if (bundle != null) {
            serviceId = bundle.getString("serviceKey");
            serviceCategory = bundle.getString("category");
        }

    populateService(serviceCategory, serviceId);


    Button buttonCancel = (Button) findViewById(R.id.cancelbutton_servicedetail);
    buttonCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });


    Button buttonHire = (Button) findViewById(R.id.hirebutton_servicedetail);
    buttonHire.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Toast.makeText(ServiceDetail.this,
                    "Votre solicitud a été envoyé", Toast.LENGTH_LONG).show();

            finish();

    }
    });

    }

    void populateService(String serviceCategory, String serviceId){


        final EditText dateFrom = (EditText) this.findViewById(R.id.servicedetail_datefrom);
        final EditText dateTo = (EditText) this.findViewById(R.id.servicedetail_dateto);
        final EditText timeFrom = (EditText) this.findViewById(R.id.servicedetail_timefrom);
        final EditText timeTo = (EditText) this.findViewById(R.id.servicedetail_timeto);
        final EditText title = (EditText) this.findViewById(R.id.servicedetail_title);
        final EditText description = (EditText) this.findViewById(R.id.servicedetail_description);
        final EditText price = (EditText) this.findViewById(R.id.servicedetail_price);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final SimpleDateFormat timeF = new SimpleDateFormat("HH:mm");


        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference().child("services").child(serviceCategory).child(serviceId).getRef();


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {

                Service service = snap.getValue(Service.class);
                title.setText(service.getTitle());
                description.setText(service.getDetail());
                price.setText(String.valueOf(service.getPrice()));
                dateFrom.setText(sdf.format(service.getFromDate()));
                timeFrom.setText(timeF.format(service.getFromDate()));
                dateTo.setText(sdf.format(service.getToDate()));
                timeTo.setText(timeF.format(service.getToDate()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        }
}

