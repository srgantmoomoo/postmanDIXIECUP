package me.srgantmoomoo.postman.client.module.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;


public class KillAuraInfo extends HudModule {
	private KillAuraInfoList list=new KillAuraInfoList();
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(230, 0, 0, 255)); 
	public BooleanSetting sort = new BooleanSetting("sortRight", this, false);


	public KillAuraInfo() {
		super("killAuraInfo", "shows if kill aura is on or off.", new Point(-3,49), Category.HUD);
		this.addSettings(sort, color);
	}
	
	@Override
	public void populate (Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, list);
	}
	
	private class KillAuraInfoList implements HUDList {

		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			if (ModuleManager.isModuleEnabled("killAura")) return ChatFormatting.GREEN + "killA" +  " on";
			else return "killA" + " off";
		}

		@Override
		public Color getItemColor(int index) {
			return color.getValue();
		}

		@Override
		public boolean sortUp() {
			return false;
		}

		@Override
		public boolean sortRight() {
			return sort.isEnabled();
		}
	}
}