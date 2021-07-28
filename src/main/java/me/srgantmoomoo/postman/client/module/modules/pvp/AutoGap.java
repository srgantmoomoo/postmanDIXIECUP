package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

public class AutoGap extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "always", "always", "smart");
	public NumberSetting health = new NumberSetting("health", this, 16, 1, 20, 1);
	public ModeSetting disableOn = new ModeSetting("disableOn", this, "switchToCrystal", "switchToCrystal", "autoCrystalEnabled");
	public BooleanSetting disableOnSurround = new BooleanSetting("disableOnSurround", this, false);
	
	public AutoGap() {
		super("autoGap", "automattically eat any gapples in ur hand.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode, health, disableOnSurround);;
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisbale() {
		Main.EVENT_BUS.unsubscribe(this);
		
		 if (wasEating) {
			 wasEating = false;
	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
		 }
	}
	
	private boolean wasEating = false;
	
	public void onUpdate() {
		if(mode.is("always")) {
				if(mc.gameSettings.keyBindSprint.isKeyDown()) mc.player.setSprinting(true);
				eatGap();
		}
		
		if(mode.is("smart")) {
			if(mc.player.getHealth() <= health.getValue()) eatGap();
			
			if (wasEating && mc.player.getHealth() >= health.getValue()) {
				wasEating = false;
	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
	        }
		}
		
		if(disableOnSurround.isEnabled()) {
			if(((Surround)ModuleManager.getModuleByName("surround")).shiftOnly.isEnabled()) {
				if(mc.player.isSneaking()) toggle();
			}else {
				if(ModuleManager.isModuleEnabled("surround")) toggle();
			}
		}
	}
	
	public void eatGap() {
			if(mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE || mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
				if(mc.currentScreen == null) {
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
					wasEating = true;
				}else {
		            mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
				}
			}
	}
}