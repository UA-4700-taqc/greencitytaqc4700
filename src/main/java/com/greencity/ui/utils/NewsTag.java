package com.greencity.ui.utils;

public enum NewsTag {
    NEWS("Новини", "News"),
    EVENTS("Події", "Events"),
    EDUCATION("Освіта", "Education"),
    INITIATIVES("Ініціативи", "Initiatives"),
    ADS("Реклама", "Ads");

    private final String ua;
    private final String en;

    NewsTag(String ua, String en) {
        this.ua = ua;
        this.en = en;
    }

    public static NewsTag getTagFromText(String text) {
        String t = text.trim();

        for (NewsTag tag : values()) {
            if (tag.ua.equalsIgnoreCase(t) || tag.en.equalsIgnoreCase(t)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("Unknown tag: " + text);
    }
}

