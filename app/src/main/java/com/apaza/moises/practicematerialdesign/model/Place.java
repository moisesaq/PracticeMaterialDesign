package com.apaza.moises.practicematerialdesign.model;


import com.apaza.moises.practicematerialdesign.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Place implements Serializable{
    private String name;
    private String description;
    private String address;
    private double rating;
    private int image;

    public Place(String name, String description, String address, double rating, int image){
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Place setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Place setAddress(String address) {
        this.address = address;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public Place setRating(float rating) {
        this.rating = rating;
        return this;
    }

    public int getImage() {
        return image;
    }

    public Place setImage(int image) {
        this.image = image;
        return this;
    }

    private static List<Place> listPlaces = new ArrayList<Place>();
    private static List<Place> listFoods = new ArrayList<Place>();
    private static List<Place> listMuseums = new ArrayList<Place>();

    public static List<Place> getListPlaces(){
        listPlaces.add(new Place("Parque cretasico", "Lugar donde se puede ver dinosaurios", "Ruta 7 zona Fancesa", 4.2, R.drawable.places_tourist));
        listPlaces.add(new Place("La recoleta", "Descripcion de la recoleta", "Av brasil 1000", 4.2, R.drawable.places_tourist));
        listPlaces.add(new Place("Cementerio general", "Descripcion del cementerio", "Calle colon 123", 4.2, R.drawable.places_tourist));
        listPlaces.add(new Place("La casa de la libertad", "Descripcion de la casa", "Ruta 7 zona Fancesa", 4.2, R.drawable.places_tourist));
        listPlaces.add(new Place("Tarabuco", "Descripcion de tarabuco", "Ruta 7 zona Fancesa", 4.2, R.drawable.places_tourist));
        listPlaces.add(new Place("Yotala", "Lugar donde se puede ver dinosaurios", "Ruta 7 zona Fancesa", 4.2, R.drawable.places_tourist));
        listPlaces.add(new Place("Potolo", "Lugar donde se puede ver dinosaurios", "Ruta 7 zona Fancesa", 4.2, R.drawable.places_tourist));
        return listPlaces;
    }

    public static List<Place> getListFoods(){
        listFoods.add(new Place("Restaurant 7 lunares", "Lugar donde se vende choripan", "Calle loa 1324", 4.2, R.drawable.restaurant));
        listFoods.add(new Place("Restaurant ", "Lugar donde se vende choripan", "Calle loa 1324", 4.2, R.drawable.restaurant));
        listFoods.add(new Place("Restaurant ", "Lugar donde se vende choripan", "Calle loa 1324", 4.2, R.drawable.restaurant));
        listFoods.add(new Place("Restaurant ", "Lugar donde se vende choripan", "Calle loa 1324", 4.2, R.drawable.restaurant));
        listFoods.add(new Place("Restaurant ", "Lugar donde se vende choripan", "Calle loa 1324", 4.2, R.drawable.restaurant));
        listFoods.add(new Place("Restaurant ", "Lugar donde se vende choripan", "Calle loa 1324", 4.2, R.drawable.restaurant));
        listFoods.add(new Place("Restaurant ", "Lugar donde se vende choripan", "Calle loa 1324", 4.2, R.drawable.restaurant));
        return listFoods;
    }

    public static List<Place> getListMuseums(){
        listMuseums.add(new Place("Museum 123", "Museo donde nacio la libertad de Bolivia", "Placa 25 de mayo", 4.2, R.drawable.museum));
        listMuseums.add(new Place("Museum ", "Museo donde nacio la libertad de Bolivia", "Placa 25 de mayo", 4.2, R.drawable.museum));
        listMuseums.add(new Place("Museum ", "Museo donde nacio la libertad de Bolivia", "Placa 25 de mayo", 4.2, R.drawable.museum));
        listMuseums.add(new Place("Museum ", "Museo donde nacio la libertad de Bolivia", "Placa 25 de mayo", 4.2, R.drawable.museum));
        listMuseums.add(new Place("Museum ", "Museo donde nacio la libertad de Bolivia", "Placa 25 de mayo", 4.2, R.drawable.museum));
        listMuseums.add(new Place("Museum ", "Museo donde nacio la libertad de Bolivia", "Placa 25 de mayo", 4.2, R.drawable.museum));
        listMuseums.add(new Place("Museum ", "Museo donde nacio la libertad de Bolivia", "Placa 25 de mayo", 4.2, R.drawable.museum));

        return listMuseums;
    }
}
