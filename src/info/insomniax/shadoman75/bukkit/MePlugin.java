package info.insomniax.shadoman75.bukkit;

import info.insomniax.shadoman75.bukkit.permissions.PermissionsHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public final class MePlugin extends JavaPlugin {
	
	public void onDisable(){
		getLogger().info("Disabled... for now");
	}
	public void onEnable(){
		getLogger().info("Enabled... for now");
		permissions = new PermissionsHandler(this);
		if(permissions.setupPermissions()){
			getLogger().info("Setting Up Permissions");
	
		} else {
			getLogger().info("failed to setup Permissions");
			this.getServer().getPluginManager().disablePlugin(this);
		}
	}
	public PermissionsHandler permissions = null;
	

	public boolean onCommand(CommandSender sender, Command cmd,
		String commandLabel, String[] args) {
		
		// if someone just happened to type /me and had a message after it...
		if(cmd.getName().equalsIgnoreCase("pme")){
			
			if(permissions.has(sender, "meplugin.pme")){
			
				if(args.length > 0){
					
					// ARGUMENT LOOP START!
					StringBuilder buffer = new StringBuilder();

					for(int i = 0; i < args.length; i++)
					{
						buffer.append(' ').append(args[i]);
					}
					// argument loop end...
					// PLAYER LOOPZ!
					if(sender.isOp())
						Bukkit.broadcastMessage(ChatColor.GREEN + "-" + sender.getName() + "'s" + buffer.toString());
						return true;
						
					} else {
						// ARGUMENT LOOP START!
						StringBuilder buffer = new StringBuilder();

						for(int i = 0; i < args.length; i++)
						{
							buffer.append(' ').append(args[i]);
						}
						// argument loop end..
						for(Player p:((Player)sender).getWorld().getPlayers()){
							// and the message will be sent here :o
							p.sendMessage(ChatColor.AQUA + "~" + " " + sender.getName() + "'s" + buffer.toString());
							return true;
						
						
						
						
						
					}
				} 
			}
		}
		return false;
	}
}