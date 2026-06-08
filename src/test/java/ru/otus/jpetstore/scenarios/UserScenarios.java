package ru.otus.jpetstore.scenarios;

import io.gatling.javaapi.core.ScenarioBuilder;
import ru.otus.jpetstore.feeders.TestData;
import ru.otus.jpetstore.steps.AccountSteps;
import ru.otus.jpetstore.steps.CartSteps;
import ru.otus.jpetstore.steps.CatalogSteps;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.scenario;

public final class UserScenarios {

    private UserScenarios() {
    }

    public static ScenarioBuilder openStoreScenario() {
        return scenario("Open Store Scenario")
                .exec(CatalogSteps.openStore());
    }

    public static ScenarioBuilder browseCatalogScenario() {
        return scenario("Browse Catalog Scenario")
                .feed(TestData.catalogFeeder())
                .exec(CatalogSteps.openStore())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openCategory())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openProduct())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openItem());
    }

    public static ScenarioBuilder searchProductScenario() {
        return scenario("Search Product Scenario")
                .feed(TestData.searchFeeder())
                .exec(CatalogSteps.openStore())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.searchProduct());
    }

    public static ScenarioBuilder addToCartScenario() {
        return scenario("Add Item To Cart Scenario")
                .feed(TestData.catalogFeeder())
                .exec(CatalogSteps.openStore())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openCategory())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openProduct())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openItem())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CartSteps.addItemToCart())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
                .exec(CartSteps.viewCart());
    }

    public static ScenarioBuilder authorizedUserScenario() {
        return scenario("Authorized User Scenario")
                .feed(TestData.catalogFeeder())
                .exec(CatalogSteps.openStore())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
                .exec(AccountSteps.login())
                .exec(CatalogSteps.enterStore())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openCategory())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openProduct())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CatalogSteps.openItem())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(CartSteps.addItemToCart())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
                .exec(CartSteps.viewCart())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
                .exec(AccountSteps.logout());
    }
}