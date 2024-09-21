package me.emafire003.dev.endereyeglow.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.emafire003.dev.endereyeglow.compat.yacl.ConfigComapt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.net.URI;

import static me.emafire003.dev.endereyeglow.EnderEyeGlow.YACL_ID;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuIntegration::createConfigScreen;
    }

    /**Create a config screen for ModMenu if YACL is present, or
     * a confirmation screen otherwise to tell you to download yacl*/
    public static Screen createConfigScreen(Screen parent) {
        if (!FabricLoader.getInstance().isModLoaded(YACL_ID)) {
            return new ConfirmScreen((result) -> {
                if (result) {
                    Util.getOperatingSystem().open(URI.create("https://modrinth.com/mod/yacl/versions"));
                }
                MinecraftClient.getInstance().setScreen(parent);
            },
                    Text.literal("You need to install YACL"), Text.literal("To modify the config file with a GUI you need to install YACL. Requires version 3.0.0 or newer of YACL. Click on yes to get the modrinth page to download it."), ScreenTexts.YES, ScreenTexts.NO);
        } else if(ConfigComapt.getHandler().supportsAutoGen()){
            return ConfigComapt.getHandler().generateGui().generateScreen(parent);
        }else{
            return new ConfirmScreen((result) -> {
                if (result) {
                    Util.getOperatingSystem().open(URI.create("https://github.com/Emafire003/EnderEyeGlow"));
                }
                MinecraftClient.getInstance().setScreen(parent);
            },
                    Text.literal("An unknown error involving YACL"), Text.literal("An unknown error linked to the config and YACL has occurred, click on yes to report an issue on the github page of EnderEyeGlow"), ScreenTexts.YES, ScreenTexts.NO);
        }
    }
}
