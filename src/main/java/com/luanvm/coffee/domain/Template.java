package com.luanvm.coffee.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "thk_template", catalog = "thkassociates")
public class Template implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "templateid", nullable = false)
	private Integer templateid;

	@Column(name = "code", length = 45)
	private String code;

	@Column(name = "content")
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "createdby")
	private Integer createdby;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;

	@Column(name = "updatedby")
	private Integer updatedby;

	public Template() {
		super();
	}

	public void setTemplateid(Integer templateid) {
		this.templateid = templateid;
	}

	public Integer getTemplateid() {
		return this.templateid;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public Integer getCreatedby() {
		return this.createdby;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}

	public Integer getUpdatedby() {
		return this.updatedby;
	}

}
