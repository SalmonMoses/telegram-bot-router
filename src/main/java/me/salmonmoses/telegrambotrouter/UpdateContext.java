package me.salmonmoses.telegrambotrouter;

import lombok.Getter;
import me.salmonmoses.telegrambotrouter.util.BotUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UpdateContext {
	@Getter
	private final Update update;
	@Getter
	private final AbsSender executor;
	@Getter
	private final String route;
	@Getter
	private final long chatId;
	private Map<String, String> params;

	protected UpdateContext(Update update, AbsSender executor, String route) {
		this.update = update;
		this.executor = executor;
		this.route = route;
		this.chatId = BotUtils.getChatId(this.update);
	}

	public Map<String, String> getRouteParams() {
		if (params != null) {
			return params;
		}
		Map<String, String> routeParams = new HashMap<>();
		String[] routeParts = route.split("\\?");
		if (routeParts.length == 2) {
			String paramsString = routeParts[1];
			String[] paramsList = paramsString.split("&");
			Arrays.stream(paramsList).forEach(param -> {
				String[] paramTokens = param.split("=");
				String paramName = paramTokens[0];
				String paramValue = paramTokens[1];
				routeParams.put(paramName, paramValue);
			});
		}
		params = routeParams;
		return params;
	}
}
