package net.leawind.mc.util.itempattern;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ItemPatternImpl implements ItemPattern {
	/**
	 * 物品描述标识符
	 * <p>
	 * 可通过 {@link Item#getDescriptionId()} 获取
	 */
	private final @Nullable String      descriptionId;
	/**
	 * 用于匹配物品的NBT标签。null 表示匹配任意标签。
	 */
	private final @Nullable CompoundTag tag;
	/**
	 * NBT标签的表达式
	 */
	private final @Nullable String      tagExp;
	private final           int         hashCode;

	/**
	 * @param descriptionId 正规的 descriptionId
	 * @param patternTag    NBT模式标签
	 */
	public ItemPatternImpl (@Nullable String descriptionId, @Nullable CompoundTag patternTag) {
		if (!(descriptionId == null || RGX_REGULAR_ID.matcher(descriptionId).matches())) {
			throw new IllegalArgumentException(String.format("Irregular item description id: %s", descriptionId));
		}
		this.descriptionId = descriptionId;
		this.tag           = patternTag;
		this.tagExp        = patternTag == null ? null: patternTag.getAsString();
		this.hashCode      = Objects.hashCode(descriptionId) ^ Objects.hashCode(tagExp);
	}

	@Override
	public boolean matchId (@Nullable ItemStack itemStack) {
		if (descriptionId == null) {
			return true;
		} else if (itemStack == null) {
			return false;
		} else {
			return descriptionId.equals(itemStack.getItem().getDescriptionId());
		}
	}

	@Override
	public boolean matchNbt (@Nullable ItemStack itemStack) {
		CompoundTag itemTag = itemStack == null ? null: itemStack.getTag();
		return NbtUtils.compareNbt(this.tag, itemTag, true);
	}

	@Override
	public int hashCode () {
		return hashCode;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		}
		ItemPatternImpl ip = (ItemPatternImpl)obj;
		return Objects.equals(descriptionId, ip.descriptionId) && Objects.equals(tagExp, ip.tagExp);
	}

	@Override
	public String toString () {
		return (descriptionId == null ? "": descriptionId) + (tagExp == null ? "": tagExp);
	}
}
