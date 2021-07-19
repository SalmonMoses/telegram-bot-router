package me.salmonmoses.telegrambotrouter.route_managers;

import java.util.Optional;

/**
 * TelegramChatStateManager is an interface that provides methods for getting and saving current user's route by chat
 * ID.
 */
public interface TelegramChatStateManager {
	/**
	 * @param chatId chat ID of bot and user
	 * @return current user's route or Optional.empty() if user doesn't have saved route yet.
	 */
	Optional<String> getChatRoute(long chatId);

	/**
	 * Saves current user's route for getting it later
	 * @param chatId chat ID of bot and user
	 * @param route current user's route
	 */
	void saveChatRoute(long chatId, String route);
}
