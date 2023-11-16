package com.vrglab.foolfactory.Core.Handling.Items;

import com.vrglab.foolfactory.Core.Database.ItemDatabase;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlock;
import com.vrglab.foolfactory.Core.Loader;
import com.vrglab.foolfactory.Core.RetrivedData;
import com.vrglab.foolfactory.Helpers.ModInfo;
import com.vrglab.foolfactory.Helpers.ModMetadata;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ItemLoader extends Loader<FoolFactoryItemMarker>{

    public <T> List<RetrivedData<T>> GetItems() {
        List<RetrivedData<T>> classes = new ArrayList<>();
        List<Class<?>> annotatedClasses = FindAnnotatedClasses(ModMetadata.GetItemPackages(), FoolFactoryItemMarker.class);
        for (Class<?> clazz : annotatedClasses) {
            try {
                RetrivedData<T> item = new RetrivedData<>();
                item.name = clazz.getAnnotation(FoolFactoryItemMarker.class).value();
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

    public void ItemRegister() {
        ModInfo.LOGGER.info("Loading Mod items");

        for (var item: GetItems()) {
           Registry.register(Registry.ITEM, new Identifier(ModInfo.MOD_ID, item.name), (FoolFactoryItem)item.loaded_item);
           ItemDatabase.getInstance().Store(item.name, (FoolFactoryItem)item.loaded_item);
        }
    }

    private static ItemLoader instance;

    private ItemLoader() {

    }

    public static ItemLoader getInstance() {
        if (instance == null) {
            instance = new ItemLoader();
        }
        return instance;
    }
}
