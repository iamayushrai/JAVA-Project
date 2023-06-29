package com.abc.demo.dto;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "c_feedback")
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO extends BaseDTO {
	
	@Column(name="QUESTION",length = 225)
	private String feedback;
	
	private Integer rating;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
