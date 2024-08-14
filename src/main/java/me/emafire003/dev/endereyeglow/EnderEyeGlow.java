package me.emafire003.dev.endereyeglow;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnderEyeGlow implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("endereyeglow");

	//Whenever a new eye of ender entity gets created it will add it to the list

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Eyes of ender will now glow when thrown! (EnderEyeGlow mod loaded!)");
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

	/*private void registerGlowingEntitiesPacket(){
		LOGGER.debug("Registering glowing entities packet...");
		ClientPlayNetworking.registerGlobalReceiver(GlowEntitiesPacketS2C.ID, ((client, handler, buf, responseSender) -> {
			List<Pair<UUID, ForestAuraRelation>> results = GlowEntitiesPacketS2C.read(buf);

			client.execute(() -> {
				try{
					if(results == null){
						LOGGER.error("The glowing entities list received is empty!");
						return;
					}if(client.player == null){
						LOGGER.error("The client player is null!");
						return;
					}else if(results.size() == 1 && results.get(0).getFirst().equals(new UUID(0,0))){
						//Clears CGL exclusive colors on client side.
						if(FabricLoader.getInstance().isModLoaded("coloredglowlib")){
							entitiesGlowingForPlayer.forEach(uuid -> {
								Entity entity = null;
								for(Entity entity1 : client.player.clientWorld.getEntities()){
									if(entity1.getUuid().equals(uuid)){
										entity = entity1;
									}
								}
								if(entity != null){
									CGLCompat.getLib().clearExclusiveColorFor(entity, client.player, false);
								}
							});
						}

						entitiesGlowingForPlayer.clear();
						return;
					}
					//If nothing else, it means it's ok to make them glow:

					results.forEach(uuidRelationPair -> {
						entitiesGlowingForPlayer.add(uuidRelationPair.getFirst());
						if(FabricLoader.getInstance().isModLoaded("coloredglowlib")){

							Entity entity = null;
							for(Entity entity1 : client.player.clientWorld.getEntities()){
								if(entity1.getUuid().equals(uuidRelationPair.getFirst())){
									entity = entity1;
								}
							}
							if(entity == null){
								LOGGER.error("Error! Can't find entity with uuid: {}", uuidRelationPair.getFirst());
								return;
							}

							if(uuidRelationPair.getSecond().equals(ForestAuraRelation.ALLY)){
								CGLCompat.getLib().setExclusiveColorFor(entity, ClientConfig.FORESTAURA_ALLY_COLOR, client.player);
							}else if(uuidRelationPair.getSecond().equals(ForestAuraRelation.ENEMY)){
								CGLCompat.getLib().setExclusiveColorFor(entity, ClientConfig.FORESTAURA_ENEMY_COLOR, client.player);
							}else{
								CGLCompat.getLib().setExclusiveColorFor(entity, ForestAuraLight.COLOR, client.player);
							}
						}
					} );

				}catch (NoSuchElementException e){
					LOGGER.warn("No value in the packet, probably not a big problem");
				}catch (Exception e){
					LOGGER.error("There was an error while getting the packet!");
					e.printStackTrace();
				}
			});
		}));
	}*/
}