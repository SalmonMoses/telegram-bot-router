package me.salmonmoses.telegrambotrouter.routes;

import me.salmonmoses.telegrambotrouter.TelegramApiMethodExecutor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Optional;

/**
 *
 */
public interface TelegramChatRoute {
	default void onEnter(long chatId, AbsSender executor) {}

	/**
	 * This method is called when receiving updates via GetUpdates method
	 * @param update Update received
	 * @param executor Executes Telegram API methods
	 * @return Next route name or Optional.empty() if route doesn't need to be changed.
	 */
	Optional<String> onUpdate(Update update, AbsSender executor);

	default void onExit(long chatId, AbsSender executor) {}
}
