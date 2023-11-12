package com.vrglab.foolfactory.Core.Handling.Items;

import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupsRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public abstract class FoolFactoryItem extends Item {

    public FoolFactoryItem() {
        super(new FabricItemSettings().group(ItemGroupsRegistry.FOOLFACTORY_GROUP));
    }

    public FoolFactoryItem(Settings settings) {
        super(settings);
    }
}
