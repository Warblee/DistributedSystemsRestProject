package de.fhws.fiw.fds.Project3.server.database;

import de.fhws.fiw.fds.Project3.server.database.inmemory.ModuleStorage;
import de.fhws.fiw.fds.Project3.server.database.inmemory.PartnerModuleStorage;
import de.fhws.fiw.fds.Project3.server.database.inmemory.PartnerStorage;

public class DaoFactory {

    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    private final PartnerDao partnerDao;

    private final ModuleDao moduleDao;

    private final PartnerModuleDao partnerModuleDao;

    private DaoFactory() {
        this.partnerDao = new PartnerStorage();
        this.moduleDao = new ModuleStorage();
        this.partnerModuleDao = new PartnerModuleStorage(this.moduleDao);
    }

    public PartnerDao getPartnerDao() {
        return this.partnerDao;
    }

    public ModuleDao getModuleDao() {
        return this.moduleDao;
    }

    public PartnerModuleDao getPartnerModuleDao() {
        return partnerModuleDao;
    }
}
