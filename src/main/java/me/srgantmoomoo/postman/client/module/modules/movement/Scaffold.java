package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class Scaffold extends Module {
	
	public Scaffold() {
		super ("scaffold", "places blocks under u automatically.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

}
