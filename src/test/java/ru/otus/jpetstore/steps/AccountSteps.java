package ru.otus.jpetstore.steps;

import io.gatling.javaapi.core.ChainBuilder;
import ru.otus.jpetstore.config.AppConfig;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.css;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public final class AccountSteps {

    private AccountSteps() {
    }

    public static ChainBuilder openSignInForm() {
        return exec(
                http("Open sign in form")
                        .get("/actions/Account.action")
                        .queryParam("signonForm", "")
                        .check(status().is(200))
                        .check(css("input[name=username]").exists())
        );
    }

    public static ChainBuilder submitLogin() {
        return exec(
                http("Submit login form")
                        .post("/actions/Account.action")
                        .formParam("username", AppConfig.USERNAME)
                        .formParam("password", AppConfig.PASSWORD)
                        .formParam("signon", "Login")
                        .check(status().in(200, 302))
        );
    }

    public static ChainBuilder login() {
        return exec(openSignInForm())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
                .exec(submitLogin())
                .pause(Duration.ofSeconds(1), Duration.ofSeconds(2));
    }

    public static ChainBuilder logout() {
        return exec(
                http("Logout")
                        .get("/actions/Account.action")
                        .queryParam("signoff", "")
                        .check(status().in(200, 302))
        );
    }
}