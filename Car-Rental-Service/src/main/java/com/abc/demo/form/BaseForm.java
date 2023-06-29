package com.abc.demo.form;

import java.sql.Timestamp;

import com.abc.demo.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public abstract class BaseForm {

	protected long id;

	protected String createdBy;

	protected String modifiedBy;

	protected Timestamp createdDateTime;

	protected Timestamp modifiedDateTime;

	protected int pageNo = 1;

	protected int pageSize = 10;

	protected long[] ids;

	protected int listsize;

	protected int total;

	private String operation;
	private int pagenosize;

	public abstract BaseDTO getDTO();

	public abstract void populate(BaseDTO bDto);
}
