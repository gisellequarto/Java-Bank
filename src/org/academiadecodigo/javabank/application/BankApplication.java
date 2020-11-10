package org.academiadecodigo.javabank.application;

import org.academiadecodigo.javabank.application.operations.*;

import java.util.Map;
import java.util.TreeMap;

public class BankApplication {

    private Map<Integer, Operation> operationMap;
    private BankMenu bankMenu;

    public BankApplication() {
        operationMap = new TreeMap<>();
        initializeMap();
        bankMenu = new BankMenu(this);
    }

    private void initializeMap() {
        operationMap.put(1, new ViewBalance());
        operationMap.put(2, new Deposit());
        operationMap.put(3, new Withdraw());
        operationMap.put(4, new OpenAccount());
        operationMap.put(5, new Quit());
    }

    public void makeOperation(int userOption) {
        operationMap.get(userOption).makeOperation();
    }

}
