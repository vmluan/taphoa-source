package com.luanvm.coffee.web.form.googlechart;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ChartOptions {
	
	// Manual Information for chart
	@JsonProperty("ChartType")
	private String charttype;

	public String getCharttype() {
		return charttype;
	}

	public void setCharttype(String charttype) {
		this.charttype = charttype;
	}
	
	
	// Google chart options
	private String title;
	private String titlePosition;
	private String backgroundColor;
	private List<String> colors;
	private Integer fontSize;
	private String fontName;
	private HVAxis hAxis;
	private HVAxis vAxis;
	
	public HVAxis getvAxis() {
		return vAxis;
	}

	public void setvAxis(HVAxis vAxis) {
		this.vAxis = vAxis;
	}

	public HVAxis gethAxis() {
		return hAxis;
	}

	public void sethAxis(HVAxis hAxis) {
		this.hAxis = hAxis;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	private Animation animation;

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlePosition() {
		return titlePosition;
	}

	public void setTitlePosition(String titlePosition) {
		this.titlePosition = titlePosition;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	
	public void createAnimation(String easing, String duration)
	{
		animation =new Animation();
		animation.setDuration(duration);
		animation.setEasing(easing);
	}
	
	public void createHAxis(Integer maxValue, Integer minValue)
	{
		hAxis = new HVAxis();
		hAxis.setMaxValue(maxValue);
		hAxis.setMinValue(minValue);
	}
	
	public void createVAxis(Integer maxValue, Integer minValue)
	{
		vAxis = new HVAxis();
		vAxis.setMaxValue(maxValue);
		vAxis.setMinValue(minValue);
	}
}
