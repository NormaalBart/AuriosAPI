package me.BartVV.AuriosAPI.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Events.ConsoleMSGEvent;
import me.BartVV.AuriosAPI.Events.PlayerMSGEvent;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class MSGCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User from = User.getUser(((Player) cs).getUniqueId());
			if (args.length <= 1) {
				from.sendMessage(Messages.NOT_ENOUGH_ARGUMENT_MSG.getMessage());
				return true;
			}
			User to = User.getUser(args[0]);
			if (to == null) {
				from.sendMessage(Messages.NO_PLAYER.getMessage());
				return true;
			}
			if (to.containsIgnore(from.getUUID())) {
				from.sendMessage(Messages.DISABLED_MSG);
				return true;
			}
			if (!to.mayMSG() && !from.hasPermission("AuriosAPI.msg.override")) {
				from.sendMessage(Messages.DISABLED_MSG.getMessage());
				return true;
			}
			String msg = null;
			try {
				for (Integer i = 1; i < args.length; i++) {
					if (msg == null) {
						msg = args[i];
					} else {
						msg = msg + " " + args[i];
					}
				}
			} catch (Exception e) {
				/* Do nothing with it since it's probably a NPE */}
			PlayerMSGEvent pmsge = new PlayerMSGEvent(from.getBase(), to.getBase(), msg);
			Bukkit.getPluginManager().callEvent(pmsge);
			if (pmsge.isCancelled()) {
				from.sendMessage(Messages.DISABLED_MSG.getMessage());
				return true;
			}
			to.setLastreply(from.getUUID());
			from.setLastreply(to.getUUID());
			from.sendMessage(Messages.MESSAGE_SEND.getMessage().replace("(TO)", to.getBase().getDisplayName())
					.replace("(FROM)", from.getBase().getDisplayName()).replace("(MESSAGE)", msg));
			msg = pmsge.getMessage();
			to.sendMessage(msg);
			return true;
		} else {
			if (args.length >= 2) {
				User u = User.getUser(args[0]);
				if (u == null) {
					cs.sendMessage(Messages.NO_PLAYER.getMessage());
					return true;
				}
				String msg = null;
				try {
					for (Integer i = 1; i < args.length; i++) {
						if (msg == null) {
							msg = args[i];
						} else {
							msg = msg + " " + args[i];
						}
					}
				} catch (Exception e) {
					/* Do nothing with it since it's probably a NPE */}
				ConsoleMSGEvent cmsge = new ConsoleMSGEvent(cs, u.getBase(), msg);
				Bukkit.getPluginManager().callEvent(cmsge);
				if (cmsge.isCancelled()) {
					cs.sendMessage(Messages.DISABLED_MSG.getMessage());
					return true;
				}
				cs.sendMessage(Messages.MESSAGE_SEND.getMessage().replace("(TO)", u.getBase().getDisplayName())
						.replace("(FROM)", "Console").replace("(MESSAGE)", msg));
				msg = cmsge.getMessage();
				u.sendMessage(msg);
				return true;
			} else {
				cs.sendMessage(Messages.NOT_ENOUGH_ARGUMENT_MSG.getMessage());
				return true;
			}
		}
	}
}
