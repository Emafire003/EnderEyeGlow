package me.emafire003.dev.endereyeglow.compat.yacl;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import me.emafire003.dev.endereyeglow.config.YaclConfig;

public class ConfigComapt {

    public static YaclConfig getConfig(){
        return YaclConfig.HANDLER.instance();
    }

    public static void loadConfig(){
        YaclConfig.HANDLER.load();
    }

    public static ConfigClassHandler<YaclConfig> getHandler(){
        return YaclConfig.HANDLER;
    }
}
