package me.salmonmoses.telegrambotrouter.routes;

public interface TelegramChatRouter {
	TelegramChatRoute getRoute(String id);
	String getDefaultRoute();
}
