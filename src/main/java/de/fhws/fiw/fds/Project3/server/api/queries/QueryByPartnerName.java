package de.fhws.fiw.fds.Project3.server.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingPage;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;
import de.fhws.fiw.fds.Project3.server.database.DaoFactory;

public class QueryByPartnerName<R> extends AbstractQuery<R, Partner> {

    private final String partnerName;
    private final String order;

    public QueryByPartnerName(String partnerName, int pageNum, String order) {
        this.partnerName = partnerName;
        this.order = order;
        this.pagingBehavior = new PagingBehaviorUsingPage<>(pageNum);
    }

    public String getPartnerName() {
        return this.partnerName;
    }

    public String getOrder() {
        return this.order;
    }


    public String getOrderAttribute() {
        if(order.length() > 0){
            return this.order.substring(1);
        }
        else{
            return "";
        }
    }

    private String inverseSortingOrderOrDefault(String orderAttribute) {
        if (getOrderAttribute().equals(orderAttribute)) {
            return inverseSortingOrder();
        } else {
            return "%2B" + orderAttribute;
        }
    }

    private String inverseSortingOrder() {
        return this.order.startsWith("+") || this.order.startsWith("%2B") ? "-" + this.order.substring(1) : "%2B" + this.order.substring(1);
    }

    protected CollectionModelResult<Partner> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        searchParameter.setOrderByAttribute(order);
        return DaoFactory.getInstance().getPartnerDao().readByName(
                this.partnerName,
                searchParameter);
    }

}

