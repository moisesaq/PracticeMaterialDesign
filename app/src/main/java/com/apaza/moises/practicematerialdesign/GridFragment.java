package com.apaza.moises.practicematerialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.apaza.moises.practicematerialdesign.model.Place;
import com.bumptech.glide.Glide;

import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class GridFragment extends Fragment implements GridViewWithHeaderAndFooter.OnItemClickListener{
    private static final String ARG_SECTION_NUMBER = "section_number";
    GridViewWithHeaderAndFooter gridViewWithHeaderAndFooter;

    public static GridFragment newInstance(int sectionNumber){
        GridFragment gridFragment = new GridFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, sectionNumber);
        gridFragment.setArguments(bundle);
        return gridFragment;
    }

    public GridFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        gridViewWithHeaderAndFooter = (GridViewWithHeaderAndFooter)view.findViewById(R.id.gridview);
        setupGridView(gridViewWithHeaderAndFooter);
        gridViewWithHeaderAndFooter.setOnItemClickListener(this);

        return view;
    }

    private void setupGridView(GridViewWithHeaderAndFooter grid){
        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        switch(sectionNumber){
            case 1:
                grid.addHeaderView(createHeaderView(6, Place.getListPlaces()));
                grid.setAdapter(new GridAdapter(getActivity(), Place.getListPlaces()));
                break;
            case 2:
                grid.addHeaderView(createHeaderView(6, Place.getListFoods()));
                grid.setAdapter(new GridAdapter(getActivity(), Place.getListFoods()));
                break;
            case 3:
                grid.addHeaderView(createHeaderView(6, Place.getListMuseums()));
                grid.setAdapter(new GridAdapter(getActivity(), Place.getListMuseums()));
                break;
        }
    }

    private View createHeaderView(int position, List<Place> list) {

        View view;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.grid_header, null, false);

        Place item = list.get(position);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        Glide.with(image.getContext()).load(item.getPathImage()).into(image);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(item.getName());

        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(item.getDescription());

        TextView address = (TextView) view.findViewById(R.id.address);
        address.setText(item.getAddress());

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);
        ratingBar.setRating((float)item.getRating());

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Place place = (Place)gridViewWithHeaderAndFooter.getAdapter().getItem(position);
        //Snackbar.make(view, "It is not place"+parent.getAdapter().getCount(), Snackbar.LENGTH_LONG).show();
        Place place = new Place("Parque cretasico", "Lugar donde se puede ver dinosaurios", "Ruta 7 zona Fancesa", 4.2, R.drawable.places_tourist);
        if(place != null)
            DetailActivity.createInstance(getActivity(), place);
        else{
            Snackbar.make(view, "It is not place", Snackbar.LENGTH_LONG).show();
        }
    }
}
