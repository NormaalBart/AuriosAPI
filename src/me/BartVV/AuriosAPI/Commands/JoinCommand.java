package me.BartVV.AuriosAPI.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class JoinCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (args.length != 1) {
				u.sendMessage(Messages.WRONG_FORMAT_JOIN);
				return true;
			}
			User t = User.getUser(args[0]);
			if (t == null) {
				u.sendMessage(Messages.NO_PLAYER);
				return true;
			}
			if (t.mayJoin()) {
				// Check if player is in a gamemode
				// TODO GET GAME OF PLAYER & TELEPORT TO IT!
				u.sendMessage("First have to make the Minigame Core Plugin, Then can hook into it :)");
				return true;
			} else {
				u.sendMessage(Messages.DISABLED_JOINING);
				return true;
			}
		} else {
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;
		}
	}

}
