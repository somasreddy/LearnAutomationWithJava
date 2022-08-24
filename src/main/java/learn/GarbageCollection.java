package learn;

public class GarbageCollection {

	public static void main(String[] args) throws Throwable {
		String s = null;
		StringBuilder sb= new StringBuilder();
		sb= null;
		Runtime rs = Runtime.getRuntime();
		System.out.println("Before GC "+rs.freeMemory());
		rs.gc();
		System.out.println("After GC "+rs.freeMemory());
		GarbageCollection gc= new GarbageCollection();
		gc.finalize();
				
	}
	@SuppressWarnings("deprecation")
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Siso");
	}
}
