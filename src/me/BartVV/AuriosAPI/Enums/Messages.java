package me.BartVV.AuriosAPI.Enums;

import static me.BartVV.AuriosAPI.Enums.FileTypes.MESSAGES;

import java.util.List;

import me.BartVV.AuriosAPI.Interfaces.MainMessage;

public enum Messages implements MainMessage{

	NO_PLAYER(MESSAGES.getFileManager().getString("noPlayer")), 
	MESSAGE_SEND(MESSAGES.getFileManager().getString("MSGSended")), 
	NOT_ENOUGH_ARGUMENT_MSG(MESSAGES.getFileManager().getString("wrongFormatMSG")), 
	DISABLED_MSG(MESSAGES.getFileManager().getString("disabledMSG")), 
	MSG_FORMAT(MESSAGES.getFileManager().getString("MSGFormat")), 
	NOT_ENOUGH_ARGUMENT_REPLY(MESSAGES.getFileManager().getString("wrongFormatReply")), 
	MSG_CANCELLED(MESSAGES.getFileManager().getString("disabledMSG")), 
	ONLY_PLAYER(MESSAGES.getFileManager().getString("onlyPlayer")), 
	NO_PERMISSION(MESSAGES.getFileManager().getString("noPermission")), 
	TOGGLE_PM(MESSAGES.getFileManager().getString("togglePM")), 
	FLY_TOGGLE(MESSAGES.getFileManager().getString("toggleFly")), 
	BUTCHER(MESSAGES.getFileManager().getString("butcher")), 
	FLY_TOGGLE_OTHERS(MESSAGES.getFileManager().getString("toggleFlyOthers")), 
	NOT_ENOUGH_ARGUMENT_IGNOREPM(MESSAGES.getFileManager().getString("wrongFormatIgnorePM")), 
	ADD_IGNORE(MESSAGES.getFileManager().getString("addIgnore")), 
	REMOVE_IGNORE(MESSAGES.getFileManager().getString("removeIgnore")), 
	IGNORE_LIST(MESSAGES.getFileManager().getString("ignoreList")), 
	IGNORE_LIST_OTHER(MESSAGES.getFileManager().getString("ignoreListOthers")), 
	HELP_MENU(MESSAGES.getFileManager().getStringList("helpMenu")),
	CONFIG_RELOADED(MESSAGES.getFileManager().getString("reload")),
	HUB_SET(MESSAGES.getFileManager().getString("hubSet")),
	HUB_SET_OTHERS(MESSAGES.getFileManager().getString("hubSetOthers")),
	HUB_NOT_FOUND(MESSAGES.getFileManager().getString("noHub")),
	HUB_TELEPORT(MESSAGES.getFileManager().getString("hubTeleport")),
	HUB_TELEPORT_OTHERS(MESSAGES.getFileManager().getString("hubTeleportOthers")),
	SAVE_WORLD(MESSAGES.getFileManager().getString("saveWorld")),
	NO_WORLD(MESSAGES.getFileManager().getString("noWorld")),
	STOP(MESSAGES.getFileManager().getString("stop")),
	STOP_CONFIRM(MESSAGES.getFileManager().getString("stopConfirm")),
	FIRST_STOP(MESSAGES.getFileManager().getString("firstStop")),
	NO_GAMEMODE(MESSAGES.getFileManager().getString("noGamemode")),
	CHANGE_GAMEMODE(MESSAGES.getFileManager().getString("changeGamemode")),
	CHANGE_GAMEMODE_OTHERS(MESSAGES.getFileManager().getString("changeGamemodeOthers")),
	WRONG_FORMAT_GAMEMODE(MESSAGES.getFileManager().getString("wrongFormatGamemode")),
	WRONG_FORMAT_JOIN(MESSAGES.getFileManager().getString("wrongFormatJoin")),
	DISABLED_JOINING(MESSAGES.getFileManager().getString("disabledJoining")), 
	JOINING(MESSAGES.getFileManager().getString("joining")),
	JOINING_OTHER(MESSAGES.getFileManager().getString("joiningOthers"));

	String message;
	List<String> messages;

	Messages(String str) {
		this.message = str;
	}

	Messages(List<String> str) {
		this.messages = str;
	}

	public List<String> getMessages() {
		return this.messages;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
