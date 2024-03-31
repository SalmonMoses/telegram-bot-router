package me.salmonmoses.telegrambotrouter.routes;

import me.salmonmoses.telegrambotrouter.UpdateContext;

import java.util.Optional;

public class DefaultTelegramChatRoute implements TelegramChatRoute {
	@Override
	public Optional<String> onUpdate(UpdateContext context) {
		return Optional.empty();
	}
}
