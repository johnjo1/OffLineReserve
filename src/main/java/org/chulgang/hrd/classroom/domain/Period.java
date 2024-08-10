package org.chulgang.hrd.classroom.domain;

public enum Period {
    NINE("09:00 ~ 10:00"),
    TEN("10:00 ~ 11:00"),
    ELEVEN("11:00 ~ 12:00"),
    TWELVE("12:00 ~ 13:00"),
    THIRTEEN("13:00 ~ 14:00"),
    FOURTEEN("14:00 ~ 15:00"),
    FIFTEEN("15:00 ~ 16:00"),
    SIXTEEN("16:00 ~ 17:00"),
    SEVENTEEN("17:00 ~ 18:00");

    private final String description;

    Period(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
