package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerManager {

    public void restart(){
        Bukkit.getServer().shutdown();
    }

    public void kickAll(boolean usePermission, String reason) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (usePermission && Permissions.hasPermission(p, Permissions.KICKALL_PROTECTION)) {
                return;
            }
            if (reason != null && reason.length() > 0) {
                p.kickPlayer("§6You have been kicked by system. Reason: §e" + reason);
            } else {
                p.kickPlayer("§6You have been kicked by system.");
            }
        }
    }

}
