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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class ServicesAdvertise extends AppCompatActivity {

    Integer[] iconsIds = {
            R.drawable.ic_reparation,
            R.drawable.ic_drum_set,
            R.drawable.ic_elder,
            R.drawable.ic_dog,
            R.drawable.ic_alarm,
            R.drawable.ic_exam,
            R.drawable.ic_radish,
            R.drawable.ic_car,
            R.drawable.ic_baseball,
            R.drawable.ic_bucket,
            R.drawable.ic_plus
    };
    String[] labels = {
            "Reparations",
            "Lesson de musique",
            "Aide aux aînes",
            "Animaux de compagnie",
            "Securité du quartier",
            "Preparer des examens",
            "Partage de nourriture",
            "Covoiturage",
            "Faire des sports",
            "Nettoyage de maison",
            "Autre"
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_advertise);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_services_advertise);
        setSupportActionBar(toolbar);


        GridView grid = (GridView) findViewById(R.id.grid_view_advertise);

        //Set the adapter
        grid.setAdapter(new IconAdapter(this));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // DO something
                goToDetailsView(position);

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void goToDetailsView(int position) {
        Intent intent = new Intent(this, CreationService.class);
        intent.putExtra("Message", labels[position]);
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ServicesAdvertise Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
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
                view = layoutInflater.inflate(R.layout.content_services_advertise, null);
                TextView textView = (TextView) view.findViewById(R.id.grid_text_advertise);
                ImageView imageView = (ImageView) view.findViewById(R.id.grid_image_advertise);

                textView.setText(labels[position]);
                imageView.setImageResource(iconsIds[position]);
            } else {
                view = convertView;
            }
            return view;
        }
    }
}