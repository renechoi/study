package org.example.javaconcurrencyproblem.testhelpers.executor;

import lombok.Getter;

/**
 * @author : Rene Choi
 * @since : 2024/02/24
 */
public class DynamicPortHolder {
    @Getter
    private static int port;

    public static void setPort(int port) {
        DynamicPortHolder.port = port;
    }
}
