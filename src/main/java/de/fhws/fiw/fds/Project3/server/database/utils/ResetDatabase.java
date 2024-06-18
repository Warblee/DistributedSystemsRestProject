package de.fhws.fiw.fds.Project3.server.database.utils;

import de.fhws.fiw.fds.Project3.server.database.DaoFactory;

public class ResetDatabase {

    public static void resetAll() {
        DaoFactory.getInstance().getPartnerDao().resetDatabase();
        DaoFactory.getInstance().getPartnerModuleDao().resetDatabase();
    }

}
