package net.leawind.mc.api.client.events;


import net.leawind.mc.api.base.ModEvent;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public final class ThirdPersonCameraSetupEvent implements ModEvent {
	public final Entity attachedEntity;
	public final float  partialTick;

	public ThirdPersonCameraSetupEvent (Entity attachedEntity, float partialTick) {
		this.attachedEntity = attachedEntity;
		this.partialTick    = partialTick;
	}

	public Vec3  pos;
	public float xRot = 0;
	public float yRot = 0;

	/**
	 * Set camera position
	 */
	public void setPosition (Vec3 pos) {
		this.pos = pos;
	}

	/**
	 * Set camera rotation
	 */
	public void setRotation (float xRot, float yRot) {
		this.xRot = xRot;
		this.yRot = yRot;
	}

	public boolean set () {
		return pos != null;
	}
}
