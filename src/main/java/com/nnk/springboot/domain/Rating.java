package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "rating")
public class Rating {
	// TODO: Map columns in data table RATING with corresponding java fields ==> OK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "moodys_rating")
	private String moodysRating;
	@Column(name = "sandprating")
	private String sandPRating;
	@Column(name = "fitch_rating")
	private String fitchRating;
	@Column(name = "order_number")
	@Digits(integer = 20, fraction = 0, message = "Doit être suppérieur à 0 et contenir moins de 20 nombres")
	private Integer orderNumber;

	public Rating() {
		super();
	}

	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

}