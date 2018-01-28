package me.BartVV.AuriosAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.BartVV.AuriosAPI.Commands.AReloadCommand;
import me.BartVV.AuriosAPI.Commands.ButcherCommand;
import me.BartVV.AuriosAPI.Commands.FlyCommand;
import me.BartVV.AuriosAPI.Commands.GamemodeCommand;
import me.BartVV.AuriosAPI.Commands.HelpCommand;
import me.BartVV.AuriosAPI.Commands.HubCommand;
import me.BartVV.AuriosAPI.Commands.IgnorePMCommand;
import me.BartVV.AuriosAPI.Commands.JoinCommand;
import me.BartVV.AuriosAPI.Commands.MSGCommand;
import me.BartVV.AuriosAPI.Commands.ReplyCommand;
import me.BartVV.AuriosAPI.Commands.SaveCommand;
import me.BartVV.AuriosAPI.Commands.SetHubCommand;
import me.BartVV.AuriosAPI.Commands.StopCommand;
import me.BartVV.AuriosAPI.Commands.StopConfirmCommand;
import me.BartVV.AuriosAPI.Commands.ToggleJoinCommand;
import me.BartVV.AuriosAPI.Commands.TogglePMCommand;
import me.BartVV.AuriosAPI.Listener.ConsoleMSG;
import me.BartVV.AuriosAPI.Listener.JoinEvent;
import me.BartVV.AuriosAPI.Listener.PlayerMSG;
import me.BartVV.AuriosAPI.Listener.QuitEvent;
import me.BartVV.AuriosAPI.Manager.FileManager;
import me.BartVV.AuriosAPI.Manager.User;

public class Core extends JavaPlugin {

	@Override
	public void onEnable() {

		new FileManager(this, "messages.yml", "messages.yml");
		new FileManager(this, "config.yml", "config.yml");

		getCommand("areload").setExecutor(new AReloadCommand());
		getCommand("butcher").setExecutor(new ButcherCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("hub").setExecutor(new HubCommand());
		getCommand("ignorepm").setExecutor(new IgnorePMCommand());
		getCommand("join").setExecutor(new JoinCommand());
		getCommand("msg").setExecutor(new MSGCommand());
		getCommand("reply").setExecutor(new ReplyCommand());
		getCommand("save").setExecutor(new SaveCommand());
		getCommand("sethub").setExecutor(new SetHubCommand());
		getCommand("stop").setExecutor(new StopCommand());
		getCommand("stopconfirm").setExecutor(new StopConfirmCommand(this));
		getCommand("togglejoin").setExecutor(new ToggleJoinCommand());
		getCommand("togglepm").setExecutor(new TogglePMCommand());

		getServer().getPluginManager().registerEvents(new ConsoleMSG(), this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		getServer().getPluginManager().registerEvents(new PlayerMSG(), this);
		getServer().getPluginManager().registerEvents(new QuitEvent(), this);

		for(Player p : Bukkit.getOnlinePlayers()){
			new User(p.getUniqueId());
		}
		super.onEnable();
	}

	@Override
	public void onDisable() {
		
		super.onDisable();
	}

}
