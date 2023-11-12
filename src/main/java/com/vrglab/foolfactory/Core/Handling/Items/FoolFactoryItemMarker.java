package com.vrglab.foolfactory.Core.Handling.Items;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FoolFactoryItemMarker {
    String value();
}
