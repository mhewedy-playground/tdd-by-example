import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public abstract class BaseTest {

    private AtomicInteger failureCount = new AtomicInteger();

    protected void run(String[] args) {
        Stream.of(this.getClass().getDeclaredMethods())
                .filter(it -> it.isAnnotationPresent(Test.class))
                .filter(it -> args.length == 0 || it.getName().equalsIgnoreCase(args[0]))
                .forEach(it -> {
                    try {
                        System.out.printf("Running: %s\n", it.getName());
                        it.invoke(this);
                    } catch (Exception e) {
                        reportFailure(e.getMessage());
                        e.printStackTrace();
                    }
                });
        System.out.printf("Finish Tests, Failure: %d\n", failureCount.get());
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Test {

    }

    protected void assertEquals(Object first, Object second) {
        if (!first.equals(second)) {
            reportFailure("Error in expression, " + first + " is not equal to " + second);
        } else {
            reportSuccess();
        }
    }

    protected void assertTrue(boolean toBeTrue) {
        if (!toBeTrue) {
            reportFailure("Error in expression, it should be true but its value is false");
        } else {
            reportSuccess();
        }
    }

    protected void assertFalse(boolean toBeFalse) {
        if (toBeFalse) {
            reportFailure("Error in expression, it should be false but its value is true");
        } else {
            reportSuccess();
        }
    }

    private void reportSuccess() {
    }

    private void reportFailure(String message) {
        if (message != null && message.trim().length() > 0) {
            System.out.print("\t");
            System.out.println(message);
        }
        failureCount.incrementAndGet();
    }
}