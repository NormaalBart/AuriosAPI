package me.BartVV.AuriosAPI.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.FileTypes;
import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class SetHubCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (!u.hasPermission("auriosapi.sethub")) {
				u.sendMessage(Messages.NO_PERMISSION);
				return true;
			}
			if (args.length == 1) {
				User t = User.getUser(args[0]);
				if (t == null) {
					u.sendMessage(Messages.NO_PLAYER);
					return true;
				}
				Location loc = t.getBase().getLocation();
				FileTypes.CONFIG.getFileManager().set("Location.hub", loc, true);
				u.sendMessage(Messages.HUB_SET_OTHERS.getMessage().replace("(PLAYER)", t.getBase().getDisplayName()));
				return true;
			} else {
				Location loc = u.getBase().getLocation();
				FileTypes.CONFIG.getFileManager().set("Location.hub", loc, true);
				u.sendMessage(Messages.HUB_SET);
				return true;
			}
		} else {
			if (args.length == 1) {
				User t = User.getUser(args[0]);
				if (t == null) {
					cs.sendMessage(Messages.NO_PLAYER.getMessage());
					return true;
				}
				Location loc = t.getBase().getLocation();
				FileTypes.CONFIG.getFileManager().set("Location.hub", loc, true);
				cs.sendMessage(Messages.HUB_SET_OTHERS.getMessage().replace("(PLAYER)", t.getBase().getDisplayName()));
				return true;
			}
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;
		}
	}
}
