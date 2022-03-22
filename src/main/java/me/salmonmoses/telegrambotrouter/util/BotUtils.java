package me.salmonmoses.telegrambotrouter.util;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static me.salmonmoses.telegrambotrouter.util.Flag.*;

public class BotUtils {
	public static User EMPTY_USER = new User(0L, "", false);

	public static Long getChatId(Update update) {
		if (MESSAGE.test(update)) {
			return update.getMessage().getChatId();
		} else if (CALLBACK_QUERY.test(update)) {
			return update.getCallbackQuery().getMessage().getChatId();
		} else if (INLINE_QUERY.test(update)) {
			return update.getInlineQuery().getFrom().getId();
		} else if (CHANNEL_POST.test(update)) {
			return update.getChannelPost().getChatId();
		} else if (EDITED_CHANNEL_POST.test(update)) {
			return update.getEditedChannelPost().getChatId();
		} else if (EDITED_MESSAGE.test(update)) {
			return update.getEditedMessage().getChatId();
		} else if (CHOSEN_INLINE_QUERY.test(update)) {
			return update.getChosenInlineQuery().getFrom().getId();
		} else if (SHIPPING_QUERY.test(update)) {
			return update.getShippingQuery().getFrom().getId();
		} else if (PRECHECKOUT_QUERY.test(update)) {
			return update.getPreCheckoutQuery().getFrom().getId();
		} else if (POLL_ANSWER.test(update)) {
			return update.getPollAnswer().getUser().getId();
		} else if (POLL.test(update)) {
			return EMPTY_USER.getId();
		} else if (MY_CHAT_MEMBER.test(update)) {
			return update.getMyChatMember().getChat().getId();
		} else if (CHAT_MEMBER.test(update)) {
			return update.getChatMember().getChat().getId();
		} else {
			throw new IllegalStateException("Could not retrieve originating chat ID from update");
		}
	}

	public static Long getFromId(Update update) {
		if (MESSAGE.test(update)) {
			return update.getMessage().getFrom().getId();
		} else if (CALLBACK_QUERY.test(update)) {
			return update.getCallbackQuery().getMessage().getFrom().getId();
		} else if (INLINE_QUERY.test(update)) {
			return update.getInlineQuery().getFrom().getId();
		} else if (CHANNEL_POST.test(update)) {
			return update.getChannelPost().getFrom().getId();
		} else if (EDITED_CHANNEL_POST.test(update)) {
			return update.getEditedChannelPost().getFrom().getId();
		} else if (EDITED_MESSAGE.test(update)) {
			return update.getEditedMessage().getFrom().getId();
		} else if (CHOSEN_INLINE_QUERY.test(update)) {
			return update.getChosenInlineQuery().getFrom().getId();
		} else if (SHIPPING_QUERY.test(update)) {
			return update.getShippingQuery().getFrom().getId();
		} else if (PRECHECKOUT_QUERY.test(update)) {
			return update.getPreCheckoutQuery().getFrom().getId();
		} else if (POLL_ANSWER.test(update)) {
			return update.getPollAnswer().getUser().getId();
		} else if (POLL.test(update)) {
			return EMPTY_USER.getId();
		} else if (MY_CHAT_MEMBER.test(update)) {
			return update.getMyChatMember().getFrom().getId();
		} else if (CHAT_MEMBER.test(update)) {
			return update.getChatMember().getFrom().getId();
		} else {
			throw new IllegalStateException("Could not retrieve originating chat ID from update");
		}
	}
}
