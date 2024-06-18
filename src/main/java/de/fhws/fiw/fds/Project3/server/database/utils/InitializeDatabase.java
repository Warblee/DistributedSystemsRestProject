package de.fhws.fiw.fds.Project3.server.database.utils;

import de.fhws.fiw.fds.Project3.server.database.DaoFactory;

public class InitializeDatabase {

    public static void initializeDB() {
        DaoFactory.getInstance().getPartnerDao().initializeDatabase();
        DaoFactory.getInstance().getPartnerModuleDao().initializeDatabase();
    }
}
