package org.academiadecodigo.javabank.controller.operations;

import org.academiadecodigo.javabank.controller.transaction.AbstractBankOperation;
import org.academiadecodigo.javabank.controller.BankApplication;
import org.academiadecodigo.javabank.model.domain.account.AccountType;
import org.academiadecodigo.javabank.view.Messages;
import org.academiadecodigo.javabank.view.UserOptions;

/**
 * A bank operation to create new accounts
 * @see AbstractBankOperation
 * @see UserOptions#OPEN_ACCOUNT
 */
public class NewAccountOperation extends AbstractBankOperation {

    /**
     * Creates a new {@code NewAccountOperation}
     *
     * @see AbstractBankOperation#AbstractBankOperation(BankApplication)
     */
    public NewAccountOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    /**
     * Creates a new bank account
     *
     * @see AbstractBankOperation#execute()
     */
    @Override
    public void execute() {

        int accountId = customer.openAccount(AccountType.CHECKING);

        System.out.println("\n" + Messages.CREATED_ACCOUNT + customer.getName() + " : " + accountId);
    }
}
