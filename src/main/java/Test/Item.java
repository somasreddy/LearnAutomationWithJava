package Test;

public class Item {
	private String desc;

	public String getDescription() {
		return desc;
	}

	public void setDescription(String desc) {
		this.desc = desc;
	}

	public static void modifyDesc(Item item, String desc) {
		item = new Item();
		item.setDescription(desc);
	}

	public static void main(String[] args) {
		Item it = new Item();
		it.setDescription("Gobstopper");
		Item it2 = new Item();
		it2.setDescription("Fizzylifting");
		modifyDesc(it, "Scrumdiddlyumptious");
		System.out.println(it.getDescription());
		System.out.println(it2.getDescription());

		
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		String s3 = new String(s1);
		System.out.println(s3 == s1);
		System.out.println(s3.equals(s1));
		
	}

}