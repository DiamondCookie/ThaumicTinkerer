/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ThaumicTinkerer Mod.
 *
 * ThaumicTinkerer is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * ThaumicTinkerer is a Derivative Work on Thaumcraft 4.
 * Thaumcraft 4 (c) Azanor 2012
 * (http://www.minecraftforum.net/topic/1585216-)
 *
 * File Created @ [Dec 29, 2013, 5:29:41 PM (GMT)]
 */
package vazkii.tinkerer.common.item.kami.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import vazkii.tinkerer.client.core.helper.IconHelper;
import vazkii.tinkerer.client.core.proxy.TTClientProxy;
import vazkii.tinkerer.common.ThaumicTinkerer;
import vazkii.tinkerer.common.core.handler.ConfigHandler;
import vazkii.tinkerer.common.core.handler.ModCreativeTab;
import vazkii.tinkerer.common.item.kami.ItemKamiResource;
import vazkii.tinkerer.common.lib.LibItemNames;
import vazkii.tinkerer.common.lib.LibResearch;
import vazkii.tinkerer.common.registry.ITTinkererItem;
import vazkii.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import vazkii.tinkerer.common.registry.ThaumicTinkererRecipe;
import vazkii.tinkerer.common.research.IRegisterableResearch;
import vazkii.tinkerer.common.research.KamiResearchItem;
import vazkii.tinkerer.common.research.ResearchHelper;

import java.util.ArrayList;

public class ItemIchorPick extends ItemPickaxe implements ITTinkererItem {

	public ItemIchorPick() {
		super(ThaumicTinkerer.proxy.toolMaterialIchor);
		setCreativeTab(ModCreativeTab.INSTANCE);

		setHarvestLevel("pickaxe", 4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = IconHelper.forItem(par1IconRegister, this);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return TTClientProxy.kamiRarity;
	}

	@Override
	public boolean isItemTool(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public ArrayList<Object> getSpecialParameters() {
		return null;
	}

	@Override
	public String getItemName() {
		return LibItemNames.ICHOR_PICK;
	}

	@Override
	public boolean shouldRegister() {
		return ConfigHandler.enableKami;
	}

	@Override
	public boolean shouldDisplayInTab() {
		return true;
	}

	@Override
	public IRegisterableResearch getResearchItem() {
		return (IRegisterableResearch) new KamiResearchItem(LibResearch.KEY_ICHOR_TOOLS, new AspectList().add(Aspect.TOOL, 2).add(Aspect.WEAPON, 1).add(Aspect.METAL, 1).add(Aspect.CRAFT, 1), 13, 12, 5, new ItemStack(this)).setConcealed().setParents(LibResearch.KEY_ICHORIUM).setParentsHidden(LibResearch.KEY_ICHORCLOTH_ROD)
				.setPages(new ResearchPage("0"), ResearchHelper.arcaneRecipePage(LibResearch.KEY_ICHOR_PICK), ResearchHelper.arcaneRecipePage(LibResearch.KEY_ICHOR_SHOVEL), ResearchHelper.arcaneRecipePage(LibResearch.KEY_ICHOR_AXE), ResearchHelper.arcaneRecipePage(LibResearch.KEY_ICHOR_SWORD));

	}

	@Override
	public ThaumicTinkererRecipe getRecipeItem() {
		return new ThaumicTinkererArcaneRecipe(LibResearch.KEY_ICHOR_PICK, LibResearch.KEY_ICHOR_TOOLS, new ItemStack(this), new AspectList().add(Aspect.FIRE, 75),
				"III", " R ", " R ",
				'R', new ItemStack(ConfigItems.itemWandRod, 1, 2),
				'I', new ItemStack(ThaumicTinkerer.registryItems.getFirstItemFromClass(ItemKamiResource.class), 1, 2));
	}
}
