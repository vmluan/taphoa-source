package com.luanvm.coffee.web.form;

import java.util.List;

public class GenericGrid<T> {

	private int totalPages;
	
	private int currentPage;

	private long totalRecords;

	private List<T> entityData;

	/**
	 * @return the categoryData
	 */
	public List<T> getEntityData() {
		return entityData;
	}

	/**
	 * @param categoryData the categoryData to set
	 */
	public void setEntityData(List<T> categoryData) {
		this.entityData = categoryData;
	}
	
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalRecords
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

}
