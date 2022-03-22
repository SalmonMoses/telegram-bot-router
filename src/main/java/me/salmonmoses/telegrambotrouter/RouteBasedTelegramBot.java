package me.salmonmoses.telegrambotrouter;

import lombok.AccessLevel;
import lombok.Getter;
import me.salmonmoses.telegrambotrouter.route_managers.InMemoryTelegramChatStateManager;
import me.salmonmoses.telegrambotrouter.route_managers.TelegramChatStateManager;
import me.salmonmoses.telegrambotrouter.routes.MapBasedTelegramChatRouter;
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
	private TelegramChatRouter chatRouter = new MapBasedTelegramChatRouter();

	@Getter(AccessLevel.PROTECTED)
	private TelegramChatStateManager chatStateManager = new InMemoryTelegramChatStateManager();

	@Override
	public void onUpdateReceived(Update update) {
		final Long chatId = BotUtils.getChatId(update);
		Optional<String> chatState = getChatStateManager().getChatRoute(chatId);
		if (chatState.isEmpty()) {
			getChatStateManager().saveChatRoute(chatId, getChatRouter().getDefaultRoute());
			chatState = Optional.of(getChatRouter().getDefaultRoute());
		}
		TelegramChatRoute handler = getChatRouter().getRoute(chatState.get().split("\\?")[0]);
		UpdateContext context = new UpdateContext(update, this, chatState.get());
		Optional<String> nextRoute = Optional.empty();
		try {
			nextRoute = handler.onUpdate(context);
		} catch (TelegramRouteHandlingFailedException e) {
			try {
				nextRoute = getChatRouter().getFallbackRouteHandler().onUpdate(context);
			} catch (TelegramRouteHandlingFailedException ex) {
				ex.printStackTrace();
			}
		}
		nextRoute.ifPresent(s -> {
			getChatStateManager().saveChatRoute(chatId, s);
			handler.onExit(chatId, this);
			getChatRouter().getRoute(s.split("\\?")[0]).onEnter(chatId, this);
		});
	}
}
