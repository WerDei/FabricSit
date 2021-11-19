package com.bb1.fabric.sit.mixins;


import com.bb1.fabric.sit.Loader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ServerWorld.class)
public class WorldModifier
{
    @Inject(method = "addEntity(Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"))
    public void inject(Entity entity, CallbackInfoReturnable<Boolean> cir)
    {
        if (
                entity.isInvisible() &&
                entity.isInvulnerable() &&
                entity.hasCustomName() &&
                entity.getCustomName().asString().equals(Loader.chairName) &&
                entity instanceof ArmorStandEntity)
            Loader.CHAIRS.add(entity);
    }
}
