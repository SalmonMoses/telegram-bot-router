package me.salmonmoses.telegrambotrouter.routes;

import me.salmonmoses.telegrambotrouter.TelegramApiMethodExecutor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 *
 */
public interface TelegramChatRoute {
	/**
	 * This method is called when receiving updates via GetUpdates method
	 * @param update Update received
	 * @param executor Executes Telegram API methods
	 * @return Next route name or Optional.empty() if route doesn't need to be changed.
	 */
	Optional<String> onUpdate(Update update, TelegramApiMethodExecutor executor);
}
