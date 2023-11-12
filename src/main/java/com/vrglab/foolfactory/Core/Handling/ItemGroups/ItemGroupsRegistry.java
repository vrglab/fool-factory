package com.vrglab.foolfactory.Core.Handling.ItemGroups;

import com.vrglab.foolfactory.Helpers.ModInfo;
import com.vrglab.foolfactory.World.Items.Ruby;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Modifier;

public class ItemGroupsRegistry {
    public static final ItemGroup FOOLFACTORY_GROUP = FabricItemGroupBuilder.build(new Identifier(ModInfo.MOD_ID, "foolfactory"), ()->new ItemStack(Items.BARRIER));
}
