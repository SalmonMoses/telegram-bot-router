package me.salmonmoses.telegrambotrouter;

import lombok.AccessLevel;
import lombok.Getter;
import me.salmonmoses.telegrambotrouter.route_managers.TelegramChatStateManager;
import me.salmonmoses.telegrambotrouter.routes.TelegramChatRoute;
import me.salmonmoses.telegrambotrouter.routes.TelegramChatRouter;
import me.salmonmoses.telegrambotrouter.util.BotUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * Base abstract class for bot that uses routing. Class implements onUpdateReceived, that is used as bot's main loop
 */
public abstract class RouteBasedTelegramBot extends TelegramLongPollingBot {
	@Getter(AccessLevel.PROTECTED)
	private TelegramChatRouter chatRouter;

	@Getter(AccessLevel.PROTECTED)
	private TelegramChatStateManager chatStateManager;

	@Override
	public void onUpdateReceived(Update update) {
		final Long chatId = BotUtils.getChatId(update);
		Optional<String> chatState = getChatStateManager().getChatRoute(chatId);
		if (chatState.isEmpty()) {
			getChatStateManager().saveChatRoute(chatId, getChatRouter().getDefaultRoute());
			chatState = Optional.of(getChatRouter().getDefaultRoute());
		}
		TelegramChatRoute handler = getChatRouter().getRoute(chatState.get());
		Optional<String> nextRoute = handler.onUpdate(update, this::execute);
		nextRoute.ifPresent(s -> getChatStateManager().saveChatRoute(chatId, s));
	}
}
