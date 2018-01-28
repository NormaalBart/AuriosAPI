package me.BartVV.AuriosAPI.Interfaces;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public interface CommandM extends CommandExecutor, TabExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args);

	@Override
	public default List<String> onTabComplete(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		return null;
	}
}
