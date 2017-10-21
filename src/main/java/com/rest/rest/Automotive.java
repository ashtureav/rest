package com.rest.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Automotive {
	
	private String make;
	private String model;
	private float engine;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public float getEngine() {
		return engine;
	}
	public void setEngine(float engine) {
		this.engine = engine;
	}
}
