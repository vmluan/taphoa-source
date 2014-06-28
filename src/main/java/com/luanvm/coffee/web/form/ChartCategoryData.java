package com.luanvm.coffee.web.form;

import java.io.Serializable;

public class ChartCategoryData  implements Serializable{

	private Integer chartcategoryid;
	
	/**
	 * @return the chartcategoryid
	 */
	public Integer getChartcategoryid() {
		return chartcategoryid;
	}

	/**
	 * @param chartcategoryid the chartcategoryid to set
	 */
	public void setChartcategoryid(Integer chartcategoryid) {
		this.chartcategoryid = chartcategoryid;
	}

	/**
	 * @return the categoryname
	 */
	public String getCategoryname() {
		return categoryname;
	}

	/**
	 * @param categoryname the categoryname to set
	 */
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	private String categoryname;
	
	private Integer position;
	
	private Boolean active;
}
