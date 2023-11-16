package com.vrglab.foolfactory.Helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModMetadata {

    private static JsonObject jsonFile;


    public static void LoadMetadata(Class resLoaderClass){
        InputStream inputStream = resLoaderClass.getClassLoader().getResourceAsStream("FF.Mod.json");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        JsonParser parser = new JsonParser();
        if(jsonFile == null){
            jsonFile = parser.parse(reader).getAsJsonObject();
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String GetModId() {
        return jsonFile.get("modid").toString().replace("\"","");
    }



    private static List<JsonElement> GetData(String name){
        JsonObject packages_array = jsonFile.getAsJsonObject("check_packages");
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
        result.add("com.vrglab.foolfactory.World.LootTableModifiers");
        return result;
    }

}
