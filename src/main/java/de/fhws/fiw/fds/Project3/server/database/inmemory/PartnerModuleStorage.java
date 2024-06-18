package de.fhws.fiw.fds.Project3.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.Project3.server.api.models.Module;
import de.fhws.fiw.fds.Project3.server.database.ModuleDao;
import de.fhws.fiw.fds.Project3.server.database.PartnerModuleDao;

public class PartnerModuleStorage extends AbstractInMemoryRelationStorage<Module> implements PartnerModuleDao {
    final private ModuleDao moduleStorage;

    public PartnerModuleStorage(ModuleDao moduleStorage) {
        this.moduleStorage = moduleStorage;
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage() {
        return this.moduleStorage;
    }

    @Override
    public CollectionModelResult<Module> readAllLinked(long primaryId, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> true),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }


    @Override
    public void resetDatabase() {
        this.storage.clear();
    }

    @Override
    public void initializeDatabase() {
        create(1L, new Module("Beer Class", 1, 3));
        create(1L, new Module("Alkohol Class", 2, 2));
        create(1L, new Module("Vodka Class", 2, 3));
        create(1L, new Module("Rum Class", 1, 2));
        create(1L, new Module("Jager Class", 1, 4));
        create(1L, new Module("Schnaps Class", 1, 5));
        create(1L, new Module("English Class", 1, 3));
        create(1L, new Module("German Class", 2, 4));
        create(1L, new Module("Spanish Class", 2, 4));
        create(1L, new Module("Another Class", 2, 4));
        create(1L, new Module("Cool Class", 2, 5));
        create(1L, new Module("Sad Class", 1, 3));
        create(1L, new Module("Happy Class", 1, 5));
        create(1L, new Module("Clean Class", 2, 4));
        create(1L, new Module("Dirty Class", 1, 3));
        create(1L, new Module("Interesting Class", 2, 3));


        create(2L, new Module("Boring Class", 1, 3));
        create(2L, new Module("Beer Class", 2, 4));
        create(2L, new Module("A Class", 1, 3));
        create(2L, new Module("B Class", 2, 6));
        create(2L, new Module("C Class", 2, 4));
        create(2L, new Module("D Class", 1, 5));
        create(2L, new Module("Woohoo Class", 1, 3));
        create(2L, new Module("Yet another Class", 2, 3));
        create(2L, new Module("Good Class", 1, 3));
        create(2L, new Module("Bad Class", 2, 8));
        create(2L, new Module("Neat Class", 1, 1000));
        create(2L, new Module("Super Class", 1, 3));
        create(2L, new Module("Crazy Class", 2, 12));
        create(2L, new Module("Class", 2, 3));
        create(2L, new Module("Yep, its a Class", 1, 42));
    }
}