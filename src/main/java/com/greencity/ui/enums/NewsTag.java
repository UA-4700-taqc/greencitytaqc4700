package com.greencity.ui.enums;

import java.util.Random;

public enum NewsTag {
    NEWS,
    EVENTS,
    EDUCATION,
    INITIATIVES,
    ADVERTISING;

    private static final Random RANDOM = new Random();

    public static NewsTag getRandomTag() {
        NewsTag[] tags = values();
        return tags[RANDOM.nextInt(tags.length)];
    }
}
