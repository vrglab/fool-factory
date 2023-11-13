package com.vrglab.foolfactory.World.ItemGroups;

import com.vrglab.foolfactory.Core.Database.ItemDatabase;
import com.vrglab.foolfactory.Core.Handling.ItemGroups.FoolFactoryItemGroup;
import com.vrglab.foolfactory.Core.Handling.ItemGroups.FoolFactoryItemGroupMarker;
import net.minecraft.item.Item;

@FoolFactoryItemGroupMarker(FoolFactoryGroup.GROUP_ID)
public class FoolFactoryGroup extends FoolFactoryItemGroup {
    public static final String GROUP_ID = "foolfactory";

    @Override
    protected String Name() {
        return FoolFactoryGroup.GROUP_ID;
    }

    @Override
    protected Item Icon() {
        return ItemDatabase.getInstance().GetEntry("ruby");
    }
}
