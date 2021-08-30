package me.salmonmoses.telegrambotrouter.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class KeyboardBuilder {
	private boolean resizeKeyboard = false;
	private boolean oneTimeKeyboard = false;
	private boolean selective = false;
	private String inputFieldPlaceholder = null;

	private ArrayList<KeyboardRow> rows = new ArrayList<>();
	private KeyboardRow currentRow = new KeyboardRow();

	public KeyboardBuilder resize() {
		resizeKeyboard = true;
		return this;
	}

	public KeyboardBuilder oneTime() {
		oneTimeKeyboard = true;
		return this;
	}

	public KeyboardBuilder selective() {
		selective = true;
		return this;
	}

	public KeyboardBuilder inputFieldPlaceholder(String placeholder) {
		inputFieldPlaceholder = placeholder;
		return this;
	}

	public KeyboardBuilder newRow() {
		rows.add(currentRow);
		currentRow = new KeyboardRow();
		return this;
	}

	public KeyboardBuilder button(String btn) {
		currentRow.add(btn);
		return this;
	}

	public KeyboardBuilder button(KeyboardButton btn) {
		currentRow.add(btn);
		return this;
	}

	public ReplyKeyboardMarkup build() {
		if (!currentRow.isEmpty()) {
			rows.add(currentRow);
		}
		var builder = ReplyKeyboardMarkup.builder()
		                                 .oneTimeKeyboard(oneTimeKeyboard)
		                                 .resizeKeyboard(resizeKeyboard)
		                                 .selective(selective)
		                                 .inputFieldPlaceholder(inputFieldPlaceholder);
		rows.forEach(builder::keyboardRow);
		return builder.build();
	}
}
