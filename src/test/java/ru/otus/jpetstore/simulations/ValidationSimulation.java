package ru.otus.jpetstore.simulations;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import ru.otus.jpetstore.config.AppConfig;
import ru.otus.jpetstore.scenarios.UserScenarios;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;

public class ValidationSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl(AppConfig.BASE_URL)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptLanguageHeader("en-US,en;q=0.9")
            .acceptEncodingHeader("gzip, deflate, br")
            .userAgentHeader("Gatling Java DSL Validation Test")
            .disableCaching();

    {
        setUp(
                UserScenarios.openStoreScenario()
                        .injectOpen(atOnceUsers(1)),

                UserScenarios.browseCatalogScenario()
                        .injectOpen(atOnceUsers(1)),

                UserScenarios.searchProductScenario()
                        .injectOpen(atOnceUsers(1)),

                UserScenarios.addToCartScenario()
                        .injectOpen(atOnceUsers(1)),

                UserScenarios.authorizedUserScenario()
                        .injectOpen(atOnceUsers(1))
        )
                .protocols(httpProtocol)
                .maxDuration(Duration.ofMinutes(3))
                .assertions(
                        global().failedRequests().percent().lt(AppConfig.MAX_FAILED_REQUESTS_PERCENT),
                        global().responseTime().percentile3().lt(AppConfig.P99_RESPONSE_TIME_MS)
                );
    }
}