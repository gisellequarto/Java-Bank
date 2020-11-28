package org.academiadecodigo.javabank.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionManager {

    private static EntityManagerFactory emf = null;

    private static EntityManagerFactory getEmf() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("test");
        }
        return emf;
    }

    public static void closeEmf() {
        if(emf != null) {
            emf.close();
            emf = null;
        }
    }

    public static EntityManager getEm() {
        return getEmf().createEntityManager();
    }


}
