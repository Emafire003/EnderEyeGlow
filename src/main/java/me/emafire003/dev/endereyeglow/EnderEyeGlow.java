package me.emafire003.dev.endereyeglow;

import me.emafire003.dev.endereyeglow.compat.yacl.ConfigComapt;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnderEyeGlow implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "endereyeglow";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String YACL_ID = "yet_another_config_lib_v3";

	//Whenever a new eye of ender entity gets created it will add it to the list

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Eyes of ender will now glow when thrown! (EnderEyeGlow mod loaded!)");
		if(FabricLoader.getInstance().isModLoaded(YACL_ID)){
			ConfigComapt.loadConfig();
			LOGGER.info("Loading the config as well!");
		}
	}

	private static final List<UUID> entitiesGlowingForPlayer = new ArrayList<>();

	public static List<UUID> getEntitiesGlowingForPlayer() {
		return entitiesGlowingForPlayer;
	}

	public static void addGlowingEntityForPlayer(UUID entityUUID){
		entitiesGlowingForPlayer.add(entityUUID);
	}

	public static void removeGlowingEntityForPlayer(UUID entityUUID){
		entitiesGlowingForPlayer.remove(entityUUID);
	}

}