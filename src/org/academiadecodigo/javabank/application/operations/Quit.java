package org.academiadecodigo.javabank.application.operations;

public class Quit implements Operation {
    @Override
    public void makeOperation() {
        System.out.println("Bye. Hope to see you soon!");
    }
}
