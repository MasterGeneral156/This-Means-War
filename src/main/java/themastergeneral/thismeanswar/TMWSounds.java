package themastergeneral.thismeanswar;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class TMWSounds {

	
	//Gun fire
	public static final SoundEvent shot_1911 = makeSoundEvent("shot_1911");
	public static final SoundEvent shot_tmg_carbine = makeSoundEvent("shot_tmg_carbine");
	public static final SoundEvent shot_thunderclaw = makeSoundEvent("shot_thunderclaw");
	public static final SoundEvent shot_mp40 = makeSoundEvent("shot_mp40");
	public static final SoundEvent shot_k98 = makeSoundEvent("shot_k98");
	
	public static final SoundEvent use_foundary = makeSoundEvent("use_foundary");
	
	public static final SoundEvent loop_alarm = makeSoundEvent("alarm_loop");
	
	private static SoundEvent makeSoundEvent(String name) {
		SoundEvent event = SoundEvent.createVariableRangeEvent(new ResourceLocation(TMWMain.MODID, name));
		TMWMain.debugLogger("Sound event: " + name + " has been registered.");
		return event;
	}
	
}
