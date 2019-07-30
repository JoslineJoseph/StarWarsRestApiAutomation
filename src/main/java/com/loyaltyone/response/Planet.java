package com.loyaltyone.response;

import java.util.ArrayList;

public class Planet {

	private String name;
	private String rotation_period;
	private String orbital_period;
	private String diameter;
	private String climate;
	private String gravity;
	private String terrain;
	private String surface_water;
	private String population;
	ArrayList < String > residents = new ArrayList < String > ();
	ArrayList < String > films = new ArrayList < String > ();
	private String created;
	private String edited;
	private String url;


	// Getter Methods 

	public String getName() {
		return name;
	}

	public String getRotation_period() {
		return rotation_period;
	}

	public String getOrbital_period() {
		return orbital_period;
	}

	public String getDiameter() {
		return diameter;
	}

	public String getClimate() {
		return climate;
	}

	public String getGravity() {
		return gravity;
	}

	public String getTerrain() {
		return terrain;
	}

	public String getSurface_water() {
		return surface_water;
	}

	public String getPopulation() {
		return population;
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

	// Setter Methods 

	public void setName(String name) {
		this.name = name;
	}

	public void setRotation_period(String rotation_period) {
		this.rotation_period = rotation_period;
	}

	public void setOrbital_period(String orbital_period) {
		this.orbital_period = orbital_period;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public void setSurface_water(String surface_water) {
		this.surface_water = surface_water;
	}

	public void setPopulation(String population) {
		this.population = population;
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

	public ArrayList<String> getResidents() {
		return residents;
	}

	public void setResidents(ArrayList<String> residents) {
		this.residents = residents;
	}

	public ArrayList<String> getFilms() {
		return films;
	}

	public void setFilms(ArrayList<String> films) {
		this.films = films;
	}

}
