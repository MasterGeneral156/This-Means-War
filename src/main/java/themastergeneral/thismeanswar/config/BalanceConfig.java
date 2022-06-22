package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BalanceConfig {

	public static ForgeConfigSpec.IntValue FIRE_RATE_AUTO;
	public static ForgeConfigSpec.IntValue FIRE_RATE_SINGLE;
	public static ForgeConfigSpec.IntValue BULLET_KILL_TICKS;
	public static ForgeConfigSpec.IntValue CONTACT_EXPLOSIVE_KILL_TICKS;
	public static ForgeConfigSpec.IntValue THROWABLE_COOLDOWN;
	public static ForgeConfigSpec.DoubleValue SAWN_OFF_XTRA_SPREAD;
	public static ForgeConfigSpec.DoubleValue SAWN_OFF_XTRA_DMG;
	public static ForgeConfigSpec.DoubleValue BASE_SPREAD;
	
	public static ForgeConfigSpec.DoubleValue BASE_9MM_DMG;
	public static ForgeConfigSpec.DoubleValue BASE_9MM_SPD;
	public static ForgeConfigSpec.DoubleValue BASE_9MM_SPRD;
	
	public static ForgeConfigSpec.DoubleValue BASE_12G_DMG;
	public static ForgeConfigSpec.DoubleValue BASE_12G_SPD;
	public static ForgeConfigSpec.DoubleValue BASE_12G_SPRD;
	
	public static ForgeConfigSpec.DoubleValue BASE_556_DMG;
	public static ForgeConfigSpec.DoubleValue BASE_556_SPD;
	public static ForgeConfigSpec.DoubleValue BASE_556_SPRD;
	
	public static ForgeConfigSpec.DoubleValue BASE_223_DMG;
	public static ForgeConfigSpec.DoubleValue BASE_223_SPD;
	public static ForgeConfigSpec.DoubleValue BASE_223_SPRD;
	
	public static ForgeConfigSpec.IntValue TMG_CARBINE_FIRE_RATE;
	public static ForgeConfigSpec.IntValue TMG_CARBINE_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue TMG_CARBINE_DMG;
	public static ForgeConfigSpec.DoubleValue TMG_CARBINE_SPD;
	public static ForgeConfigSpec.DoubleValue TMG_CARBINE_SPRD;
	
	public static ForgeConfigSpec.IntValue SAINT_AR_223_FIRE_RATE;
	public static ForgeConfigSpec.IntValue SAINT_AR_223_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue SAINT_AR_223_DMG;
	public static ForgeConfigSpec.DoubleValue SAINT_AR_223_SPD;
	public static ForgeConfigSpec.DoubleValue SAINT_AR_223_SPRD;
	
	public static ForgeConfigSpec.IntValue SAINT_AR_556_FIRE_RATE;
	public static ForgeConfigSpec.IntValue SAINT_AR_556_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue SAINT_AR_556_DMG;
	public static ForgeConfigSpec.DoubleValue SAINT_AR_556_SPD;
	public static ForgeConfigSpec.DoubleValue SAINT_AR_556_SPRD;
	
	public static ForgeConfigSpec.IntValue BAZOOKA_FIRE_RATE;
	public static ForgeConfigSpec.IntValue BAZOOKA_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue BAZOOKA_DMG;
	public static ForgeConfigSpec.DoubleValue BAZOOKA_SPD;
	
	public static ForgeConfigSpec.IntValue BERETTA_92_FS_FIRE_RATE;
	public static ForgeConfigSpec.IntValue BERETTA_92_FS_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue BERETTA_92_FS_DMG;
	public static ForgeConfigSpec.DoubleValue BERETTA_92_FS_SPD;
	public static ForgeConfigSpec.DoubleValue BERETTA_92_FS_SPRD;
	
	public static ForgeConfigSpec.IntValue GLOCK_26_FIRE_RATE;
	public static ForgeConfigSpec.IntValue GLOCK_26_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue GLOCK_26_DMG;
	public static ForgeConfigSpec.DoubleValue GLOCK_26_SPD;
	public static ForgeConfigSpec.DoubleValue GLOCK_26_SPRD;
	
	public static ForgeConfigSpec.IntValue M870_FIRE_RATE;
	public static ForgeConfigSpec.IntValue M870_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue M870_DMG;
	public static ForgeConfigSpec.DoubleValue M870_SPD;
	public static ForgeConfigSpec.DoubleValue M870_SPRD;
	
	public static ForgeConfigSpec.IntValue SO_M870_FIRE_RATE;
	public static ForgeConfigSpec.IntValue SO_M870_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue SO_M870_DMG;
	public static ForgeConfigSpec.DoubleValue SO_M870_SPD;
	public static ForgeConfigSpec.DoubleValue SO_M870_SPRD;
	
	public static ForgeConfigSpec.IntValue DOUBLE_BARREL_FIRE_RATE;
	public static ForgeConfigSpec.IntValue DOUBLE_BARREL_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue DOUBLE_BARREL_DMG;
	public static ForgeConfigSpec.DoubleValue DOUBLE_BARREL_SPD;
	public static ForgeConfigSpec.DoubleValue DOUBLE_BARREL_SPRD;
	
	public static ForgeConfigSpec.IntValue SO_DOUBLE_BARREL_FIRE_RATE;
	public static ForgeConfigSpec.IntValue SO_DOUBLE_BARREL_RLD_TIME;
	public static ForgeConfigSpec.DoubleValue SO_DOUBLE_BARREL_DMG;
	public static ForgeConfigSpec.DoubleValue SO_DOUBLE_BARREL_SPD;
	public static ForgeConfigSpec.DoubleValue SO_DOUBLE_BARREL_SPRD;
	
	public static ForgeConfigSpec.DoubleValue GRENADE_DMG;
	public static ForgeConfigSpec.DoubleValue DYNA_STICK_DMG;
	public static ForgeConfigSpec.DoubleValue NUKE_DMG;
	
    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the mod. Keep this synced between client and server.").push("Fire Rate Balance");

        FIRE_RATE_AUTO = COMMON_BUILDER
                .comment("Delay (in ticks) before firing next round in a fully automatic weapon")
                .defineInRange("fireRateAuto", 1, 1, Integer.MAX_VALUE);
        FIRE_RATE_SINGLE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing next round in a semi-automatic weapon")
                .defineInRange("fireRateSingle", 7, 7, 7);
        
        THROWABLE_COOLDOWN = COMMON_BUILDER
                .comment("Delay (in ticks) before player can throw another throwable")
                .defineInRange("throwableCooldown", 15, 15, 15);
        
        SAWN_OFF_XTRA_SPREAD = COMMON_BUILDER
                .comment("Default value for extra spread on weapons that are sawn-off")
                .defineInRange("sawnOffXtraSpread", 5.1356, 5.1356, 5.1356);
        SAWN_OFF_XTRA_DMG = COMMON_BUILDER
                .comment("Default value for extra damage on weapons that are sawn-off")
                .defineInRange("sawnOffXtraDamage", 2.682, 2.682, 2.682);

        COMMON_BUILDER.pop();
    }
    
    public static void register9mmConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance reference for the 9mm round. Does not effect in-game at all.").push("9mm Round");

        BASE_9MM_DMG = COMMON_BUILDER
                .comment("Base damage for round.")
                .defineInRange("Round Damage", 3.4612, 3.4612, 3.4612);
        BASE_9MM_SPD = COMMON_BUILDER
                .comment("Base speed for round.")
                .defineInRange("Round Speed", 7.81243, 7.81243, 7.81243);
        BASE_9MM_SPRD = COMMON_BUILDER
                .comment("Base spread for round.")
                .defineInRange("Round Spread", 1.011, 1.011, 1.011);

        COMMON_BUILDER.pop();
    }
    
    public static void register556Config(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance reference for the 5.56 round. Does not effect in-game at all.").push("556 Round");

        BASE_556_DMG = COMMON_BUILDER
                .comment("Base damage for round.")
                .defineInRange("Round Damage", 7.2348, 7.2348, 7.2348);
        BASE_556_SPD = COMMON_BUILDER
                .comment("Base speed for round.")
                .defineInRange("Round Speed", 11.454, 11.454, 11.454);
        BASE_556_SPRD = COMMON_BUILDER
                .comment("Base spread for round.")
                .defineInRange("Round Spread", 1.655, 1.655, 1.655);

        COMMON_BUILDER.pop();
    }
    
    public static void register223Config(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance reference for the .223 round. Does not effect in-game at all.").push("223 Round");

        BASE_223_DMG = COMMON_BUILDER
                .comment("Base damage for round.")
                .defineInRange("Round Damage", 4.015, 4.015, 4.015);
        BASE_223_SPD = COMMON_BUILDER
                .comment("Base speed for round.")
                .defineInRange("Round Speed", 8.241, 8.241, 8.241);
        BASE_223_SPRD = COMMON_BUILDER
                .comment("Base spread for round.")
                .defineInRange("Round Spread", 1.414, 1.414, 1.414);

        COMMON_BUILDER.pop();
    }
    
    public static void register12gConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance reference for the 12G round. Does not effect in-game at all.").push("12G Round");

        BASE_12G_DMG = COMMON_BUILDER
                .comment("Base damage for round.")
                .defineInRange("Round Damage", 5.167, 5.167, 5.167);
        BASE_12G_SPD = COMMON_BUILDER
                .comment("Base speed for round.")
                .defineInRange("Round Speed", 52.81243, 52.81243, 52.81243);
        BASE_12G_SPRD = COMMON_BUILDER
                .comment("Base spread for round.")
                .defineInRange("Round Spread", 1.07, 1.07, 1.07);

        COMMON_BUILDER.pop();
    }
    
    public static void registerTMGCarbineConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the TMG Carbine weapon.").push("TMG Carbine (9mm)");

        TMG_CARBINE_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 1, 1, Integer.MAX_VALUE);
        TMG_CARBINE_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 38, 1, Integer.MAX_VALUE);
        TMG_CARBINE_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 2.0112, 0, Double.MAX_VALUE);
        TMG_CARBINE_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 11.49043, 0, Double.MAX_VALUE);
        TMG_CARBINE_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 0.981, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerSaintAR223Config(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Saint AR15 weapon.").push("Saint AR15 (223)");

        SAINT_AR_223_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
        SAINT_AR_223_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 45, 1, Integer.MAX_VALUE);
        SAINT_AR_223_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 2.565, 0, Double.MAX_VALUE);
        SAINT_AR_223_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 11.919, 0, Double.MAX_VALUE);
        SAINT_AR_223_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 1.384, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerSaintAR556Config(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Saint AR15 weapon.").push("Saint AR15 (556)");

        SAINT_AR_556_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
        SAINT_AR_556_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 45, 1, Integer.MAX_VALUE);
        SAINT_AR_556_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 5.7848, 0, Double.MAX_VALUE);
        SAINT_AR_556_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 15.132, 0, Double.MAX_VALUE);
        SAINT_AR_556_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 1.625, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerBazookaConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Bazooka weapon.").push("Bazooka (Rocket)");

        BAZOOKA_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
        BAZOOKA_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 35, 1, Integer.MAX_VALUE);
        BAZOOKA_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 5.75, 0, Double.MAX_VALUE);
        BAZOOKA_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 50.53, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerBeretta92fsConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Beretta 92 FS weapon.").push("Beretta 92 FS (9mm)");

    	BERETTA_92_FS_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
    	BERETTA_92_FS_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 45, 1, Integer.MAX_VALUE);
    	BERETTA_92_FS_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 6.1757, 0, Double.MAX_VALUE);
    	BERETTA_92_FS_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 7.71243, 0, Double.MAX_VALUE);
    	BERETTA_92_FS_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 0.99, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerGlock26Config(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Glock26 FS weapon.").push("Glock26 (9mm)");

    	GLOCK_26_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
    	GLOCK_26_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 45, 1, Integer.MAX_VALUE);
    	GLOCK_26_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 5.77687, 0, Double.MAX_VALUE);
    	GLOCK_26_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 7.97243, 0, Double.MAX_VALUE);
    	GLOCK_26_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 1.028, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerM870Config(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Remmington M870 weapon.").push("Remmington 870 (12G)");

    	M870_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
    	M870_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 8, 1, Integer.MAX_VALUE);
    	M870_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 10.3231, 0, Double.MAX_VALUE);
    	M870_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 52.65243, 0, Double.MAX_VALUE);
    	M870_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 1.13, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerSOM870Config(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Sawn off Remmington M870 weapon.").push("Sawn off Remmington 870 (12G)");

    	SO_M870_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
    	SO_M870_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 8, 1, Integer.MAX_VALUE);
    	SO_M870_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 13.0051, 0, Double.MAX_VALUE);
    	SO_M870_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 52.65243, 0, Double.MAX_VALUE);
    	SO_M870_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 6.2656, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerDoubleBarrelConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Double Barrel weapon.").push("Double Barrel (12G)");

    	DOUBLE_BARREL_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
    	DOUBLE_BARREL_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 8, 1, Integer.MAX_VALUE);
    	DOUBLE_BARREL_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 11.479, 0, Double.MAX_VALUE);
    	DOUBLE_BARREL_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 56.36843, 0, Double.MAX_VALUE);
    	DOUBLE_BARREL_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 1.2, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerSODoubleBarrelConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Sawn off Double Barrel weapon.").push("Sawn off Double Barrel (12G)");

    	SO_DOUBLE_BARREL_FIRE_RATE = COMMON_BUILDER
                .comment("Delay (in ticks) before firing again")
                .defineInRange("fireRate", 7, 1, Integer.MAX_VALUE);
    	SO_DOUBLE_BARREL_RLD_TIME = COMMON_BUILDER
                .comment("Time (in ticks) to reload")
                .defineInRange("fireRate", 8, 1, Integer.MAX_VALUE);
    	SO_DOUBLE_BARREL_DMG = COMMON_BUILDER
                .comment("Damage received when hit")
                .defineInRange("damage", 14.161, 0, Double.MAX_VALUE);
    	SO_DOUBLE_BARREL_SPD = COMMON_BUILDER
                .comment("Bullet speed")
                .defineInRange("speed", 56.36843, 0, Double.MAX_VALUE);
    	SO_DOUBLE_BARREL_SPRD = COMMON_BUILDER
                .comment("Bullet spread")
                .defineInRange("spread", 6.3356, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerGrenadeConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Hand Grenade throwable.").push("Hand Grenade");
    	GRENADE_DMG = COMMON_BUILDER
                .comment("Explosion damage")
                .defineInRange("damage", 1.75, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerDynamiteStickConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Dynamite Stick throwable.").push("Dynamite Stick");
    	DYNA_STICK_DMG = COMMON_BUILDER
                .comment("Explosion damage")
                .defineInRange("damage", 0.44, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    public static void registerNukeConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
    	COMMON_BUILDER.comment("Balance settings for the Nuclear Warhead throwable.").push("Nuclear Warhead");
    	NUKE_DMG = COMMON_BUILDER
                .comment("Explosion damage")
                .defineInRange("damage", 345.67, 0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
}
