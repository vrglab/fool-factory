package com.vrglab.foolfactory.Core.Handling.Blocks;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FoolFactoryBlockMarker {
    String value();
}
