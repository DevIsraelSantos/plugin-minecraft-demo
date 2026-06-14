package br.com.israel.models;

public record ParOuImparResult(
                ChoiceType choice,
                Integer playerValue,
                Integer systemValue,
                Integer total,
                Boolean won) {
}
