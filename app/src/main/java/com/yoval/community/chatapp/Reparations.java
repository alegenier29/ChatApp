package com.yoval.community.chatapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static android.R.attr.resource;
import static com.yoval.community.chatapp.R.id.image;

public class Reparations extends AppCompatActivity {

    ListView list;
    String [] titles;
    String [] descriptions;
    int avatar = R.id.avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparations);

        Resources resources = getResources();
        titles = resources.getStringArray(R.array.allPost);
        descriptions = resources.getStringArray(R.array.Description);

        list = (ListView) findViewById(R.id.postList);

        PostAdapter postAdapter = new PostAdapter(this,titles,image,descriptions);
        list.setAdapter(postAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    class  PostAdapter extends ArrayAdapter {

        int image;
        String[] title;
        String[] description;
        Context context;

        public PostAdapter(Context context, String[] title, int image, String[] description) {
            super(context,R.layout.postrow,R.id.textUp, titles);
            this.title =title;
            this.image = image;
            this.description = description;
            this.context = context;

        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater layoutInflater =(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.postrow, parent,false);
            ImageView actualImage= (ImageView) row.findViewById(R.id.avatar);
            TextView actualTitle = (TextView) row.findViewById(R.id.textUp);
            TextView descriptions = (TextView) row.findViewById(R.id.textDown);
            actualImage.setImageResource(R.drawable.avatar);
            actualTitle.setText(titles[position]);
            descriptions.setText(description[position]);
            return row;

        }
    }



    {

    }
}
