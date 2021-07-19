package me.salmonmoses.telegrambotrouter.routes;

import java.util.HashMap;
import java.util.function.Supplier;

public class MapBasedTelegramChatRouter implements TelegramChatRouter {
    private final HashMap<String, Supplier<TelegramChatRoute>> routes = new HashMap<>();
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
        return routes.getOrDefault(route, routes.get(getDefaultRoute())).get();
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
