package com.luanvm.coffee.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thk_product_unit", catalog = "thkassociates")
public class TH_ProductUnit implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unitID", nullable = false)
	private Integer unitID;
	
	@Column(name ="name")
	private String unitName;
	
	@Column(name = "number_of_friends")
	private Integer numberOfFriends;
	
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id", insertable = false, updatable = false)
	private TH_ProductUnit parentUnit;
    
    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="category_id", insertable = false, updatable = false)
    private TH_Category category;
    
	public Integer getUnitID() {
		return unitID;
	}


	public void setUnitID(Integer unitID) {
		this.unitID = unitID;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public Integer getNumberOfFriends() {
		return numberOfFriends;
	}


	public void setNumberOfFriends(Integer numberOfFriends) {
		this.numberOfFriends = numberOfFriends;
	}


	public TH_ProductUnit getParentUnit() {
		return parentUnit;
	}


	public void setParentUnit(TH_ProductUnit parentUnit) {
		this.parentUnit = parentUnit;
	}



	public TH_Category getCategory() {
		return category;
	}


	public void setCategory(TH_Category category) {
		this.category = category;
	}
	
	
	
}
