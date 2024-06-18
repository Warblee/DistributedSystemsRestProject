package de.fhws.fiw.fds.Project3.client.rest;

import de.fhws.fiw.fds.Project3.client.models.PartnerClientModel;
import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;
import de.fhws.fiw.fds.Project3.client.models.ModuleClientModel;
import de.fhws.fiw.fds.Project3.client.web.PartnerWebClient;
import de.fhws.fiw.fds.Project3.client.web.ModuleWebClient;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PartnerRestClient extends AbstractRestClient {
    private static final String BASE_URL = "http://localhost:8080/temp/api";

    //Relation types of partners
    private static final String CREATE_PARTNER = "createPartner";
    private static final String GET_ALL_PARTNERS = "getAllPartners";
    private static final String UPDATE_SINGLE_PARTNER = "updatePartner";
    private static final String DELETE_SINGLE_PARTNER = "deletePartner";
    private static final String GET_NEXT_PAGE = "next";
    private static final String GET_PREV_PAGE = "prev";
    private static final String FILTER_BY_NAME = "filterByPartnerName";
    private static final String ORDERING = "ascOrDscByPartnerName";
    private static final String FILTERANDORDER = "filterAndOrderByPartnerName";
    private static final String GET_SINGLE_PARTNER = "getPartner";

    //relation types of Modules
    private static final String CREATE_MODULE = "createModule";
	private static final String GET_ALL_MODULES = "getAllModules";
	private static final String UPDATE_SINGLE_MODULE = "updateModule";
	private static final String DELETE_MODULE = "deleteModule";
    private static final String GET_SINGLE_MODULE = "getModule";

    private List<PartnerClientModel> currentPartnerData;
    private int cursorPartnerData = 0;

    private List<ModuleClientModel> currentModuleData;
    private int cursorModuleData = 0;

    private final PartnerWebClient client;
    private final ModuleWebClient modClient;

    public PartnerRestClient() {
        super();
        this.client = new PartnerWebClient();
        this.modClient = new ModuleWebClient();
        this.currentPartnerData = Collections.EMPTY_LIST;
    }

    public void resetDatabase() throws IOException {
        processResponse(this.client.resetDatabaseOnServer(BASE_URL), (response) -> {
        });
    }

    public void initializeDatabase() throws IOException {
        processResponse(this.client.initializeDatabaseOnServer(BASE_URL), (response) -> {
        });
    }

    public void start() throws IOException {
        processResponse(this.client.getDispatcher(BASE_URL), (response) -> {
        });
    }

    public boolean isCreatePartnerAllowed() {
        return isLinkAvailable(CREATE_PARTNER);
    }

    public void createPartner(PartnerClientModel partner) throws IOException {
        if (isCreatePartnerAllowed()){
            processResponse(this.client.postNewPartner(getUrl(CREATE_PARTNER), partner), (response) -> {
                this.currentPartnerData = Collections.EMPTY_LIST;
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllPartnersAllowed() {
        return isLinkAvailable(GET_ALL_PARTNERS);
    }

    public void getAllPartners() throws IOException {
        if (isGetAllPartnersAllowed()){
            processResponse(this.client.getCollectionOfPartners(getUrl(GET_ALL_PARTNERS)), (response) -> {
                this.currentPartnerData = new LinkedList<>(response.getResponseData());
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSinglePartnerAllowed() {
        return !this.currentPartnerData.isEmpty() || isLocationHeaderAvailable() || isLinkAvailable(GET_SINGLE_PARTNER);
    }

    public List<PartnerClientModel> partnerData(){
        if(this.currentPartnerData.isEmpty()){
            throw new IllegalStateException();
        }

        return this.currentPartnerData;
    }

    public void setPartnerCursor(int index) {
        if (0 <= index && index < this.currentPartnerData.size()) {
            this.cursorPartnerData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void getSinglePartner() throws IOException {
        if ( isLocationHeaderAvailable()) {
            getSinglePartner(getLocationHeaderURL());
        }
        else if(isLinkAvailable(GET_SINGLE_PARTNER)){
            getSinglePartner(getUrl(GET_SINGLE_PARTNER));
        }
        else if (!this.currentPartnerData.isEmpty()) {
            getSinglePartner(this.cursorPartnerData);
        }
        else {
            throw new IllegalStateException();
        }
    }

    //getting single partner through index (from get all partners)
    public void getSinglePartner(int index) throws IOException {
        getSinglePartner(this.currentPartnerData.get(index).getSelfLink().getUrl());
    }

    //getting single partner through location header
    private void getSinglePartner(String url) throws IOException {
        processResponse(this.client.getSinglePartner(url), (response) -> {
            this.currentPartnerData = new LinkedList<>(response.getResponseData());
            this.cursorPartnerData = 0;
        });
    }

    public boolean isDeletePartnerAllowed() {
        return isLinkAvailable(DELETE_SINGLE_PARTNER);
    }

    public void deleteSinglePartner() throws IOException {
        if(isDeletePartnerAllowed()){
            processResponse(this.client.deletePartner(getUrl(DELETE_SINGLE_PARTNER)), (response) -> {
                this.currentPartnerData = Collections.EMPTY_LIST;
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isUpdatePartnerAllowed() {
        return isLinkAvailable(UPDATE_SINGLE_PARTNER);
    }

    public void updateSinglePartner(PartnerClientModel partner) throws IOException {
        if(isUpdatePartnerAllowed()){
            processResponse(this.client.putPartner(getUrl(UPDATE_SINGLE_PARTNER), partner), (response) -> {
                this.currentPartnerData = Collections.EMPTY_LIST;
                this.cursorPartnerData = 0;
            });
        } else{
            throw new IllegalStateException();
        }
    }

    public boolean isGetByPartnerNameAllowed(){
        return isLinkAvailable(FILTER_BY_NAME);
    }

    public void getByPartnerName(String partnerName) throws IOException{
        if(isGetByPartnerNameAllowed()){
            var url = getUrl(FILTER_BY_NAME);
            url = url.replace("{PARTNERNAME}", partnerName);
            processResponse(this.client.getCollectionOfPartners(url), (response) -> {
                this.currentPartnerData = new LinkedList<>(response.getResponseData());
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetByOrderingAvailable(){
        return isLinkAvailable(ORDERING);
    }

    public void getByOrdering(String order) throws IOException{
        if(isGetByOrderingAvailable()){
            var url = getUrl(ORDERING);
            url = url.replace("{ORDER}", order);
            processResponse(this.client.getCollectionOfPartners(url), (response) -> {
                this.currentPartnerData = new LinkedList<>(response.getResponseData());
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetByOrderandNameAvailable(){
        return isLinkAvailable(FILTERANDORDER);
    }

    public void getByPartnerNameAndOrdering(String partnerName, String order) throws IOException {
        if(isGetByOrderandNameAvailable()){
            var url = getUrl(FILTERANDORDER);
            url = url.replace("{ORDER}", order);
            url = url.replace("{PARTNERNAME}", partnerName);
            processResponse(this.client.getCollectionOfPartners(url), (response) -> {
                this.currentPartnerData = new LinkedList<>(response.getResponseData());
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetNextAvailable() {
        return isLinkAvailable(GET_NEXT_PAGE);
    }

    public boolean isGetPrevAvailable() {
        return isLinkAvailable(GET_PREV_PAGE);
    }

    public void getNextPage() throws IOException {
        if(isGetNextAvailable()){
            processResponse(this.client.getCollectionOfPartners(getUrl(GET_NEXT_PAGE)), (response) -> {
                this.currentPartnerData = new LinkedList<>(response.getResponseData());
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public void getPrevPage() throws IOException {
        if(isGetPrevAvailable()){
            processResponse(this.client.getCollectionOfPartners(getUrl(GET_PREV_PAGE)), (response) -> {
                this.currentPartnerData = new LinkedList<>(response.getResponseData());
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    //Module methods
    //using modClient as it has module methods within
    public boolean isCreateModuleAllowed() {
        return isLinkAvailable(CREATE_MODULE);
    }

    public void createModule(ModuleClientModel module) throws IOException {
        if (isCreateModuleAllowed()){
            processResponse(this.modClient.postNewModule(getUrl(CREATE_MODULE), module), (response) -> {
                this.currentModuleData = Collections.EMPTY_LIST;
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllModulesAllowed() {
        return isLinkAvailable(GET_ALL_MODULES);
    }

    public void getAllModules() throws IOException {
        if(isGetAllModulesAllowed()){
            processResponse(this.modClient.getCollectionOfModules(getUrl(GET_ALL_MODULES)), (response) -> {
                this.currentModuleData = new LinkedList<>(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSingleModuleAllowed(){
        return !this.currentModuleData.isEmpty() || isLocationHeaderAvailable() || isLinkAvailable(GET_SINGLE_MODULE);
    }

    public List<ModuleClientModel> moduleData(){
        if(this.currentModuleData.isEmpty()){
            throw new IllegalStateException();
        }

        return this.currentModuleData;
    }

    public void setModuleCursor(int index) {
        if (0 <= index && index < this.currentModuleData.size()) {
            this.cursorModuleData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void getSingleModule() throws IOException {
        if ( isLocationHeaderAvailable()) {
            getSingleModule(getLocationHeaderURL());
        }
        else if (isGetSingleModuleAllowed()){
            getSingleModule(getUrl(GET_SINGLE_MODULE));
        }
        else if (!this.currentModuleData.isEmpty()) {
            getSingleModule(this.cursorModuleData);
        }
        else {
            throw new IllegalStateException();
        }
    }

    //getting single Module through index (from get all Module)
    public void getSingleModule(int index) throws IOException {
        getSingleModule(this.currentModuleData.get(index).getSelfLink().getUrl());
    }

    //getting single partner through location header
    private void getSingleModule(String url) throws IOException {
        processResponse(this.modClient.getSingleModule(url), (response) -> {
            this.currentModuleData = new LinkedList<>(response.getResponseData());
            this.cursorModuleData = 0;
        });
    }

    public boolean isUpdateModuleAllowed(){
        return isLinkAvailable(UPDATE_SINGLE_MODULE);
    }

    public void updateSingleModule(ModuleClientModel module) throws IOException {
        if(isUpdateModuleAllowed()){
            processResponse(this.modClient.putModule(getUrl(UPDATE_SINGLE_MODULE), module), (result) ->{
                this.currentModuleData = Collections.EMPTY_LIST;
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isDeleteModuleALlowed(){
        return isLinkAvailable(DELETE_MODULE);
    }

    public void deleteSingleModule() throws IOException {
        if(isDeleteModuleALlowed()){
            processResponse(this.modClient.deleteModule(getUrl(DELETE_MODULE)), (result) -> {
                this.currentModuleData = Collections.EMPTY_LIST;
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    //for next and previous also check for the single partner link, as this is the only place the rel type should show up I think

    public boolean isGetNextModuleAvailable(){
        return isLinkAvailable(GET_NEXT_PAGE) && isLinkAvailable(GET_SINGLE_PARTNER);
    }

    public boolean isGetPrevModuleAvailable(){
        return isLinkAvailable(GET_PREV_PAGE) && isLinkAvailable(GET_SINGLE_PARTNER);
    }
    
    public void getNextModulePage() throws IOException {
        if(isGetNextAvailable()){
            processResponse(this.modClient.getCollectionOfModules(getUrl(GET_NEXT_PAGE)), (response) -> {
                this.currentModuleData = new LinkedList<>(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public void getPrevModulePage() throws IOException {
        if(isGetPrevAvailable()){
            processResponse(this.modClient.getCollectionOfModules(getUrl(GET_PREV_PAGE)), (response) -> {
                this.currentModuleData = new LinkedList<>(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
}
