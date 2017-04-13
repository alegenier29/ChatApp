package com.yoval.community.chatapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;

import static com.yoval.community.chatapp.R.id.image;

public class PostRepairs extends AppCompatActivity {

    ListView list;
    String [] titles;
    String [] descriptions;
    int avatar = R.id.avatar;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparations_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_post_repairs);
        setSupportActionBar(toolbar);

        Resources resources = getResources();
        titles = resources.getStringArray(R.array.allPost);
        descriptions = resources.getStringArray(R.array.Description);
        date = new Date();

        list = (ListView) findViewById(R.id.postList_reparations);

        PostAdapter postAdapter = new PostAdapter(this, titles, image, date, descriptions);
        list.setAdapter(postAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Chat.class);
                startActivity(intent);
            }
        });
    }

    class  PostAdapter extends ArrayAdapter {

        int image;
        String[] title;
        String[] description;
        Context context;
        Date date;

        public PostAdapter(Context context, String[] title, int image, Date date, String[] description) {
            super(context,R.layout.postrow,R.id.textUp, titles);
            this.title =title;
            this.image = image;
            this.description = description;
            this.date = date;
            this.context = context;

        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater layoutInflater =(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.postrow, parent,false);
            TextView actualDate = (TextView) row.findViewById(R.id.Date);
            ImageView actualImage= (ImageView) row.findViewById(R.id.avatar);
            TextView actualTitle = (TextView) row.findViewById(R.id.textUp);
            TextView descriptions = (TextView) row.findViewById(R.id.textDown);
            actualImage.setImageResource(R.drawable.avatar);
            actualDate.setText("2015-12-10 23:15"); // To change, read from database.
            actualTitle.setText(titles[position]);
            descriptions.setText(description[position]);
            return row;

        }
    }



    {

    }
}
