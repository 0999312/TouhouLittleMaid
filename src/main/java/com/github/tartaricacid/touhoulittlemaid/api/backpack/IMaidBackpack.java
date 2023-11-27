package com.github.tartaricacid.touhoulittlemaid.api.backpack;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.item.BackpackLevel;
import com.github.tartaricacid.touhoulittlemaid.util.ItemsUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public abstract class IMaidBackpack {
    public abstract ResourceLocation getId();

    public abstract Item getItem();

    public abstract void onPutOn(ItemStack stack, Player player, EntityMaid maid);

    public ItemStack getTakeOffItemStack(ItemStack stack, @Nullable Player player, EntityMaid maid) {
        return this.getItem().getDefaultInstance();
    }

    public abstract void onTakeOff(ItemStack stack, @Nullable Player player, EntityMaid maid);

    public abstract List<Slot> getContainer(ItemStackHandler itemHandler);

    public abstract int getAvailableMaxContainerIndex();

    @OnlyIn(Dist.CLIENT)
    public abstract void drawBackpackScreen(GuiGraphics graphics, EntityMaid maid, int leftPos, int topPos);

    @OnlyIn(Dist.CLIENT)
    public abstract void offsetBackpackItem(PoseStack poseStack);

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public abstract EntityModel<EntityMaid> getBackpackModel(EntityModelSet modelSet);

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public abstract ResourceLocation getBackpackTexture();

    protected final void dropAllItems(EntityMaid maid) {
        ItemsUtil.dropEntityItems(maid, maid.getMaidInv(), BackpackLevel.EMPTY_CAPACITY);
    }
}
