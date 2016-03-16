package com.apaza.moises.practicematerialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.apaza.moises.practicematerialdesign.model.Place;
import com.bumptech.glide.Glide;

import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by moises on 16/03/16.
 */
public class GridFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

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
        GridViewWithHeaderAndFooter gridViewWithHeaderAndFooter = (GridViewWithHeaderAndFooter)view.findViewById(R.id.gridview);
        setupGridView(gridViewWithHeaderAndFooter);
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

        // Seteando Imagen
        ImageView image = (ImageView) view.findViewById(R.id.image);
        Glide.with(image.getContext()).load(item.getPathImage()).into(image);

        // Seteando Nombre
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(item.getName());

        // Seteando Descripción
        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(item.getDescription());

        // Seteando Precio
        TextView address = (TextView) view.findViewById(R.id.address);
        address.setText(item.getAddress());

        // Seteando Rating
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);
        ratingBar.setRating((float)item.getRating());

        return view;
    }

}
