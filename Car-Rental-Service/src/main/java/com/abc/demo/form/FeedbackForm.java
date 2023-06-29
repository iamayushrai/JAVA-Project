package com.abc.demo.form;

import javax.validation.constraints.NotEmpty;

import com.abc.demo.dto.BaseDTO;
import com.abc.demo.dto.FeedbackDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class FeedbackForm extends BaseForm {

	@NotEmpty(message = "Feedback is required")
	private String feedback;
	@NotEmpty(message = "Rating is required")
	private Integer rating;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		FeedbackDTO bean = new FeedbackDTO();
		bean.setId(id);
		bean.setFeedback(feedback);
		bean.setRating(rating);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		FeedbackDTO bean = (FeedbackDTO)bDto;
		id = bean.getId();
		feedback = bean.getFeedback();
		rating = bean.getRating();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		
	}

}
