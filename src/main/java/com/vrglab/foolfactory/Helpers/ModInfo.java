package com.vrglab.foolfactory.Helpers;

import com.google.gson.JsonArray;
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
    public final static String MOD_ID = LoadModId();
    public static final Logger LOGGER = LoggerFactory.getLogger(ModInfo.MOD_ID);

    private static String modid = null;
    private static JsonObject json_file;


    private static String LoadModId() {
        if(modid == null) {
            InputStream inputStream = ModInfo.class.getClassLoader().getResourceAsStream("FF.Mod.json");
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            JsonParser parser = new JsonParser();
            if(json_file == null){
                json_file = parser.parse(reader).getAsJsonObject();
            }
            modid = json_file.get("modid").toString().replace("\"","");
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return modid;
    }

    private static List<JsonElement> GetData(String name){
        JsonObject packages_array = json_file.getAsJsonObject("check_packages");
        List<JsonElement> result = new ArrayList<>();
        for (var package_:packages_array.getAsJsonArray(name)) {
            result.add(package_);
        }
        return result;
    }
    public static List<String> GetItemGroupPackages(){
        List<String> result = new ArrayList<>();
        for (var package_:GetData("item_groups")) {
            result.add(package_.getAsString());
        }
        result.add("com.vrglab.foolfactory.World.ItemGroups");
        return result;
    }

    public static List<String> GetItemPackages(){
      List<String> result = new ArrayList<>();
      for (var package_:GetData("items")) {
          result.add(package_.getAsString());
      }
      result.add("com.vrglab.foolfactory.World.Items");
      return result;
    }

    public static List<String> GetBlockPackages(){
        List<String> result = new ArrayList<>();
        for (var package_:GetData("blocks")) {
            result.add(package_.getAsString());
        }
        result.add("com.vrglab.foolfactory.World.Blocks");
        return result;
    }

    public static List<String> GetFluidPackages(){
        List<String> result = new ArrayList<>();
        for (var package_:GetData("fluids")) {
            result.add(package_.getAsString());
        }
        result.add("com.vrglab.foolfactory.World.Fluids");
        return result;
    }

    public static List<String> GetLootTableModifierPackages(){
        List<String> result = new ArrayList<>();
        for (var package_:GetData("loot_table_modifiers")) {
            result.add(package_.getAsString());
        }
        return result;
    }
}
