package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.PacketEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketCloseWindow;

public class InventoryPlus extends Module {
	
	public InventoryPlus() {
		super ("inventoryPlus", "lets u hold extra items in ur crafting gui.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEnabled() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	 @EventHandler
	 private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
		 if (event.getPacket() instanceof CPacketCloseWindow) {
	         final CPacketCloseWindow packet = (CPacketCloseWindow) event.getPacket();
	         if (packet.windowId == Minecraft.getMinecraft().player.inventoryContainer.windowId) {
	             event.cancel();
	         }
		 }
	 });
}