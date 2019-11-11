package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;

public class InsertBudget implements MenuOperation {

    private int budget;

    @Override
    public void execute(Prompt prompt) {

        IntegerRangeInputScanner getBudget = new IntegerRangeInputScanner(500, 5000);

        getBudget.setMessage(Messages.INSERT_THE_BUDGET);
        getBudget.setError(Messages.INVALID_BUDGET);

        budget = prompt.getUserInput(getBudget);

    }

    public int getBudget(){
        return budget;
    }
}
