package java.lang;

//https://stackoverflow.com/questions/78271237/adding-standard-java-classes-that-are-missing-in-gwt
public class Thread {

    private static final Thread ONLY_THREAD = new Thread();

    public static Thread currentThread() {
        return ONLY_THREAD;
    }

    public void interrupt() {
        // Do nothing? Set a flag for other later calls to check?
    }
    // etc...
}
