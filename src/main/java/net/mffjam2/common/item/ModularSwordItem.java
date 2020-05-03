package net.mffjam2.common.item;

import net.mffjam2.common.gem.EffectType;
import net.mffjam2.common.gem.FlightType;
import net.mffjam2.common.gem.Gem;
import net.mffjam2.common.gem.GemSlot;
import net.mffjam2.common.gem.GemSocket;
import net.mffjam2.common.gem.ProjectileType;
import net.mffjam2.common.gem.ShootType;
import net.mffjam2.common.gem.SummonType;
import net.mffjam2.common.gem.UserActionType;
import net.mffjam2.common.packet.SpellTriggerPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ModularSwordItem extends SwordItem
{
    public ModularSwordItem(Properties builder)
    {
        super(ItemTier.IRON, 3, -2.4F, builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if (world.isRemote())
            new SpellTriggerPacket(UserActionType.BLOCK).sendToServer();
        return ActionResult.newResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT tag)
    {
        if (this.getClass() == ModularSwordItem.class)
        {
            GemSocket gemSocket = new GemSocket(GemSlot.builder()
                    .projectileType(ProjectileType.BULLET)
                    .userActionType(UserActionType.BLOCK)
                    .build());
            gemSocket.putGem(0, Gem.builder()
                    .effectType(EffectType.FIRE)
                    .flightType(FlightType.NORMAL)
                    .shootType(ShootType.SINGLE)
                    .summonType(SummonType.POINT_TARGET)
                    .power(1)
                    .build());
            return gemSocket;
        }
        else
            return super.initCapabilities(stack, tag);
    }
}
