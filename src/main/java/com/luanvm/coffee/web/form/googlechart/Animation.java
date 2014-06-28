package com.luanvm.coffee.web.form.googlechart;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("animation")
public class Animation {
	private String duration;
	private String easing;
	
	public String getEasing() {
		return easing;
	}
	public void setEasing(String easing) {
		this.easing = easing;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
}
