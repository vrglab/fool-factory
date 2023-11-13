package com.vrglab.foolfactory.Core.Handling.ItemGroups;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FoolFactoryItemGroupMarker {
    String value();
}
