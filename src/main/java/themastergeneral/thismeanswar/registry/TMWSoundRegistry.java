package themastergeneral.thismeanswar.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.TMWSounds;

public class TMWSoundRegistry 
{
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TMWMain.MODID);

	public static final RegistryObject<SoundEvent> shot_1911 = SOUNDS.register("shot_1911", () -> TMWSounds.shot_1911);
	public static final RegistryObject<SoundEvent> shot_tmg_carbine = SOUNDS.register("shot_tmg_carbine", () -> TMWSounds.shot_tmg_carbine);
	public static final RegistryObject<SoundEvent> shot_thunderclaw = SOUNDS.register("shot_thunderclaw", () -> TMWSounds.shot_thunderclaw);
	public static final RegistryObject<SoundEvent> use_foundary = SOUNDS.register("use_foundary", () -> TMWSounds.use_foundary);
}
