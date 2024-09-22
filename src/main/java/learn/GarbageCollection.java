package learn;
//
//public class GarbageCollection {
//
//	@SuppressWarnings("unused")
//	public static void main(String[] args) throws Throwable {
//		String s = null;
//		StringBuilder sb= new StringBuilder();
//		sb= null;
//		Runtime rs = Runtime.getRuntime();
//		System.out.println("Before GC "+rs.freeMemory());
//		rs.gc();
//		System.out.println("After GC "+rs.freeMemory());
//		GarbageCollection gc= new GarbageCollection();
//		gc.finalize();
//				
//	}
//
//	protected void finalize() throws Throwable {
//		super.finalize();
//		System.out.println("Siso");
//	}
//}
public class GarbageCollection {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        String s = null;
        StringBuilder sb = new StringBuilder();
        sb = null;

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Before GC: " + runtime.freeMemory());

        // Request garbage collection
        System.gc(); // This is a suggestion to the JVM; it's not guaranteed to run immediately.

        System.out.println("After GC: " + runtime.freeMemory());

        // No need for finalize() method
        GarbageCollection gc = new GarbageCollection();
        gc.cleanup();
    }

    // Explicit cleanup method
    protected void cleanup() {
        System.out.println("Cleanup method called.");
    }
}
