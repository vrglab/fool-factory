package com.vrglab.foolfactory.Core.Handling.LootTable;

import com.vrglab.foolfactory.Core.Database.ItemDatabase;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItem;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItemMarker;
import com.vrglab.foolfactory.Core.Handling.Items.ItemLoader;
import com.vrglab.foolfactory.Core.Loader;
import com.vrglab.foolfactory.Core.RetrivedData;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class LootTableModifierLoader extends Loader<FoolFactoryLootTableModifierMarker> {

    public <T> List<RetrivedData<T>> GetItems() {
        List<RetrivedData<T>> classes = new ArrayList<>();
        List<Class<?>> annotatedClasses = FindAnnotatedClasses(ModInfo.GetLootTableModifierPackages(), FoolFactoryLootTableModifierMarker.class);
        for (Class<?> clazz : annotatedClasses) {
            try {
                RetrivedData<T> item = new RetrivedData<>();
                item.name = clazz.getAnnotation(FoolFactoryLootTableModifierMarker.class).value();
                item.loaded_item = (T)clazz.getDeclaredConstructors()[0].newInstance();
                classes.add(item);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return classes;
    }

    public void Register() {
        ModInfo.LOGGER.info("Loading Tool Table Modifications");

        for (var item: GetItems()) {
            LootTableModifier mapped_item = (LootTableModifier)item.loaded_item;
            LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
                if(mapped_item.ModifiedEntry().equals(id)){
                    tableBuilder.pool(mapped_item.Modifications().build());
                }
            });
        }
    }

    private static LootTableModifierLoader instance;

    private LootTableModifierLoader() {

    }

    public static LootTableModifierLoader getInstance() {
        if (instance == null) {
            instance = new LootTableModifierLoader();
        }
        return instance;
    }
}
