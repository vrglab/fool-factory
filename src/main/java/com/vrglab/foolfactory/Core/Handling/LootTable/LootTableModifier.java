package com.vrglab.foolfactory.Core.Handling.LootTable;

import net.minecraft.loot.LootPool;
import net.minecraft.util.Identifier;

public abstract class LootTableModifier {
    public abstract Identifier ModifiedEntry();
    public abstract LootPool.Builder Modifications();
}
