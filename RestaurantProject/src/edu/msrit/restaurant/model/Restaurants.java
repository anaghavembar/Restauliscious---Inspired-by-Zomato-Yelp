package edu.msrit.restaurant.model;

import java.util.ArrayList;

public class Restaurants {
	int restId;
	String name; 
	String area; 
	String city; 
	int pendingOrders;
	ArrayList<RestaurantType> types;
	
	public ArrayList<RestaurantType> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<RestaurantType> arr) {
		this.types = arr;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPendingOrders() {
		return pendingOrders;
	}
	public void setPendingOrders(int pendingOrders) {
		this.pendingOrders = pendingOrders;
	}
}