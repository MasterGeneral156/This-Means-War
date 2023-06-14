package themastergeneral.thismeanswar.items;

public interface IAmmoHolder {

	int getCurrentAmmo();
	int getMaxAmmo();
	int removeAmmo(int removed);
	int addAmmo(int added);
	int setAmmo(int setAmmo);
}
