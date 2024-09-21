package me.emafire003.dev.endereyeglow.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.ColorField;
import dev.isxander.yacl3.config.v2.api.autogen.TickBox;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import me.emafire003.dev.endereyeglow.EnderEyeGlow;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.awt.Color;


public class YaclConfig {

    public static ConfigClassHandler<YaclConfig> HANDLER = ConfigClassHandler.createBuilder(YaclConfig.class)
            .id(Identifier.of(EnderEyeGlow.MOD_ID, "config"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FabricLoader.getInstance().getConfigDir().resolve("endereyeglow/config.json5"))
                            .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry
    @AutoGen(category = "EnderEyeGlow")
    @TickBox
    public boolean showDroppedItemParticles = true;

    @SerialEntry
    @AutoGen(category = "EnderEyeGlow")
    @TickBox
    public boolean showEnderEyeGlowOutline = true;

    @SerialEntry
    @AutoGen(category = "EnderEyeGlow")
    @ColorField()
    //Color.getColor("#09964d")
    public Color enderEyeGlowColor = new Color(9, 150,77);

}