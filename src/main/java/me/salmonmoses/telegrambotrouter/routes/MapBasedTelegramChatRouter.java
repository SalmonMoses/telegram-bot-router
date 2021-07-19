package me.salmonmoses.telegrambotrouter.routes;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

public class MapBasedTelegramChatRouter implements TelegramChatRouter {
	private WeakHashMap<String, Supplier<TelegramChatRoute>> routes = new WeakHashMap<>();
	private String defaultRoute = "/";

	public MapBasedTelegramChatRouter() {
		routes.put(getDefaultRoute(), DefaultTelegramChatRoute::new);
	}

	public MapBasedTelegramChatRouter route(String route, Supplier<TelegramChatRoute> supplier) {
		routes.put(route, supplier);
		return this;
	}

	@Override
	public TelegramChatRoute getRoute(String route) {
		var routeSupplier = routes.get(route);
		return routeSupplier != null ? routeSupplier.get() : routes.get(getDefaultRoute()).get();
	}

	@Override
	public String getDefaultRoute() {
		return defaultRoute;
	}

	public MapBasedTelegramChatRouter defaultRoute(String route) {
		defaultRoute = route;
		return this;
	}
}
