package me.salmonmoses.telegrambotrouter.util;

public class RouteBuilder {
	private StringBuilder builder = new StringBuilder();
	private boolean hasParams = false;

	public RouteBuilder(String route) {
		builder.append(route);
	}

	public RouteBuilder withParam(String param, String value) {
		if (!hasParams) {
			builder.append("?");
		} else {
			builder.append("&");
		}
		builder.append(param);
		builder.append("=");
		builder.append(value);
		return this;
	}

	public String get() {
		return builder.toString();
	}
}
