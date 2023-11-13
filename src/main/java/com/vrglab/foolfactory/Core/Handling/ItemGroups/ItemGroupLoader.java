package com.vrglab.foolfactory.Core.Handling.ItemGroups;

import com.vrglab.foolfactory.Core.Database.ItemGroupDatabase;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItemMarker;
import com.vrglab.foolfactory.Core.Handling.Items.ItemLoader;
import com.vrglab.foolfactory.Core.Loader;
import com.vrglab.foolfactory.Core.RetrivedData;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ItemGroupLoader extends Loader<FoolFactoryItemGroupMarker> {

    public <T> List<RetrivedData<T>> GetItems() {
        List<RetrivedData<T>> classes = new ArrayList<>();
        List<Class<?>> annotatedClasses = FindAnnotatedClasses(new String[]{"com.vrglab.foolfactory.World.ItemGroups"}, FoolFactoryItemGroupMarker.class);
        for (Class<?> clazz : annotatedClasses) {
            try {
                RetrivedData<T> item = new RetrivedData<>();
                item.name = clazz.getAnnotation(FoolFactoryItemGroupMarker.class).value();
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
        ModInfo.LOGGER.info("Loading Mod item Groups");

        for (var item: GetItems()) {
            FoolFactoryItemGroup mapped_item = (FoolFactoryItemGroup)item.loaded_item;
            mapped_item.setRegisteredItemGroup(FabricItemGroupBuilder.build(mapped_item.GetId(), mapped_item::GetIcon));
            ItemGroupDatabase.getInstance().Store(mapped_item.Name(), mapped_item);
        }
    }


    private static ItemGroupLoader instance;
    private ItemGroupLoader() {

    }
    public static ItemGroupLoader getInstance() {
        if (instance == null) {
            instance = new ItemGroupLoader();
        }
        return instance;
    }
}
