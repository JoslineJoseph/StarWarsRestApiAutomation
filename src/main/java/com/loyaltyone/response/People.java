package com.loyaltyone.response;

import java.util.ArrayList;

public class People {

	private String name;
	private String height;
	private String mass;
	private String hair_color;
	private String skin_color;
	private String eye_color;
	private String birth_year;
	private String gender;
	private String homeworld;
	ArrayList < String > films = new ArrayList < String > ();
	ArrayList < String > species = new ArrayList < String > ();
	ArrayList < String > vehicles = new ArrayList < String > ();
	ArrayList < String > starships = new ArrayList < String > ();
	private String created;
	private String edited;
	private String url;


	// Getter Methods 

	public String getName() {
		return name;
	}

	public String getHeight() {
		return height;
	}

	public String getMass() {
		return mass;
	}

	public String getHair_color() {
		return hair_color;
	}

	public String getSkin_color() {
		return skin_color;
	}

	public String getEye_color() {
		return eye_color;
	}

	public String getBirth_year() {
		return birth_year;
	}

	public String getGender() {
		return gender;
	}

	public String getHomeworld() {
		return homeworld;
	}

	public String getCreated() {
		return created;
	}

	public String getEdited() {
		return edited;
	}

	public String getUrl() {
		return url;
	}

	public ArrayList<String> getFilms() {
		return films;
	}

	public void setFilms(ArrayList<String> films) {
		this.films = films;
	}

	public ArrayList<String> getSpecies() {
		return species;
	}

	public void setSpecies(ArrayList<String> species) {
		this.species = species;
	}

	public ArrayList<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<String> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<String> getStarships() {
		return starships;
	}

	public void setStarships(ArrayList<String> starships) {
		this.starships = starships;
	}
	// Setter Methods 

	public void setName(String name) {
		this.name = name;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}

	public void setSkin_color(String skin_color) {
		this.skin_color = skin_color;
	}

	public void setEye_color(String eye_color) {
		this.eye_color = eye_color;
	}

	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
