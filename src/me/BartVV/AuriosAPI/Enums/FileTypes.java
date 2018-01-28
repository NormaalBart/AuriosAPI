package me.BartVV.AuriosAPI.Enums;

import me.BartVV.AuriosAPI.Manager.FileManager;

public enum FileTypes {
	
	MESSAGES(FileManager.getFileManager("messages.yml")), 
	CONFIG(FileManager.getFileManager("config.yml"));
	
	private FileManager fm;
	
	FileTypes(FileManager fm){
		this.fm = fm;
	}
	
	public FileManager getFileManager(){
		return this.fm;
	}

}
