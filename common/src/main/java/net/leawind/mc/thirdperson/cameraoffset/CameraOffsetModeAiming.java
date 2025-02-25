package net.leawind.mc.thirdperson.cameraoffset;


import net.leawind.mc.thirdperson.ThirdPerson;
import net.leawind.mc.thirdperson.config.Config;
import net.leawind.mc.util.math.LMath;
import net.leawind.mc.util.math.vector.Vector2d;
import net.leawind.mc.util.math.vector.Vector3d;
import org.jetbrains.annotations.NotNull;

public class CameraOffsetModeAiming extends AbstractCameraOffsetMode {
	public CameraOffsetModeAiming (@NotNull Config config) {
		super(config);
	}

	@Override
	public @NotNull Vector3d getEyeSmoothHalflife () {
		return Vector3d.of(config.aiming_smooth_halflife_horizon, config.aiming_smooth_halflife_vertical, config.aiming_smooth_halflife_horizon);
	}

	@Override
	public double getDistanceSmoothHalflife () {
		return config.aiming_distance_smooth_halflife;
	}

	@Override
	public @NotNull Vector2d getOffsetSmoothHalflife () {
		return Vector2d.of(config.aiming_camera_offset_smooth_halflife);
	}

	@Override
	public double getMaxDistance () {
		return config.aiming_max_distance;
	}

	@Override
	public void setMaxDistance (double distance) {
		config.aiming_max_distance = distance;
	}

	@Override
	public boolean isCentered () {
		return config.aiming_is_centered;
	}

	@Override
	public void setCentered (boolean isCentered) {
		config.aiming_is_centered = isCentered;
	}

	@Override
	public boolean isCameraLeftOfPlayer () {
		return config.aiming_offset_x > 0;
	}

	@Override
	public void toNextSide () {
		ThirdPerson.LOGGER.debug("Switching camera to the other side");
		if (isCentered()) {
			setCentered(false);
		} else {
			config.aiming_offset_x = -config.aiming_offset_x;
		}
	}

	@Override
	public void setSideOffsetRatio (@NotNull Vector2d v) {
		config.aiming_offset_x = LMath.clamp(v.x(), -1, 1);
		config.aiming_offset_y = LMath.clamp(v.y(), -1, 1);
	}

	@Override
	public double getCenterOffsetRatio () {
		return config.aiming_offset_center;
	}

	@Override
	public void setCenterOffsetRatio (double offset) {
		config.aiming_offset_center = LMath.clamp(offset, -1, 1);
	}

	@Override
	public @NotNull Vector2d getSideOffsetRatio (@NotNull Vector2d v) {
		return v.set(config.aiming_offset_x, config.aiming_offset_y);
	}
}
