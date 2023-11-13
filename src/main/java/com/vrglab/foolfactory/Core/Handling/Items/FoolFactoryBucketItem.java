package com.vrglab.foolfactory.Core.Handling.Items;

import com.vrglab.foolfactory.Core.Database.ItemGroupDatabase;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;

public class FoolFactoryBucketItem extends BucketItem implements FoolFactoryItemType {
    public FoolFactoryBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings.group(ItemGroupDatabase.getInstance().GetEntry("foolfactory").getRegisteredItemGroup()));
    }
}
