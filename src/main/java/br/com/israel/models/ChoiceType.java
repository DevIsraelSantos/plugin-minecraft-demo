package br.com.israel.models;

public enum ChoiceType {
    PAR("Par"),
    IMPAR("Ímpar");

    private final String displayName;

    ChoiceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
