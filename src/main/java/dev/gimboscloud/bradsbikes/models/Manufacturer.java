package dev.gimboscloud.bradsbikes.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@OneToMany(mappedBy = "manufacturer")
	private List<Motorbike> motorbikes;

	public Manufacturer() {
		motorbikes = new ArrayList<>();
	}
	
	public Manufacturer(String name) {
		super();
		motorbikes = new ArrayList<>();
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Motorbike> getProducts() {
		return motorbikes;
	}

	public void setProducts(List<Motorbike> motorbikes) {
		this.motorbikes = motorbikes;
	}

	public void addProduct(Motorbike motorbike) {
		motorbikes.add(motorbike);
	}
}
