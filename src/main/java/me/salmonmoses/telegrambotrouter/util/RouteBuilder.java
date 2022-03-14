package me.salmonmoses.telegrambotrouter.util;

public class RouteBuilder {
	private final StringBuilder builder = new StringBuilder();
	private boolean hasParams = false;

	public RouteBuilder(String route) {
		builder.append(route);
	}

	public RouteBuilder withParam(String param, String value) {
		if (!hasParams) {
			builder.append("?");
			hasParams = true;
		} else {
			builder.append("&");
		}
		builder.append(param);
		builder.append("=");
		builder.append(value);
		return this;
	}

	@Deprecated
	public String get() {
		return build();
	}

	public String build() {
		return builder.toString();
	}
}
