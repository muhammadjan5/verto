package com.mfsys.verto.security;

import java.util.List;

public class BypassURI {

    public static final List<String> PUBLIC_URIS = List.of(
            "/api/auth/login",
            "/api/auth/signup",
            "/error"
    );

    public static boolean isPublic(String path) {
        return PUBLIC_URIS.stream()
                .anyMatch(path::equals);
    }
}
