package com.vrglab.foolfactory.Helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModInfo {
    public static final String MOD_ID = LoadModId();
    public static final Logger LOGGER = LoggerFactory.getLogger(ModInfo.MOD_ID);
    private static String modid = null;



    private static String LoadModId() {
        if(modid == null) {
            modid = ModMetadata.GetModId();
        }
        return modid;
    }
}
