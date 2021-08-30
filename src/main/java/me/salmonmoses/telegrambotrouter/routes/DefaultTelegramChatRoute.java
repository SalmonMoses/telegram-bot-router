package me.salmonmoses.telegrambotrouter.routes;

import me.salmonmoses.telegrambotrouter.TelegramApiMethodExecutor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Optional;

public class DefaultTelegramChatRoute implements TelegramChatRoute {
	@Override
	public Optional<String> onUpdate(Update update, AbsSender executor) {
		return Optional.empty();
	}
}
