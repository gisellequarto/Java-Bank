package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.domain.Bank;

public interface Operation {

    public void makeOperation(Bank bank, int customerId);

}
