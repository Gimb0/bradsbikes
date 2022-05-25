package dev.gimboscloud.bradsbikes.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class MotorbikeAccessory extends Product {

	@ManyToMany(mappedBy = "accessories")
	private List<Motorbike> motorbikes;

	public MotorbikeAccessory() {
		super();
		motorbikes = new ArrayList<>();
	}

	public MotorbikeAccessory(String name, String description, Integer quantity, double price) {
		super(name, description, quantity, price);
		motorbikes = new ArrayList<>();
	}

	public List<Motorbike> getMotorbikes() {
		return motorbikes;
	}

	public void setMotorbikes(List<Motorbike> motorbike) {
		this.motorbikes = motorbike;
	}
	
	public void addMotorbike(Motorbike motorbike) {
		this.motorbikes.add(motorbike);
	}
}
