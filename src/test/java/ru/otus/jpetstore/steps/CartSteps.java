package ru.otus.jpetstore.steps;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public final class CartSteps {

    private CartSteps() {
    }

    public static ChainBuilder addItemToCart() {
        return exec(
                http("Add item #{itemId} to cart")
                        .get("/actions/Cart.action")
                        .queryParam("addItemToCart", "")
                        .queryParam("workingItemId", "#{itemId}")
                        .check(status().is(200))
        );
    }

    public static ChainBuilder viewCart() {
        return exec(
                http("View cart")
                        .get("/actions/Cart.action")
                        .queryParam("viewCart", "")
                        .check(status().is(200))
        );
    }
}