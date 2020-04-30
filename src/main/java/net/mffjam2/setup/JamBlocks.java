package net.mffjam2.setup;

import net.mffjam2.MFFJam2;
import net.mffjam2.common.block.GemCrusherBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MFFJam2.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class JamBlocks
{
    public static final Block GEM_CRUSHER = null;

    @SubscribeEvent
    public static void onBlockRegister(Register<Block> event)
    {
        registerBlock(event, new GemCrusherBlock(Properties.create(Material.IRON)), "gem_crusher");
    }

    private static <T extends Block> void registerBlock(Register<Block> event, T block, String name)
    {
        block.setRegistryName(MFFJam2.MODID, name);
        event.getRegistry().register(block);
        JamItems.registerItemBlock(block);
    }
}
