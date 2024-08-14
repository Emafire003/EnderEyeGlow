package me.emafire003.dev.endereyeglow.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class EyeItemEntityParticlesMixin extends Entity {

    @Shadow public abstract ItemStack getStack();

    public EyeItemEntityParticlesMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void injectParticlesIfEnderEye(CallbackInfo ci){
        //this.getWorld().addParticle();
        if(this.getStack().isOf(Items.ENDER_EYE)){
            this.getWorld().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY(), this.getZ(),
                    0.1 + (double) this.getRandom().nextBetween(1, 3) /10+this.getRandom().nextBetween(-1, 1),
                    0.1 + (double) this.getRandom().nextBetween(1, 3) /10+this.getRandom().nextBetween(-1, 1),
                    0.1 + (double) this.getRandom().nextBetween(1, 3) /10+this.getRandom().nextBetween(-1, 1));
        }
    }
}
