package dev.gimboscloud.bradsbikes.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Motorbike extends Product {

	private double horsepower;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<MotorbikeAccessory> accessories;

	public Motorbike() {
		super();
		accessories = new ArrayList<>();
	}

	public Motorbike(String name, String description, Integer quantity, double price, double horsepower) {
		super(name, description, quantity, price);
		this.horsepower = horsepower;
		accessories = new ArrayList<>();
	}
	
	public double getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(double horsepower) {
		this.horsepower = horsepower;
	}
	
	public void addAccessory(MotorbikeAccessory accessory) {
		this.accessories.add(accessory);
	}
}
