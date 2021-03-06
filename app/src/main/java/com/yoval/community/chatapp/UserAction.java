package com.yoval.community.chatapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Alejandro on 2017-03-31.
 */

public class UserAction extends AppCompatActivity {

    Integer[] iconsIds = {
            R.drawable.ic_megaphone,
            R.drawable.ic_binoculars,
            R.drawable.ic_customer_rate,
            R.drawable.ic_user_profile

    };
    String[] labels = {
            "Annoncer un service",
            "Chercher une service",
            "Évaluer un service",
            "Modifier mon profil"
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAction);
        setSupportActionBar(toolbar);

        GridView grid = (GridView) findViewById(R.id.grid_view_action);

        //Set the adapter
        grid.setAdapter(new IconAdapter(this));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // DO something
                goToServicesView(position);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void goToServicesView(int position) {

        Intent intent = new Intent();
        switch (position) {

            case 0:
                intent = new Intent(this, ServicesAdvertise.class);
                break;

            case 1:
                intent = new Intent(this, ServicesSearch.class);
                break;
            default:
                intent = new Intent(this, ServicesSearch.class);
        }

        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public com.google.android.gms.appindexing.Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ServicesAdvertise Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new com.google.android.gms.appindexing.Action.Builder(com.google.android.gms.appindexing.Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(com.google.android.gms.appindexing.Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    public class IconAdapter extends BaseAdapter {

        private Context context;

        public IconAdapter(Context ctx) {
            context = ctx;
        }

        @Override
        public int getCount() {
            return iconsIds.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.content_action, null);
                TextView textView = (TextView) view.findViewById(R.id.grid_text_action);
                ImageView imageView = (ImageView) view.findViewById(R.id.grid_image_action);

                textView.setText(labels[position]);
                imageView.setImageResource(iconsIds[position]);
            } else {
                view = convertView;
            }
            return view;
        }
    }
}
