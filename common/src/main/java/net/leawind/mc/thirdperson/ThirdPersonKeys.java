package net.leawind.mc.thirdperson;


import com.mojang.blaze3d.platform.InputConstants;
import net.leawind.mc.api.base.GameStatus;
import net.leawind.mc.thirdperson.cameraoffset.CameraOffsetScheme;
import net.leawind.mc.thirdperson.config.Config;
import net.leawind.mc.thirdperson.core.rotation.RotateTargetEnum;
import net.leawind.mc.util.modkeymapping.ModKeyMapping;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class ThirdPersonKeys {
	public static final ModKeyMapping ADJUST_POSITION   = ModKeyMapping.of(getId("adjust_position"), InputConstants.KEY_Z, ThirdPersonConstants.KEY_CATEGORY)    //
																	   .onDown(ThirdPersonEvents::onStartAdjustingCameraOffset)    //
																	   .onUp(ThirdPersonEvents::onStopAdjustingCameraOffset);
	public static final ModKeyMapping FORCE_AIMING      = ModKeyMapping.of(getId("force_aiming"), ThirdPersonConstants.KEY_CATEGORY);
	public static final ModKeyMapping TOOGLE_MOD_ENABLE = ModKeyMapping.of(getId("toggle_mod_enable"), ThirdPersonConstants.KEY_CATEGORY).onDown(() -> {
		Config config = ThirdPerson.getConfig();
		if (ThirdPersonStatus.isRenderingInThirdPerson()) {
			if (config.is_mod_enable) {
				ThirdPerson.ENTITY_AGENT.setRotateTarget(RotateTargetEnum.CAMERA_ROTATION);
			} else {
				GameStatus.sprintImpulseThreshold = ThirdPerson.getConfig().sprint_impulse_threshold;
				ThirdPersonStatus.lastPartialTick = Minecraft.getInstance().getFrameTime();
				ThirdPerson.mc.gameRenderer.checkEntityPostEffect(null);
				ThirdPerson.CAMERA_AGENT.reset();
				ThirdPerson.ENTITY_AGENT.reset();
			}
			config.is_mod_enable = !config.is_mod_enable;
		}
	});
	public static final ModKeyMapping OPEN_CONFIG_MENU  = ModKeyMapping.of(getId("open_config_menu"), ThirdPersonConstants.KEY_CATEGORY).onDown(() -> {
		Minecraft mc = Minecraft.getInstance();
		if (mc.screen == null) {
			mc.setScreen(ThirdPerson.CONFIG_MANAGER.getConfigScreen(null));
		}
	});
	public static final ModKeyMapping TOGGLE_SIDE       = ModKeyMapping.of(getId("toggle_side"), InputConstants.KEY_CAPSLOCK, ThirdPersonConstants.KEY_CATEGORY) //
																	   .onDown(() -> {
																		   CameraOffsetScheme scheme      = ThirdPerson.getConfig().getCameraOffsetScheme();
																		   boolean            wasCentered = scheme.isCentered();
																		   if (wasCentered) {
																			   scheme.toNextSide();
																		   }
																		   return wasCentered;
																	   }) //
																	   .onHold(() -> ThirdPerson.getConfig().getCameraOffsetScheme().setCentered(true)) //
																	   .onPress(() -> ThirdPerson.getConfig().getCameraOffsetScheme().toNextSide());
	public static final ModKeyMapping TOGGLE_AIMING     = ModKeyMapping.of(getId("toggle_aiming"), ThirdPersonConstants.KEY_CATEGORY).onDown(() -> {
		if (ThirdPerson.isAvailable() && ThirdPersonStatus.isRenderingInThirdPerson()) {
			ThirdPersonStatus.isToggleToAiming = !ThirdPersonStatus.isToggleToAiming;
		}
	});
	public static final ModKeyMapping TOGGLE_PITCH_LOCK = ModKeyMapping.of(getId("toggle_pitch_lock"), ThirdPersonConstants.KEY_CATEGORY).onDown(() -> {
		Config config = ThirdPerson.getConfig();
		config.lock_camera_pitch_angle = !config.lock_camera_pitch_angle;
	});

	private static @NotNull String getId (@NotNull String name) {
		return "key." + ThirdPersonConstants.MOD_ID + "." + name;
	}

	public static void register () {
		ModKeyMapping.registerAll();
	}
}
