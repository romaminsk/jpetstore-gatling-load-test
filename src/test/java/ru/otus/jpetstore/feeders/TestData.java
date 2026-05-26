package ru.otus.jpetstore.feeders;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public final class TestData {

    private TestData() {
    }

    public static final String CATEGORY_FISH = "FISH";
    public static final String PRODUCT_FISH = "FI-SW-01";
    public static final String ITEM_FISH = "EST-1";

    public static final String CATEGORY_DOGS = "DOGS";
    public static final String PRODUCT_DOG = "K9-BD-01";
    public static final String ITEM_DOG = "EST-6";

    public static final String SEARCH_KEYWORD = "fish";

    public static Iterator<Map<String, Object>> catalogFeeder() {
        List<Map<String, Object>> data = List.of(
                Map.of(
                        "categoryId", CATEGORY_FISH,
                        "productId", PRODUCT_FISH,
                        "itemId", ITEM_FISH
                ),
                Map.of(
                        "categoryId", CATEGORY_DOGS,
                        "productId", PRODUCT_DOG,
                        "itemId", ITEM_DOG
                )
        );

        return Stream.generate(() -> data.get(
                ThreadLocalRandom.current().nextInt(data.size())
        )).iterator();
    }

    public static Iterator<Map<String, Object>> searchFeeder() {
        List<Map<String, Object>> data = List.of(
                Map.of("keyword", "fish"),
                Map.of("keyword", "dog"),
                Map.of("keyword", "cat")
        );

        return Stream.generate(() -> data.get(
                ThreadLocalRandom.current().nextInt(data.size())
        )).iterator();
    }
}