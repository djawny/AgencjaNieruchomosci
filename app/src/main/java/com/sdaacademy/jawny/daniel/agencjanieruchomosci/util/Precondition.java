package com.sdaacademy.jawny.daniel.agencjanieruchomosci.util;

public final class Precondition {

    private Precondition() {
    }

    public static <T> T checkNotNull(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        return t;
    }
}
