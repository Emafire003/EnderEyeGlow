package me.emafire003.dev.endereyeglow.compat.cgl;

import me.emafire003.dev.coloredglowlib.ColoredGlowLibAPI;
import me.emafire003.dev.coloredglowlib.ColoredGlowLibMod;
import me.emafire003.dev.endereyeglow.EnderEyeGlow;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CGLCompat {

    public static @Nullable ColoredGlowLibAPI getLib(){
        return ColoredGlowLibMod.getColoredGlowLib();
    }

    //TODO i think CGL is not working huh
    @Environment(EnvType.CLIENT)
    public static void setEyeColor(Entity eye){
        if(!FabricLoader.getInstance().isModLoaded("coloredglowlib")){
            return;
        }
        if(getLib() == null){
            ColoredGlowLibAPI coloredGlowLib = new ColoredGlowLibAPI(eye.getWorld().getScoreboard());
            coloredGlowLib.setExclusiveColorFor(eye, "#09964d", MinecraftClient.getInstance().player);
        }else{
            getLib().setExclusiveColorFor(eye, "#09964d", MinecraftClient.getInstance().player);
        }
    }

    @Environment(EnvType.CLIENT)
    public static void clearEyeColor(Entity eye){
        if(!FabricLoader.getInstance().isModLoaded("coloredglowlib")){
            return;
        }
        if(getLib() == null){
            ColoredGlowLibAPI coloredGlowLib = new ColoredGlowLibAPI(eye.getWorld().getScoreboard());
            coloredGlowLib.clearExclusiveColorFor(eye, MinecraftClient.getInstance().player, true);
        }else{
            getLib().setExclusiveColorFor(eye, "#09964d", MinecraftClient.getInstance().player);
        }
        Objects.requireNonNull(getLib()).clearExclusiveColorFor(eye, MinecraftClient.getInstance().player, true);
    }

    public static String getModID(){
        return "coloredglowlib";
    }
}
