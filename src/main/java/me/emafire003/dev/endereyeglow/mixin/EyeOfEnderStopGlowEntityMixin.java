package me.emafire003.dev.endereyeglow.mixin;

import me.emafire003.dev.endereyeglow.EnderEyeGlow;
import me.emafire003.dev.endereyeglow.compat.cgl.CGLCompat;
import me.emafire003.dev.endereyeglow.compat.yacl.ConfigComapt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.emafire003.dev.endereyeglow.EnderEyeGlow.YACL_ID;

@Mixin(EyeOfEnderEntity.class)
public abstract class EyeOfEnderStopGlowEntityMixin extends Entity {

    public EyeOfEnderStopGlowEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    boolean glowingAlready = false;

    @Inject(method = "tick", at = @At("HEAD"))
    public void injectGlowingOnUse(CallbackInfo ci){
        //Prevents the glowing if it's disabled in the config
        if(FabricLoader.getInstance().isModLoaded(YACL_ID)){
            if(!ConfigComapt.getConfig().showEnderEyeGlowOutline){
                return;
            }
        }

        if(!glowingAlready){
            glowingAlready = true;
            EnderEyeGlow.addGlowingEntityForPlayer(this.getUuid());

            if(FabricLoader.getInstance().isModLoaded("coloredglowlib")){
                CGLCompat.setEyeColor(this);
            }
        }

    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EyeOfEnderEntity;discard()V"))
    public void injectStopGlow(CallbackInfo ci){
        //Prevents the glowing if it's disabled in the config
        if(FabricLoader.getInstance().isModLoaded(YACL_ID)){
            if(!ConfigComapt.getConfig().showEnderEyeGlowOutline){
                return;
            }
        }

        EnderEyeGlow.removeGlowingEntityForPlayer(this.getUuid());

        if(FabricLoader.getInstance().isModLoaded("coloredglowlib")){
            CGLCompat.clearEyeColor(this);
        }
    }
}
