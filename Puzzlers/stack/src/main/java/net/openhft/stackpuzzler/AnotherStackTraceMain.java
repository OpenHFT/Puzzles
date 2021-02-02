package net.openhft.stackpuzzler;

import net.openhft.chronicle.core.Jvm;
import net.openhft.chronicle.core.StackTrace;
import org.jetbrains.annotations.NotNull;

public class AnotherStackTraceMain {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(AnotherStackTraceMain::thinking, "background");
        t.start();
        Jvm.pause(50);
        StackTrace st = StackTrace.forThread(t);
        t.interrupt();
        st.printStackTrace();
    }

    @NotNull
    private static void thinking() {
        Jvm.pause(1000);
    }
}
