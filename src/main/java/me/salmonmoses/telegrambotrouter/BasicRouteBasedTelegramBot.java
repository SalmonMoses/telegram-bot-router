package me.salmonmoses.telegrambotrouter;

import me.salmonmoses.telegrambotrouter.route_managers.InMemoryTelegramChatStateManager;
import me.salmonmoses.telegrambotrouter.route_managers.TelegramChatStateManager;
import me.salmonmoses.telegrambotrouter.routes.MapBasedTelegramChatRouter;
import me.salmonmoses.telegrambotrouter.routes.TelegramChatRouter;

public class BasicRouteBasedTelegramBot extends RouteBasedTelegramBot {
	private final String botToken;
	private final String botUsername;
	private final TelegramChatRouter chatRouter;
	private final TelegramChatStateManager chatStateManager;

	public static RouteBasedBotBuilder builder() {
		return new RouteBasedBotBuilder();
	}

	public BasicRouteBasedTelegramBot(String botToken, String botUsername, TelegramChatRouter chatRouter,
	                                  TelegramChatStateManager chatStateManager) {
		this.botToken = botToken;
		this.botUsername = botUsername;
		this.chatRouter = chatRouter;
		this.chatStateManager = chatStateManager;
	}

	@Override
	public String getBotUsername() {
		return botUsername;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	@Override
	protected TelegramChatRouter getChatRouter() {
		return chatRouter;
	}

	@Override
	protected TelegramChatStateManager getChatStateManager() {
		return chatStateManager;
	}

	public static class RouteBasedBotBuilder {
		private String botToken = "";
		private String botUsername = "";
		private TelegramChatRouter chatRouter = new MapBasedTelegramChatRouter();
		private TelegramChatStateManager chatStateManager = new InMemoryTelegramChatStateManager();

		public RouteBasedBotBuilder token(String token) {
			this.botToken = token;
			return this;
		}

		public RouteBasedBotBuilder username(String username) {
			this.botUsername = username;
			return this;
		}

		public RouteBasedBotBuilder chatRouter(TelegramChatRouter chatRouter) {
			this.chatRouter = chatRouter;
			return this;
		}

		public RouteBasedBotBuilder chatStateManager(TelegramChatStateManager chatStateManager) {
			this.chatStateManager = chatStateManager;
			return this;
		}

		public BasicRouteBasedTelegramBot build() {
			return new BasicRouteBasedTelegramBot(botToken, botUsername, chatRouter, chatStateManager);
		}
	}
}
