package com.vrglab.foolfactory.Core.Handling.Fluid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FoolFactoryFluidMarker {
    String value();
}
