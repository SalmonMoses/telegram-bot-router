package me.salmonmoses.telegrambotrouter.routes;

import me.salmonmoses.telegrambotrouter.TelegramRouteHandlingFailedException;
import me.salmonmoses.telegrambotrouter.UpdateContext;

import java.util.Optional;

/**
 *
 */
public interface TelegramChatRoute {
	/**
	 * This method is called when user enters this route after being redirected or when using bot for the first time.
	 *
	 * @param context Object that contains all information about update
	 */
	default void onEnter(UpdateContext context) {
	}

	/**
	 * This method is called when receiving updates via GetUpdates method
	 *
	 * @param context Object that contains all information about update
	 * @return Next route name or Optional.empty() if route doesn't need to be changed.
	 */
	Optional<String> onUpdate(UpdateContext context) throws TelegramRouteHandlingFailedException;

	/**
	 * This method is called before redirecting user to the new route (but after calling saveState from
	 * ChatStateManager)
	 *
	 * @param context Object that contains all information about update
	 */
	default void onExit(UpdateContext context) {
	}
}
