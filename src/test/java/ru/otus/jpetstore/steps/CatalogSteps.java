package ru.otus.jpetstore.steps;

import io.gatling.javaapi.core.ChainBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.css;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public final class CatalogSteps {

    private CatalogSteps() {
    }

    public static ChainBuilder openHomePage() {
        return exec(
                http("Open home page")
                        .get("/")
                        .check(status().is(200))
        );
    }

    public static ChainBuilder enterStore() {
        return exec(
                http("Enter store")
                        .get("/actions/Catalog.action")
                        .check(status().is(200))
                        .check(css("body").exists())
        );
    }

    public static ChainBuilder openStore() {
        return exec(openHomePage())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(3))
                .exec(enterStore());
    }

    public static ChainBuilder openCategory() {
        return exec(
                http("Open category #{categoryId}")
                        .get("/actions/Catalog.action")
                        .queryParam("viewCategory", "")
                        .queryParam("categoryId", "#{categoryId}")
                        .check(status().is(200))
        );
    }

    public static ChainBuilder openProduct() {
        return exec(
                http("Open product #{productId}")
                        .get("/actions/Catalog.action")
                        .queryParam("viewProduct", "")
                        .queryParam("productId", "#{productId}")
                        .check(status().is(200))
        );
    }

    public static ChainBuilder openItem() {
        return exec(
                http("Open item #{itemId}")
                        .get("/actions/Catalog.action")
                        .queryParam("viewItem", "")
                        .queryParam("itemId", "#{itemId}")
                        .check(status().is(200))
        );
    }

    public static ChainBuilder searchProduct() {
        return exec(
                http("Search product #{keyword}")
                        .get("/actions/Catalog.action")
                        .queryParam("keyword", "#{keyword}")
                        .queryParam("searchProducts", "")
                        .check(status().is(200))
        );
    }
}