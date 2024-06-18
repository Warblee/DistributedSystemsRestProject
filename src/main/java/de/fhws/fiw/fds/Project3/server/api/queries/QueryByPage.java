package de.fhws.fiw.fds.Project3.server.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingPage;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.Project3.server.api.models.Module;
import de.fhws.fiw.fds.Project3.server.database.DaoFactory;

public class QueryByPage<R> extends AbstractRelationQuery<R, Module> {

    public QueryByPage(long primaryId, int page) {
        super(primaryId);
        this.pagingBehavior = new PagingBehaviorUsingPage<>(page);
    }


    @Override
    protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getPartnerModuleDao().readAllLinked(this.primaryId, searchParameter);
    }
}
