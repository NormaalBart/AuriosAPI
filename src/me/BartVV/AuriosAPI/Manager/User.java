package me.BartVV.AuriosAPI.Manager;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import me.BartVV.AuriosAPI.Interfaces.MainMessage;

public class User {

	private static HashMap<UUID, User> users = Maps.newHashMap();

	public static User getUser(UUID uuid) {
		User user = users.get(uuid);
		if (user == null) {
			if (Bukkit.getPlayer(uuid) == null)
				return null;
			user = new User(uuid);
		}
		return users.get(uuid);
	}

	public static User getUser(String user) {
		Player p = Bukkit.getPlayer(user);
		if(p == null) return null;
		return getUser(p.getUniqueId());
	}
	
	private UUID uuid;
	private UUID lastreply;
	private UUID follow;
	private Set<UUID> ignore;
	private Set<String> nameIgnore;
	boolean mayFollow, mayMSG, stopConfirm, mayJoin;

	public User(UUID uuid) {
		if(!users.containsKey(uuid)){
			Bukkit.broadcastMessage("Loading (AuriosAPI)");
			this.uuid = uuid;
			this.mayFollow = true;
			this.mayJoin = true;
			this.mayMSG = true;
			this.ignore = Sets.newHashSet();
			this.nameIgnore = Sets.newHashSet();
			this.stopConfirm = false;
			users.put(uuid, this);	
		}
	}

	public Player getBase() {
		return Bukkit.getPlayer(this.uuid);
	}

	public UUID getLastreply() {
		return lastreply;
	}

	public void setLastreply(UUID lastreply) {
		this.lastreply = lastreply;
	}

	public UUID getFollow() {
		return follow;
	}

	public void setFollow(UUID follow) {
		this.follow = follow;
	}

	public boolean isMayFollow() {
		return mayFollow;
	}

	public void setMayFollow(boolean mayFollow) {
		this.mayFollow = mayFollow;
	}

	public Boolean hasPermission(String permission) {
		// PermissionCheckEvent pce = new
		// PermissionCheckEvent(Bukkit.getPlayer(this.uuid), permission);
		// Bukkit.getPluginManager().callEvent(pce);
		// return pce.hasPerm();
		return Bukkit.getPlayer(this.uuid).hasPermission(permission);
	}

	public boolean mayMSG() {
		return mayMSG;
	}

	public void setMayMSG(boolean mayMSG) {
		this.mayMSG = mayMSG;
	}

	public void remove() {
		// TODO having to do...
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public void sendMessage(String message) {
		this.getBase().sendMessage(message);
	}

	public void sendMessage(MainMessage message) {
		this.sendMessage(message.getMessage());
	}

	public boolean containsIgnore(UUID uuid) {
		return this.ignore.contains(uuid);
	}

	public boolean addIgnore(UUID uuid) {
		this.nameIgnore.add(Bukkit.getPlayer(uuid).getName());
		return this.ignore.add(uuid);
	}

	public boolean removeIgnore(UUID uuid) {
		this.nameIgnore.remove(Bukkit.getPlayer(uuid).getName());
		return this.ignore.remove(uuid);
	}

	public Set<String> getNameIgnores() {
		return this.nameIgnore;
	}

	public Boolean autoStop() {
		return this.stopConfirm;
	}

	public void setAutoStop(Boolean stopConfirm) {
		this.stopConfirm = stopConfirm;
	}

	public boolean mayJoin() {
		return mayJoin;
	}

	public void setMayJoin(boolean mayJoin) {
		this.mayJoin = mayJoin;
	}
}
