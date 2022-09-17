package Test;

public class TestObjectCreation extends RestrictObjectCreation {

	TestObjectCreation() throws ObjectLimitExceededException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws ObjectLimitExceededException {
		num = 4;
		for (int i = 0; i <= num; i++) {
			RestrictObjectCreation rs = new TestObjectCreation();
			System.out.println(i + 1 + "." + rs.hashCode());
		}
	}

}
