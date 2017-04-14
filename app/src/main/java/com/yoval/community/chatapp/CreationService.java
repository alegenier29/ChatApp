package com.yoval.community.chatapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yoval.community.model.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.logging.Level.parse;

/**
 * Created by Alejandro on 2017-04-05.
 */

public class CreationService extends AppCompatActivity {
    String dateSelected = "from";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creationservice);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_creationdeservice);
        setSupportActionBar(toolbar);


        final EditText dateFrom = (EditText) this.findViewById(R.id.creationservice_datefrom);
        final EditText dateTo = (EditText) this.findViewById(R.id.creationservice_dateto);
        final EditText timeFrom = (EditText) this.findViewById(R.id.creationservice_timefrom);
        final EditText timeTo = (EditText) this.findViewById(R.id.creationservice_timeto);
        final EditText title = (EditText) this.findViewById(R.id.creationservice_title);
        final EditText description = (EditText) this.findViewById(R.id.creationservice_description);
        final EditText prix = (EditText) this.findViewById(R.id.creationservice_price);

        final Calendar myCalendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeF = new SimpleDateFormat("HH:mm");
        String time = timeF.format(myCalendar.getTime());


        dateFrom.setText(sdf.format(new Date()));
        dateTo.setText(sdf.format(new Date()));
        timeFrom.setText(time);
        timeTo.setText(time);


        dateFrom.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                dateSelected = "from";
                return false;
            }
        });

        dateTo.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                dateSelected = "to";
                return false;
            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {

                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA_FRENCH);

                String dateText = sdf.format(myCalendar.getTime());

                if (dateSelected == "from")
                    dateFrom.setText(dateText);
                else
                    dateTo.setText(dateText);
            }

        };

        dateFrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreationService.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreationService.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        timeFrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreationService.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeFrom.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Choisissez l'heure");
                mTimePicker.show();

            }
        });

        timeTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreationService.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeTo.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Choisissez l'heure");
                mTimePicker.show();

            }
        });

        Button buttonCancel = (Button) findViewById(R.id.cancelbutton_creationservice);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button buttonPublish = (Button) findViewById(R.id.publishbutton_creationservice);
        buttonPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Editable serviceTitle = title.getText();
                Editable serviceDescription = description.getText();
                Editable servicePrice = prix.getText();
                Editable serviceDateFrom = dateFrom.getText();
                Editable serviceDateTo = dateTo.getText();
                Editable serviceTimeTo = timeFrom.getText();
                Editable serviceTimeFrom = timeTo.getText();


                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                Date serDateFrom= new Date();
                Date serDateTo=new Date();

                try {
                    serDateFrom = df.parse(serviceDateFrom.toString() + " " + serviceTimeFrom.toString());
                    serDateTo = df.parse(serviceDateTo.toString() + " " + serviceTimeTo.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                if (!(serviceTitle.length() == 0) && !(serviceDescription.length() == 0) && !(servicePrice.length() == 0)) {
                    //Store service in database
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                     DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                    if (firebaseUser != null) {
                        String userId = firebaseUser.getUid();

                        Service service = new Service(userId, serviceTitle.toString(),serviceDescription.toString(),
                                Integer.parseInt(servicePrice.toString()),serDateFrom, serDateTo, new Date());

                        String category = findCategorie();

                        if(category != "") {
                            databaseReference.child("services").child(category).push().setValue(service);
                        }
                        else
                        {
                            Toast.makeText(CreationService.this,
                                    "Erreur: Catégorie n'éxiste pas", Toast.LENGTH_LONG).show();

                        }

                        Toast.makeText(CreationService.this,
                                "Votre service a été publié", Toast.LENGTH_LONG).show();

                        finish();
                    }

                }
                else {
                    //Fill in required fields
                    Toast toast = Toast.makeText(CreationService.this,
                            "Remplisez les champs obligatoires", Toast.LENGTH_LONG);

                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL,0,100);
                    toast.show();
                    }



        }

            private String findCategorie() {
                Intent intent= getIntent();
                Bundle bundle = intent.getExtras();

                if(bundle!=null)
                {
                   String category = (String) bundle.get("Category");
                   String tableName= "";
                    switch (category)
                   {
                       case "Reparations":
                            tableName="Reparations";
                            break;
                       case "Lesson de musique":
                           tableName="Music";
                           break;
                       case "Aide aux aînes":
                            tableName="Elder";

                       case "Animaux de compagnie":
                           tableName="Pets";
                           break;
                       case "Securité du quartier":
                           tableName="Security";
                           break;
                       case "Preparer des examens":
                           tableName="Review";
                           break;
                       case "Partage de nourriture":
                           tableName =  "Food";
                           break;
                       case  "Covoiturage":
                           tableName="Carpooling";
                           break;
                       case "Faire des sports":
                           tableName="Sports";
                           break;
                       case "Nettoyage de maison":
                           tableName="Cleaning";
                           break;
                       case "Autre":
                       tableName= "Other";
                       break;


                   }
                    return tableName;
                }
                return "";
            }
        });
    }
}
