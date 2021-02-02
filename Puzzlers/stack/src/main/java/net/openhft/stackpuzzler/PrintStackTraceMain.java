package net.openhft.stackpuzzler;

import net.openhft.chronicle.core.StackTrace;
import org.jetbrains.annotations.NotNull;

public class PrintStackTraceMain {
    public static void main(String[] args) throws StackTrace {
        StackTrace st = createStackTrace();
        st.printStackTrace();
        try {
            throw st;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static StackTrace createStackTrace() {
        return new StackTrace("Here");
    }
}
