package br.com.israel.services;

import br.com.israel.models.ChoiceType;
import br.com.israel.models.ParOuImparResult;

public class ParOuImparService {
    private static final int MIN = 1;
    private static final int MAX = 10;

    public ParOuImparResult play(
            ChoiceType choice,
            Integer playerValue) {

        Integer systemValue = (int) ((Math.random() * MAX) + MIN);
        Integer total = playerValue + systemValue;
        Boolean isEven = total % 2 == 0;

        Boolean won = choice == ChoiceType.PAR ? isEven : !isEven;

        return new ParOuImparResult(
                choice,
                playerValue,
                systemValue,
                total,
                won);
    }

}
