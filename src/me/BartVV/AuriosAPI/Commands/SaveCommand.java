package me.BartVV.AuriosAPI.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class SaveCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (!u.hasPermission("AuriosAPI.save.world")) {
				u.sendMessage(Messages.NO_PERMISSION);
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("all")) {
					String world = "";
					for (World w : Bukkit.getWorlds()) {
						w.save();
						if (world.equals("")) {
							world = w.getName();
						} else {
							world = world + ", " + w.getName();
						}
					}
					u.sendMessage(Messages.SAVE_WORLD.getMessage().replace("(WORLD)", world));
					return true;
				}
				World w = Bukkit.getWorld(args[0]);
				if (w == null) {
					u.sendMessage(Messages.NO_WORLD);
					return true;
				}
				w.save();
				u.sendMessage(Messages.SAVE_WORLD.getMessage().replace("(WORLD)", w.getName()));
				return true;
			}
			World w = u.getBase().getWorld();
			w.save();
			u.sendMessage(Messages.SAVE_WORLD.getMessage().replace("(WORLD)", w.getName()));
			return true;
		} else {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("all")) {
					String world = "";
					for (World w : Bukkit.getWorlds()) {
						w.save();
						if (world.equals("")) {
							world = w.getName();
						} else {
							world = world + ", " + w.getName();
						}
					}
					cs.sendMessage(Messages.SAVE_WORLD.getMessage().replace("(WORLD)", world));
					return true;
				}
				World w = Bukkit.getWorld(args[0]);
				if (w == null) {
					cs.sendMessage(Messages.NO_WORLD.getMessage());
					return true;
				}
				w.save();
				cs.sendMessage(Messages.SAVE_WORLD.getMessage().replace("(WORLD)", w.getName()));
				return true;
			} else {
				cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
				return true;
			}
		}
	}

}
