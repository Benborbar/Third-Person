package net.leawind.mc.thirdpersonperspective.core;


import com.mojang.blaze3d.Blaze3D;
import com.mojang.logging.LogUtils;
import net.leawind.mc.thirdpersonperspective.config.Config;
import net.leawind.mc.util.smoothvalue.ExpSmoothVec2;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CameraAgent {
	public static final Logger              LOGGER               = LogUtils.getLogger();
	public              BlockGetter         level;
	public              LocalPlayer         player;
	public              PlayerAgent         playerAgent;
	public              Camera              camera;
	public              boolean             isThirdPersonEnabled = false;
	public              CameraOffsetProfile offsetProfile        = CameraOffsetProfile.DEFAULT_MODE_CLOSER;
	public              ExpSmoothVec2       smoothOffset         = new ExpSmoothVec2().setValue(0, 0);
	public              double              lastTickTime         = 0;
	public              boolean             isAiming             = false;

	public CameraAgent () {
		// TODO 读取配置文件，加载两种相机模式
		init();
		LOGGER.info("New CameraAgent created");
	}

	private void init () {//TODO
		Minecraft mc = Minecraft.getInstance();
		camera = mc.gameRenderer.getMainCamera();
		player = (LocalPlayer)mc.getCameraEntity();
		// 将虚拟球心放在实体眼睛处
		//		eyePositionSmooth = player.getEyePosition(lerpK);
		assert player != null;
	}

	private boolean getProfileKey () {
		return Minecraft.getInstance().options.getCameraType().isMirrored();
	}

	/**
	 * 鼠标移动导致的相机旋转
	 *
	 * @param dy 水平角变化量
	 * @param dx 俯仰角变化量
	 */
	public void onCameraTurn (double dy, double dx) {
	}

	/**
	 * 进入第三人称视角时触发
	 *
	 * @param lerpK
	 */
	public void onEnterThirdPerson (float lerpK) {
		init();
		isThirdPersonEnabled     = true;
		isAiming                 = false;
		Options.isToggleToAiming = false;
		lastTickTime             = Blaze3D.getTime();
		LOGGER.info("Enter third person, lerpK={}", lerpK);
	}

	/**
	 * 计算并更新相机的朝向和坐标
	 *
	 * @param level      维度
	 * @param entity     实体
	 * @param profileKey false 和 true 分别表示两个不同的 profile
	 */
	public void onRenderTick (BlockGetter level, Entity entity, boolean profileKey, float lerpK) {
		this.level = level;
		player     = (LocalPlayer)entity;
		//TODO
	}

	private static CameraAgent                      instance;
	//TODO 初始化
	public static  Map<Object, CameraOffsetProfile> profiles = new HashMap<>();

	/**
	 * 判断相机代理实例是否可用
	 */
	public static boolean isAvailable () {
		if (!Config.is_mod_enable) {
			return false;
		}
		Minecraft mc     = Minecraft.getInstance();
		Camera    camera = mc.gameRenderer.getMainCamera();
		if (!camera.isInitialized()) {
			return false;
		}
		LocalPlayer player = mc.player;
		return player != null;
	}

	/**
	 * 获取相机代理实例
	 * <p>
	 * 如果当前相机不可用，则抛出错误
	 */
	public static CameraAgent getInstance () {
		if (instance == null) {
			instance = new CameraAgent();
		}
		if (Minecraft.getInstance().gameRenderer.getMainCamera() != instance.camera) {
			throw new Error("The second Camera instance was newed by Minecraft, I didn't expect that!");
		}
		return instance;
	}
}
