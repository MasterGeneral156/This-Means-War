package themastergeneral.thismeanswar;

public class TMWUtils {
	
	public static float makeFloatReadable(float value)
	{
		return Math.round(value * 100F) / 100F;
	}
	
	public static double makeDoubleReadable(double value)
	{
		return Math.round(value * 100D) / 100D;
	}

}
