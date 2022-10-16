package de.rehfeldinio.motd;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;


public class MOTDChange extends JavaPlugin implements Listener {

    Random random;

    @Override
    public void onEnable() {
        LoadConfig();
        if ((this.getConfig().getBoolean("Motd.Enabled")) && (!this.getConfig().getStringList("Motd.Texts").isEmpty())) {
            this.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @Override
    public void onDisable() {
    }

    public void LoadConfig() {
        reloadConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void setMotd(ServerListPingEvent s) {
        this.random = new Random();
        ArrayList motd = (ArrayList) this.getConfig().getStringList("Motd.Texts");
        int max = motd.size();
        int text = random.nextInt(max);
        s.setMotd(TranslateColor((String) motd.get(text)));
    }

    public static String TranslateColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message.replace("\\\\n", "\n").replace("\\n", "\n").replace("&nl", "\n"));
    }
}