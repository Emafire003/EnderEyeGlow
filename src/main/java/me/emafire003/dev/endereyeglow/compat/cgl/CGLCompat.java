package me.emafire003.dev.endereyeglow.compat.cgl;

import me.emafire003.dev.coloredglowlib.ColoredGlowLibAPI;
import me.emafire003.dev.coloredglowlib.ColoredGlowLibMod;
import me.emafire003.dev.endereyeglow.compat.yacl.ConfigComapt;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static me.emafire003.dev.endereyeglow.EnderEyeGlow.YACL_ID;

public class CGLCompat {

    public static @Nullable ColoredGlowLibAPI getLib(){
        return ColoredGlowLibMod.getColoredGlowLib();
    }

    @Environment(EnvType.CLIENT)
    public static void setEyeColor(Entity eye){
        if(!FabricLoader.getInstance().isModLoaded("coloredglowlib")){
            return;
        }
        if(getLib() == null){
            ColoredGlowLibAPI coloredGlowLib = new ColoredGlowLibAPI(eye.getWorld().getScoreboard());

            if(FabricLoader.getInstance().isModLoaded(YACL_ID)){
                String color = "#"+Integer.toHexString(ConfigComapt.getConfig().enderEyeGlowColor.getRGB()).substring(2);
                coloredGlowLib.setExclusiveColorFor(eye, color, MinecraftClient.getInstance().player);
            }else{
                coloredGlowLib.setExclusiveColorFor(eye, "#09964d", MinecraftClient.getInstance().player);
            }

        }else{
            if(FabricLoader.getInstance().isModLoaded(YACL_ID)){
                String color = "#"+Integer.toHexString(ConfigComapt.getConfig().enderEyeGlowColor.getRGB()).substring(2);
                getLib().setExclusiveColorFor(eye, color, MinecraftClient.getInstance().player);
            }else{
                getLib().setExclusiveColorFor(eye, "#09964d", MinecraftClient.getInstance().player);
            }
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
            getLib().clearExclusiveColorFor(eye, MinecraftClient.getInstance().player, true);
        }
        Objects.requireNonNull(getLib()).clearExclusiveColorFor(eye, MinecraftClient.getInstance().player, true);
    }
}
