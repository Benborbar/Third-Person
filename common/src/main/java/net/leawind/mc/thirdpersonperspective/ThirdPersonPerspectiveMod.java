package net.leawind.mc.thirdpersonperspective;


import com.mojang.logging.LogUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.*;
import net.leawind.mc.thirdpersonperspective.config.Config;
import net.leawind.mc.thirdpersonperspective.core.*;
import net.leawind.mc.thirdpersonperspective.userprofile.UserProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import org.slf4j.Logger;
import org.slf4j.event.Level;

public class ThirdPersonPerspectiveMod {
	public static final String MOD_ID = "leawind_third_person_perspective";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static void init () {
		LOGGER.atLevel(Level.TRACE);
		System.out.println(ExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
		ModKeys.register();
		ModEvents.register();
		LOGGER.debug("Debug message oziaosfdp");
	}

	private static class ModEvents {
		public static void register () {
			ClientLifecycleEvent.CLIENT_STARTED.register(ModEvents::onClientStarted);
			ClientGuiEvent.RENDER_HUD.register(ModEvents::onRenderHud);
			ClientPlayerEvent.CLIENT_PLAYER_RESPAWN.register(ModEvents::onClientPlayerRespawn);
			ClientPlayerEvent.CLIENT_PLAYER_JOIN.register(ModEvents::onClientPlayerJoin);
			ClientTickEvent.CLIENT_POST.register(ModKeys::handleThrowExpey);
			ClientRawInputEvent.MOUSE_SCROLLED.register(ModEvents::onMouseScrolled);
		}

		private static EventResult onMouseScrolled (Minecraft minecraft, double amount) {
			System.out.printf("\rMouse Scroll: %f", amount);
			if (Options.isAdjustingCameraOffset()) {
				double dist = UserProfile.getCameraOffsetProfile().getMode().maxDistance;
				dist = Config.distanceMonoList.offset(dist, (int)-Math.signum(amount));
				UserProfile.getCameraOffsetProfile().getMode().setMaxDistance(dist);
				return EventResult.interruptFalse();
			} else {
				return EventResult.pass();
			}
		}

		public static void onPlayerReset (LocalPlayer player) {
			CameraAgent.reset();
			PlayerAgent.reset();
		}

		/**
		 * 当玩家死亡后重生或加入新的维度时触发
		 */
		public static void onClientPlayerRespawn (LocalPlayer oldPlayer, LocalPlayer newPlayer) {
			onPlayerReset(newPlayer);
			LOGGER.info("on Client player respawn");
		}

		public static void onClientPlayerJoin (LocalPlayer player) {
			onPlayerReset(player);
			LOGGER.info("on Client player join");
		}

		public static void onRenderHud (GuiGraphics graphics, float tickDelta) {
			if (CameraAgent.isAvailable() && CameraAgent.isThirdPerson) {
				CrosshairRenderer.render(graphics);
			}
		}

		public static void onClientStarted (Minecraft minecraft) {
			UserProfile.loadDefault();
			UserProfile.load();
			CameraOffsetProfile profile = UserProfile.getCameraOffsetProfile();
			CameraAgent.updateUserProfile(profile);
			PlayerAgent.updateUserProfile(profile);
		}
	}
}
