package de.fhws.fiw.fds.Project3.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;
import de.fhws.fiw.fds.Project3.server.database.PartnerDao;
import java.util.Collections;
import java.util.Collection;
import de.fhws.fiw.fds.Project3.server.api.PartnerComparator;
import java.util.List;
import java.time.LocalDate;
import org.apache.commons.lang.ObjectUtils;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PartnerStorage extends AbstractInMemoryStorage<Partner> implements PartnerDao {
    @Override
    public CollectionModelResult<Partner> readByName(String partnerName, SearchParameter searchParameter) {

        return InMemoryPaging.page(this.readAllByPredicateExtra(byName(partnerName), searchParameter), searchParameter.getOffset(), searchParameter.getSize());
    }
    


    //bunch of stuff copied from Sutton because ordering is hell
    protected CollectionModelResult<Partner> readAllByPredicateExtra(final Predicate<Partner> predicate,final SearchParameter searchParameter) {
        final CollectionModelResult<Partner> filteredResult =
        new CollectionModelResult<>(filterByExtra(predicate));

        //sorting
        Collection<Partner> listy = filteredResult.getResult();
        Collections.sort((List<Partner>)listy,PartnerComparator.by(searchParameter.getOrderByAttribute()));
        
        final CollectionModelResult<Partner> page = InMemoryPaging.page(filteredResult,
        searchParameter.getOffset(), searchParameter.getSize());
        final CollectionModelResult<Partner> returnValue =
        new CollectionModelResult<>(cloneExtra(page.getResult()));
        returnValue.setTotalNumberOfResult(filteredResult.getTotalNumberOfResult());

        return returnValue;
    }

    private Collection<Partner> filterByExtra(final Predicate<Partner> predicate) {
		return this.storage.values().stream().filter(predicate).collect(Collectors.toList());
	}

    private Collection<Partner> cloneExtra(final Collection<Partner> result) {
		return result.stream().map(e -> cloneExtra(e)).collect(Collectors.toList());
	}

    protected Partner cloneExtra(final Partner result) {
		final Partner clone = (Partner) ObjectUtils.cloneIfPossible(result);
		return clone;
	}



    public void resetDatabase() {
        this.storage.clear();
    }

    //initializes the database w 15 or so values
    public void initializeDatabase() {
        create(new Partner("Auburn","USA", "Engineering", "auburn.edu", "John RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("THWS","Germany", "other", "side.edu", "John FakeGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Spanish Uni","Spain", "didk", "random.edu", "Jim Cool", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Oesterreich Hochschule","Austria", "fake", "auburn.edu", "Alphonse", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("University","Mexico", "cool", "Other.edu", "Johannes", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("A Good Name","China", "Art", "coolwebsite.edu", "Johnathan", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("I'm Out of Ideas","Ukraine", "Literature", "notMalware.edu", "Jimbo RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Super Real Uni","FakeCountry", "Language", "Website.edu", "James RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Apple School","Canada", "Beer", "yup.edu", "Jeff RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Alabama","USA", "Alchohol", "okay.edu", "Jackson RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Great University","Nigeria", "Spanish", "bad.edu", "Greg RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Hmmm","Lebanon", "Water", "cool.edu", "Paul RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("I ran out of names a while ago","France", "Earth", "woah.edu", "Peter RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("ughh","Other", "Air", "crazy.edu", "Rohit RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));
        create(new Partner("Wah","USA", "Fire", "super.edu", "Super RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7)));

    }

    private Predicate<Partner> byName(String partnerName) {
        return p -> (partnerName.isEmpty() || p.getName().startsWith(partnerName));
    }

}
