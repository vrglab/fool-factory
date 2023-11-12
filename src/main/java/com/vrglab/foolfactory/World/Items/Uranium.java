package com.vrglab.foolfactory.World.Items;

import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItem;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItemMarker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@FoolFactoryItemMarker("uranium")
public class Uranium extends FoolFactoryItem {

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        entity.damage(new DamageSource("Radiation"), 1);
    }
}
