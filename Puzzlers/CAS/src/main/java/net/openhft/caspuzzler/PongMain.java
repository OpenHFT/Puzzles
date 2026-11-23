/*
 * Copyright 2013-2025 chronicle.software; SPDX-License-Identifier: Apache-2.0
 */
package net.openhft.caspuzzler;

import java.io.FileNotFoundException;

import static net.openhft.caspuzzler.PingMain.pingPong;

public class PongMain {
    public static void main(String[] args) throws FileNotFoundException {
        pingPong(false);
    }
}

