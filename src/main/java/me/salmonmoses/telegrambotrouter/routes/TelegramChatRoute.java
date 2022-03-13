package me.salmonmoses.telegrambotrouter.routes;

import me.salmonmoses.telegrambotrouter.TelegramApiMethodExecutor;
import me.salmonmoses.telegrambotrouter.UpdateContext;
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
	 * @param context Object that contains all information about update
	 * @return Next route name or Optional.empty() if route doesn't need to be changed.
	 */
	Optional<String> onUpdate(UpdateContext context);

	default void onExit(long chatId, AbsSender executor) {}
}
