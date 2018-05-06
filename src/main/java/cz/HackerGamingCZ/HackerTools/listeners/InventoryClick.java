package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.GUI;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Item;
import cz.HackerGamingCZ.HackerTools.events.Event;
import cz.HackerGamingCZ.HackerTools.events.ItemInInventoryClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener{

    @EventHandler
    public void onItemClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player player = (Player) e.getWhoClicked();
        GUI gui = HackerTools.getPlugin().getGUIAPI().getGUI(inv.getTitle());
        if(gui == null){
            return;
        }
        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){
            return;
        }
        Item item = gui.getItembyIS(e.getCurrentItem());
        Event event = item.getEvent();
        if(event == null){
            return;
        }
        if(!(event instanceof ItemInInventoryClickEvent)){
            return;
        }
        event.getAction().cast(player);
        e.setCancelled(item.isCanceled());
        if(item.isClosingInventory()){
            player.closeInventory();
        }
    }

}
