package com.vrglab.foolfactory.Core.Handling.Blocks;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Core.Loader;
import com.vrglab.foolfactory.Core.RetrivedData;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BlockLoader extends Loader<FoolFactoryBlockMarker> {

    public <T> List<RetrivedData<T>> GetItems() {
        List<RetrivedData<T>> classes = new ArrayList<>();
        List<Class<?>> annotatedClasses = FindAnnotatedClasses(new String[]{"com.vrglab.foolfactory.World.Blocks"}, FoolFactoryBlockMarker.class);
        for (Class<?> clazz : annotatedClasses) {
            try {
                RetrivedData<T> item = new RetrivedData<>();
                item.name = clazz.getAnnotation(FoolFactoryBlockMarker.class).value();
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

    public void BlockRegister() {
        ModInfo.LOGGER.info("Loading Mod Blocks");

        for (var item: GetItems()) {
            Registry.register(Registry.BLOCK, new Identifier(ModInfo.MOD_ID, item.name), (Block)item.loaded_item);
            if(((FoolFactoryBlock)item.loaded_item).HasItem()) {
                Registry.register(Registry.ITEM, new Identifier(ModInfo.MOD_ID, item.name), new BlockItem((Block) item.loaded_item, new FabricItemSettings().group(((FoolFactoryBlock)item.loaded_item).getCreativeTab())));
            }
            BlockDatabase.getInstance().Store(item.name, (FoolFactoryBlock)item.loaded_item);
        }
    }

    private static BlockLoader instance;

    private BlockLoader() {

    }

    public static BlockLoader getInstance() {
        if (instance == null) {
            instance = new BlockLoader();
        }
        return instance;
    }
}
