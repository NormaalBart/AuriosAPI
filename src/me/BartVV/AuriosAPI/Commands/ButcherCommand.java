package me.BartVV.AuriosAPI.Commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class ButcherCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (!u.hasPermission("AuriosAPI.butcher")) {
				u.sendMessage(Messages.NO_PERMISSION);
				return true;
			}
			World w = u.getBase().getWorld();
			for (Entity e : w.getEntities()) {
				if (!(e instanceof Player)) {
					if (!(e instanceof ItemFrame)) {
						e.eject();
						e.remove();
					}
				}
			}
			u.sendMessage(Messages.BUTCHER);
			return true;
		}
		return true;
	}

}
