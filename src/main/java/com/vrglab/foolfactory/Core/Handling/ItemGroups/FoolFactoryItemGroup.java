package com.vrglab.foolfactory.Core.Handling.ItemGroups;

import com.vrglab.foolfactory.Helpers.ModInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public abstract class FoolFactoryItemGroup {
    public FoolFactoryItemGroup() {

    }

    protected ItemGroup registeredItemGroup;

    protected abstract String Name();
    protected abstract Item Icon();

    public Identifier GetId() {
        return new Identifier(ModInfo.MOD_ID, Name());
    }

    public ItemStack GetIcon(){
        if(Icon() == null) {
            return new ItemStack(Items.BARRIER);
        } else {
            return new ItemStack(Icon());
        }
    }

    public ItemGroup getRegisteredItemGroup() {
        return registeredItemGroup;
    }

    public void setRegisteredItemGroup(ItemGroup registeredItemGroup) {
        this.registeredItemGroup = registeredItemGroup;
    }
}

