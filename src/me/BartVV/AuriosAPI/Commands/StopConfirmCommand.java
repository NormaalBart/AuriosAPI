package me.BartVV.AuriosAPI.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class StopConfirmCommand implements CommandM {

	private Plugin pl;

	public StopConfirmCommand(Plugin pl) {
		this.pl = pl;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (u.autoStop()) {
				u.sendMessage(Messages.STOP_CONFIRM);
				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.shutdown();
					}
				}.runTaskLater(pl, 30L);
				return true;
			} else {
				u.sendMessage(Messages.FIRST_STOP);
				return true;
			}
		} else {
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		return null;
	}

}
