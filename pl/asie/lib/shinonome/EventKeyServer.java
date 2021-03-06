package pl.asie.lib.shinonome;

import java.util.Date;
import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pl.asie.lib.AsieLibMod;
import pl.asie.lib.Packets;
import pl.asie.lib.network.PacketInput;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class EventKeyServer {
	public static boolean isEntityNano(Entity entity) {
		return (entity != null && entity instanceof EntityPlayer && ((EntityPlayer)entity).username.equals("asiekierka"));
	}
	
	@ForgeSubscribe
	public void containerEvent(PlayerInteractEvent event) {
		if(isEntityNano(event.entityPlayer) && event.action == Action.RIGHT_CLICK_BLOCK) {
			Block block = Block.blocksList[event.entity.worldObj.getBlockId(event.x, event.y, event.z)];
			TileEntity te = event.entity.worldObj.getBlockTileEntity(event.x, event.y, event.z);
			if(block instanceof BlockContainer || (Math.random() < 0.1))
				scheduleSpin();
		}
	}
	
	@ForgeSubscribe
	public void damageEvent(LivingHurtEvent event) {
		if(isEntityNano(event.entity) && event.ammount > 0.0F) {
			scheduleSpin();
		}
	}
	
	public void scheduleSpin() {
		try {
			PacketInput packet = AsieLibMod.packet.create(Packets.NANO_NANO);
			AsieLibMod.packet.sendToAllPlayers(packet);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
