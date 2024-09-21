package me.emafire003.dev.endereyeglow.mixin;

import me.emafire003.dev.endereyeglow.compat.yacl.ConfigComapt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.emafire003.dev.endereyeglow.EnderEyeGlow.YACL_ID;

@Mixin(ItemEntity.class)
public abstract class EyeItemEntityParticlesMixin extends Entity {

    @Shadow public abstract ItemStack getStack();

    public EyeItemEntityParticlesMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void injectParticlesIfEnderEye(CallbackInfo ci){

        //Stops the particles from spawning if the setting is disabled in the settings
        if(FabricLoader.getInstance().isModLoaded(YACL_ID)){
            if(!ConfigComapt.getConfig().showDroppedItemParticles){
                return;
            }
        }

        //this.getWorld().addParticle();
        if(this.getStack().isOf(Items.ENDER_EYE)){
            Random r = this.getWorld().getRandom();
            this.getWorld().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY(), this.getZ(),
                    (0.1 + (double) r.nextBetween(1, 3) /10) * r.nextBetween(-1, 1),
                    (0.1 + (double) r.nextBetween(1, 3) /10) * r.nextBetween(-1, 1),
                    (0.1 + (double) r.nextBetween(1, 3) /10) * r.nextBetween(-1, 1));
        }
    }
}
