package com.apaza.moises.practicematerialdesign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.apaza.moises.practicematerialdesign.model.Place;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_PLACE = "place";

    public static void createInstance(Activity activity, Place place) {
        Intent intent = getLaunchIntent(activity, place);
        activity.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, Place place) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_PLACE, place);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setToolbar();
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Place place = (Place)i.getSerializableExtra(EXTRA_PLACE);

        CollapsingToolbarLayout collapser = (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(place.getName()); // Cambiar título

        loadImageParallax(place.getPathImage());// Cargar Imagen

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    private void loadImageParallax(int id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        Glide.with(this)
                .load(id)
                .centerCrop()
                .into(image);
    }

}
