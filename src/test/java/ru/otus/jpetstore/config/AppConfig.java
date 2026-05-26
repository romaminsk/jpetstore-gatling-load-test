package ru.otus.jpetstore.config;

import java.time.Duration;

public final class AppConfig {

    private AppConfig() {
    }

    public static final String BASE_URL = System.getProperty(
            "baseUrl",
            "https://petstore.octoperf.com"
    );

    public static final String USERNAME = System.getProperty(
            "jpetstore.username",
            "loadtest_user_01"
    );

    public static final String PASSWORD = System.getProperty(
            "jpetstore.password",
            "LoadTest123"
    );

    public static final Duration MIN_PAUSE = Duration.ofSeconds(1);
    public static final Duration MAX_PAUSE = Duration.ofSeconds(3);

    public static final double MAX_FAILED_REQUESTS_PERCENT = 1.0;

    public static final int P95_RESPONSE_TIME_MS = 1500;
    public static final int P99_RESPONSE_TIME_MS = 3000;
}