package ru.otus.jpetstore.simulations;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import ru.otus.jpetstore.config.AppConfig;
import ru.otus.jpetstore.scenarios.UserScenarios;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;

public class StepCapacitySimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl(AppConfig.BASE_URL)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptLanguageHeader("en-US,en;q=0.9")
            .acceptEncodingHeader("gzip, deflate, br")
            .userAgentHeader("Gatling Java DSL Step Capacity Test")
            .disableCaching();

    {
        setUp(
                UserScenarios.browseCatalogScenario().injectOpen(
                        constantUsersPerSec(0.1).during(Duration.ofMinutes(2)),
                        constantUsersPerSec(0.2).during(Duration.ofMinutes(2)),
                        constantUsersPerSec(0.3).during(Duration.ofMinutes(2))
                ),
                UserScenarios.searchProductScenario().injectOpen(
                        constantUsersPerSec(0.1).during(Duration.ofMinutes(2)),
                        constantUsersPerSec(0.1).during(Duration.ofMinutes(2)),
                        constantUsersPerSec(0.2).during(Duration.ofMinutes(2))
                ),
                UserScenarios.authorizedUserScenario().injectOpen(
                        constantUsersPerSec(0.1).during(Duration.ofMinutes(2)),
                        constantUsersPerSec(0.1).during(Duration.ofMinutes(2)),
                        constantUsersPerSec(0.1).during(Duration.ofMinutes(2))
                )
        )
                .protocols(httpProtocol)
                .maxDuration(Duration.ofMinutes(7))
                .assertions(
                        global().failedRequests().percent().lt(5.0),
                        global().responseTime().percentile3().lt(5000)
                );
    }
}