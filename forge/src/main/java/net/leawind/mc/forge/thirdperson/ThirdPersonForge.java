package net.leawind.mc.forge.thirdperson;


import dev.architectury.platform.forge.EventBuses;
import net.leawind.mc.thirdperson.ThirdPerson;
import net.leawind.mc.thirdperson.ThirdPersonConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("unused")
@Mod(ThirdPersonConstants.MOD_ID)
public final class ThirdPersonForge {
	public ThirdPersonForge () {
		// 仅在客户端运行 
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			EventBuses.registerModEventBus(ThirdPersonConstants.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
			ThirdPerson.init();
			// 配置屏幕
			if (ThirdPerson.CONFIG_MANAGER.isScreenAvailable()) {
				ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> ThirdPerson.CONFIG_MANAGER.getConfigScreen(screen)));
			}
		});
	}
}
