package com.vrglab.foolfactory.Core.Handling.Fluid;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Core.Database.FluidDatabase;
import com.vrglab.foolfactory.Core.Database.ItemDatabase;
import com.vrglab.foolfactory.Core.Database.ItemGroupDatabase;
import com.vrglab.foolfactory.Core.Handling.Blocks.BlockLoader;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlock;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlockMarker;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryFluidBlock;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryBucketItem;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItem;
import com.vrglab.foolfactory.Core.Loader;
import com.vrglab.foolfactory.Core.RetrivedData;
import com.vrglab.foolfactory.Helpers.ModInfo;
import com.vrglab.foolfactory.Helpers.ModMetadata;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FluidLoader extends Loader<FoolFactoryFluidMarker> {

    public <T> List<RetrivedData<T>> GetItems() {
        List<RetrivedData<T>> classes = new ArrayList<>();
        List<Class<?>> annotatedClasses = FindAnnotatedClasses(ModMetadata.GetFluidPackages(), FoolFactoryFluidMarker.class);
        for (Class<?> clazz : annotatedClasses) {
            try {
                RetrivedData<T> item = new RetrivedData<>();
                item.name = clazz.getAnnotation(FoolFactoryFluidMarker.class).value();
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
        ModInfo.LOGGER.info("Loading Mod Fluids");

        for (var item: GetItems()) {
            FoolFactoryFluid mapped_item = (FoolFactoryFluid)item.loaded_item;
            FoolFactoryFlowableFluid.FoolFactoryStill still_fluid_instance = new FoolFactoryFlowableFluid.FoolFactoryStill(mapped_item);
            FoolFactoryFlowableFluid.FoolFactoryFlowing flowing_fluid_instance = new FoolFactoryFlowableFluid.FoolFactoryFlowing(mapped_item);

            //Register fluid
            mapped_item.setStillFluid(Registry.register(Registry.FLUID, new Identifier(ModInfo.MOD_ID, "still_"+item.name), still_fluid_instance));
            mapped_item.setFlowingFluid(Registry.register(Registry.FLUID, new Identifier(ModInfo.MOD_ID, "flowing_"+item.name), flowing_fluid_instance));


            //Register it's block
            mapped_item.setFluidBlock(Registry.register(Registry.BLOCK, new Identifier(ModInfo.MOD_ID, item.name+"_block"), new FoolFactoryFluidBlock(still_fluid_instance, mapped_item.GetBlockSettings())));
            BlockDatabase.getInstance().Store(item.name+"_block", (FoolFactoryFluidBlock)mapped_item.getFluidBlock());

            //Register it's bucket item
            mapped_item.setBucket(Registry.register(Registry.ITEM, new Identifier(ModInfo.MOD_ID, item.name+"_bucket"), new FoolFactoryBucketItem(still_fluid_instance, mapped_item.GetItemSettings()
                    .group(ItemGroupDatabase.getInstance().GetEntry(mapped_item.ItemGroup()).getRegisteredItemGroup()).recipeRemainder(Items.BUCKET).maxCount(1))));
            ItemDatabase.getInstance().Store(item.name+"_bucket", (FoolFactoryBucketItem)mapped_item.getBucket());

            FluidDatabaseEntry databaseEntry = new FluidDatabaseEntry();
            databaseEntry.flowing_instance = flowing_fluid_instance;
            databaseEntry.still_instance = still_fluid_instance;
            databaseEntry.fluid = mapped_item;
            FluidDatabase.getInstance().Store(item.name, databaseEntry);
        }
    }

    private static FluidLoader instance;

    private FluidLoader() {

    }

    public static FluidLoader getInstance() {
        if (instance == null) {
            instance = new FluidLoader();
        }
        return instance;
    }
}
