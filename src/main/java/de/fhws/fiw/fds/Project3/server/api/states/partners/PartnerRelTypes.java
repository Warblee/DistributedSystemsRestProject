package de.fhws.fiw.fds.Project3.server.api.states.partners;


//different states for Partners
public interface PartnerRelTypes {
    String CREATE_PARTNER = "createPartner";
    String GET_ALL_PARTNERS = "getAllPartners";
    String UPDATE_SINGLE_PARTNER = "updatePartner";
    String DELETE_SINGLE_PARTNER = "deletePartner";
    String GET_SINGLE_PARTNER = "getPartner";
    String FILTER_BY_NAME = "filterByPartnerName";
    String ORDERING = "ascOrDscByPartnerName";
    String FILTERANDORDER = "filterAndOrderByPartnerName";
}