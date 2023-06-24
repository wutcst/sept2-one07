package Item;

/**
 * 这是一个物品类，用于抽象一个物品
 * @author zhangchen
 *
 */
public class Item implements IItem
{
	// Name of the item
	private final String _name;
	// Weight of the item
	private final int _itemWeight;
	private final String _cn_name;
	
	/**
	 * constructor
	 *
	 * @param name
	 * @param itemWeight
	 * @param cn_name
	 */
	public Item(String name, int itemWeight, String cn_name)
	{
		this._name = name;
		this._itemWeight = itemWeight;
		this._cn_name = cn_name;
	}

	/**
	 * get the name
	 */
	public String getItemName()
	{
		return this._name;
	}
	
	/**
	 * get the weight
	 */
	public int getWeight()
	{
		return this._itemWeight;
	}

	public String get_cn_name() {return this._cn_name; }

	public void print() { }

}
