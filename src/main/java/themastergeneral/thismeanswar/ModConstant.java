package themastergeneral.thismeanswar;

public class ModConstant {
	
	public static class fireRate
	{
		public static int SINGLE_FIRE = 7;
		public static int AUTO_FIRE = 1;
	}
	
	public static class misc
	{
		public static int BULLET_KILL_TICKS = 200;
		public static int CONTACT_EXPLOSIVE_KILL_TICKS = 200;
		public static int GRENADE_THROW_COOLDOWN = 15;
		public static float SAWN_OFF_SPREAD_ADD= 5.1356F;
		public static float SAWN_OFF_DMG_ADD= 2.682F;
		public static float BASE_SPREAD= 1.0F;
	}
	
	public static class bulletBase
	{
		public static class nine_mm
		{
			public static float dmg = 3.4612F;
			public static float spd = 7.81243F;
			public static float sprd = 0.011F + misc.BASE_SPREAD;
		}
		public static class twelve_gauge
		{
			public static float dmg = 5.167F;
			public static float spd = 52.81243F;
			public static float sprd = 0.07F + misc.BASE_SPREAD;
		}
	}
	
	public static class weaponBalance
	{
		public static class beretta_fs
		{
			public static float DMG = 2.7145F + bulletBase.nine_mm.dmg;
			public static float SPD = -0.1F + bulletBase.nine_mm.spd;
			public static float SPRD = -0.021F + bulletBase.nine_mm.sprd;
		}
		
		public static class glock_26
		{
			public static float DMG = 2.31567F + bulletBase.nine_mm.dmg;
			public static float SPD = 0.15F + bulletBase.nine_mm.spd;
			public static float SPRD = 0.017F + bulletBase.nine_mm.sprd;
		}
		
		public static class remmington_m870
		{
			public static float DMG = 5.1561F + bulletBase.twelve_gauge.dmg;
			public static float SPD = 0.16F + bulletBase.twelve_gauge.spd;
			public static float SPRD = 0.06F + bulletBase.twelve_gauge.sprd;
		}
		
		public static class remmington_m870_sawn_off
		{
			public static float DMG = weaponBalance.remmington_m870.DMG + misc.SAWN_OFF_DMG_ADD;
			public static float SPD = weaponBalance.remmington_m870.SPD;
			public static float SPRD = weaponBalance.remmington_m870.SPRD + misc.SAWN_OFF_DMG_ADD;
		}
		
		public static class double_barrel_shotgun
		{
			public static float DMG = 6.312F + bulletBase.twelve_gauge.dmg;
			public static float SPD = 3.556F + bulletBase.twelve_gauge.spd;
			public static float SPRD = 0.13F + bulletBase.twelve_gauge.sprd;
		}
		
		public static class double_barrel_shotgun_sawn_off
		{
			public static float DMG = weaponBalance.double_barrel_shotgun.DMG + misc.SAWN_OFF_DMG_ADD;
			public static float SPD = weaponBalance.double_barrel_shotgun.SPD;
			public static float SPRD = weaponBalance.double_barrel_shotgun.SPRD + misc.SAWN_OFF_DMG_ADD;
		}
		
		public static class tmg_carbine
		{
			public static float DMG = -1.45F + bulletBase.nine_mm.dmg;
			public static float SPD = 3.678F + bulletBase.nine_mm.spd;
			public static float SPRD = -0.03F + bulletBase.nine_mm.sprd;
		}
		
		public static class dynamite_stick
		{
			public static float EXPLOSION_RADIUS = 0.44F;
		}
		
		public static class nuclear_warhead
		{
			public static float EXPLOSION_RADIUS = 350.0F;
		}
		
		public static class bazooka
		{
			public static float EXPLOSION_RADIUS = 5.75F;
			public static float SPEED = 50.53F;
			public static int RLD_TIME = 35;
		}

		public static class hand_grenade
		{
			public static float EXPLOSION_RADIUS = 1.75F;
		}
	}
	
}
