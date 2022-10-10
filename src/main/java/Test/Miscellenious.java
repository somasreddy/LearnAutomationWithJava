package Test;

class Item1 {
	private String desc;

	public String getDescription() {
		return desc;
	}

	public void setDescription(String desc) {
		this.desc = desc;
	}

	public static void modifyDesc(Item1 item, String desc) {
		item = new Item1();
		item.setDescription(desc);
	}

}
public class Miscellenious extends Item1{
	public static void main(String[] args) {
		Item1 it = new Item1();
		it.setDescription("Gobstopper");
		Item1 it2 = new Item1();
		it2.setDescription("Fizzylifting");
		modifyDesc(it, "Scrumdiddlyumptious");
		System.out.println(it.getDescription());
		System.out.println(it2.getDescription());
	}
}