package me.salmonmoses.telegrambotrouter.route_managers;

import java.util.Optional;
import java.util.WeakHashMap;

public class InMemoryTelegramChatStateManager implements TelegramChatStateManager {
	private final WeakHashMap<Long, String> states = new WeakHashMap<>();

	@Override
	public Optional<String> getChatRoute(long chatId) {
		return Optional.ofNullable(states.get(chatId));
	}

	@Override
	public void saveChatRoute(long chatId, String route) {
		states.put(chatId, route);
	}
}
