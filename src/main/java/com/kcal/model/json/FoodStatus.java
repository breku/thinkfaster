package com.kcal.model.json;

/**
 * User: Breku
 * Date: 2014-10-03
 */
public enum FoodStatus {
    ADD_ELEMENT("AE"),
    ADD_UPDATE("AU"),
    REMOVE_UPDATE("RU"),
    REMOVE_ELEMENT("RE");

    private String message;

    FoodStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
