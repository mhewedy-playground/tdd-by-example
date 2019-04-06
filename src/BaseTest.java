import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.stream.Stream;

public class BaseTest {
    
    protected void run() {
        Stream.of(this.getClass().getDeclaredMethods())
        .filter(it -> it.isAnnotationPresent(Test.class))
        .forEach(it -> {
            try {
                it.invoke(new MoneyTest());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("FINISH");
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Test {

    }

    protected void assertEquals(Object first, Object second) {
        if (!first.equals(second)) {
            System.out.println("Error in expression, " + first + " is not equal to " + second);
        } else {
            System.out.println("PASS");
        }
    }

    protected void assertTrue(boolean toBeTrue) {
        if (!toBeTrue) {
            System.out.println("Error in expression, it should be true but its value is false");
        } else {
            System.out.println("PASS");
        }
    }

    protected void assertFalse(boolean toBeFalse) {
        if (toBeFalse) {
            System.out.println("Error in expression, it should be false but its value is true");
        } else {
            System.out.println("PASS");
        }
    }
}