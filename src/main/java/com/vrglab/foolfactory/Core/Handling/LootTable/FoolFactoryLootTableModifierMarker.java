package com.vrglab.foolfactory.Core.Handling.LootTable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FoolFactoryLootTableModifierMarker {
    String value();
}
