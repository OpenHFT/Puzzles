package net.openhft.caspuzzler;

import net.openhft.chronicle.bytes.MappedBytes;

import java.io.File;
import java.io.FileNotFoundException;

public class PingMain {
    public static void main(String[] args) throws FileNotFoundException {
        pingPong(true);
    }

    static void pingPong(boolean ping) throws FileNotFoundException {
        try (MappedBytes bytes = MappedBytes.mappedBytes(new File("data.tmp"), 64 << 10)) {
            int from = ping ? 0 : 1;
            int to = ping ? 1 : 0;
            System.out.println("waiting");
            waitForStart(bytes, from, to);
            waitForStart(bytes, from, to);

            System.out.println("running");

            long start = System.nanoTime();
            long count = 0;
            while (System.nanoTime() < start + 10e9) {
                for (int i = 0; i < 1000; i++) {
                    if (bytes.compareAndSwapLong(0, from, to))
                        count++;
                }
            }
            long time = System.nanoTime() - start;
            System.out.println("average time per CAS was " + 10 * time / count / 10.0 + " NS");
        }
    }

    private static void waitForStart(MappedBytes bytes, int from, int to) {
        for (; ; )
            if (bytes.compareAndSwapLong(0, from, to))
                break;
    }
}

