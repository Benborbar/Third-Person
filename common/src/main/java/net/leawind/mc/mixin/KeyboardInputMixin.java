package net.leawind.mc.mixin;


import net.leawind.mc.api.base.GameEvents;
import net.leawind.mc.api.client.event.CalculateMoveImpulseEvent;
import net.minecraft.client.player.KeyboardInput;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value=KeyboardInput.class, priority=2000)
public class KeyboardInputMixin {
	/**
	 * 注入到tick的末尾，重新计算 leftImpulse 和 forwardImpulse 的值
	 */
	@Inject(method="tick", at=@At(value="TAIL"))
	@PerformanceSensitive
	private void tick_tail (boolean multiplyImpulse, float impulseMultiplier, CallbackInfo ci) {
		KeyboardInput that = ((KeyboardInput)(Object)this);
		if (GameEvents.calculateMoveImpulse != null) {
			CalculateMoveImpulseEvent event = new CalculateMoveImpulseEvent(that, multiplyImpulse ? impulseMultiplier: 1);
			event.forwardImpulse = that.forwardImpulse;
			event.leftImpulse    = that.leftImpulse;
			GameEvents.calculateMoveImpulse.accept(event);
			that.forwardImpulse = event.forwardImpulse;
			that.leftImpulse    = event.leftImpulse;
		}
	}
}
