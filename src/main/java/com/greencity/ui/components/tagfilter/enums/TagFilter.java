package com.greencity.ui.components.tagfilter.enums;

public enum TagFilter {

    NEWS("News"),
    EVENTS("Events"),
    EDUCATION("Education"),
    INITIATIVES("Initiatives"),
    ADS("Ads");

    private final String tagName;

    TagFilter(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
