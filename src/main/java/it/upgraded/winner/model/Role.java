package it.upgraded.winner.model;

public enum Role {
    GOALKEEPER, DEFENDER, MIDFIELDER, STRICKER, UNDEF;

    public static Role fromString(String value) {
        try {
            return Role.valueOf(value);
        } catch (IllegalArgumentException e) {
            return Role.UNDEF;
        }
    }
}
