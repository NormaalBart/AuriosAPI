package me.BartVV.AuriosAPI.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Events.PlayerMSGEvent;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class ReplyCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User from = User.getUser(((Player) cs).getUniqueId());
			if (args.length == 0) {
				from.getBase().sendMessage(Messages.NOT_ENOUGH_ARGUMENT_REPLY.getMessage());
				return true;
			}
			User to = User.getUser(from.getLastreply());
			if (to == null || Bukkit.getPlayer(to.getUUID()) == null) {
				from.getBase().sendMessage(Messages.NO_PLAYER.getMessage());
				return true;
			}
			if (to.containsIgnore(from.getUUID())) {
				from.sendMessage(Messages.DISABLED_MSG);
				return true;
			}
			if (!to.mayMSG() && !from.hasPermission("AuriosAPI.msg.override")) {
				from.getBase().sendMessage(Messages.DISABLED_MSG.getMessage());
				return true;
			}
			String msg = null;
			try {
				for (Integer i = 0; i < args.length; i++) {
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
				from.getBase().sendMessage(Messages.DISABLED_MSG.getMessage());
				return true;
			}
			to.setLastreply(from.getUUID());
			from.setLastreply(to.getUUID());
			from.sendMessage(Messages.MESSAGE_SEND.getMessage().replace("(TO)", to.getBase().getDisplayName())
					.replace("(FROM)", "Console").replace("(MESSAGE)", msg));
			msg = pmsge.getMessage();
			Bukkit.getPlayer(to.getUUID()).sendMessage(msg);
			from.getBase()
					.sendMessage(Messages.MESSAGE_SEND.getMessage().replace("(PLAYER)", to.getBase().getDisplayName()));
			return true;
		} else {
			cs.sendMessage("Replying from console isn't supported yet... Try /msg (name) (message) !");
			return true;
		}
	}

}
