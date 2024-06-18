package de.fhws.fiw.fds.sutton.server;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.Project3.client.models.PartnerClientModel;
import de.fhws.fiw.fds.Project3.client.models.ModuleClientModel;
import de.fhws.fiw.fds.Project3.client.rest.PartnerRestClient;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TestProjectAppIT {
    private PartnerRestClient client;
    final private Faker faker = new Faker();
    //Check that each correctly allowed thing are allowed
    //actually check that the different states work
    //check that correct codes are returned :)


    @BeforeEach
    public void setUp() throws IOException{
       this.client = new PartnerRestClient();
       this.client.resetDatabase();
    }

    //Dispatcher
    //is available
    //get all, and create partner allowed

    @Test void test_dispatcher_is_available() throws IOException {
        client.start();
        assertEquals(200, client.getLastStatusCode());
    } 

    @Test void test_dispatcher_is_get_all_partners_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllPartnersAllowed());
    }

    @Test void test_dispatcher_is_create_partner_allowed() throws IOException {
        client.start();
        assertTrue(client.isCreatePartnerAllowed());
    }

    //create partner & get partner
    //create 1, and test that get single works
    //create 1
    //create multiple and make sure that multiple can be got
    @Test void test_create_partner() throws IOException
    {
        client.start();

        var partner = new PartnerClientModel("Auburn","USA", "Engineering", "auburn.edu", "John RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7));

        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
    }

    @Test void test_create_and_get_partner() throws IOException
    {
        client.start();

        var partner = new PartnerClientModel("Auburn","USA", "Engineering", "auburn.edu", "John RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7));

        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerAllowed() );

        //getting from location header
        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());

        PartnerClientModel partnerFromServer = client.partnerData().get(0);
        assertEquals( "Auburn", partnerFromServer.getName() );
    }

    @Test void test_create_5_partner_and_get_all() throws IOException
    {
        
        client.start();
        client.initializeDatabase();

        client.start();
        assertTrue( client.isGetAllPartnersAllowed() );

        client.getAllPartners();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(10, client.partnerData().size());

        //getting from index
        client.setPartnerCursor(0);
        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test void test_next_and_prev_partners() throws IOException
    {
        
        client.start();
        client.initializeDatabase();
        
        client.start();
        assertTrue( client.isGetAllPartnersAllowed() );

        client.getAllPartners();
        
        assertTrue(client.isGetNextAvailable());
        assertTrue(!client.isGetPrevAvailable());

        client.getNextPage();
        assertEquals( 200, client.getLastStatusCode());
        assertEquals(5, client.partnerData().size());

        assertTrue(!client.isGetNextAvailable());
        assertTrue(client.isGetPrevAvailable());

        client.getPrevPage();
        assertEquals( 200, client.getLastStatusCode());
        assertEquals(10, client.partnerData().size());
    }

    @Test void test_filter_partners() throws IOException{
        client.start();
        client.initializeDatabase();

        client.start();
        client.getAllPartners();
        
        assertTrue(client.isGetByPartnerNameAllowed());
        
        //testing with a search parameter based on initialized values
        client.getByPartnerName("A");
        assertEquals(200, client.getLastStatusCode());
        assertEquals(4, client.partnerData().size());
    }

    @Test void test_order_partners() throws IOException {
        client.start();
        client.initializeDatabase();

        client.start();
        client.getAllPartners();
        
        assertTrue(client.isGetByOrderingAvailable());

        client.getByOrdering("-name");
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Wah", client.partnerData().get(0).getName());
        
        client.getByOrdering("name");
        assertEquals(200, client.getLastStatusCode());
        assertEquals("A Good Name", client.partnerData().get(0).getName());

        client.getByOrdering("");
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Auburn", client.partnerData().get(0).getName());
    }

    @Test void test_order_filter_partners() throws IOException {
        client.start();
        client.initializeDatabase();

        client.start();
        client.getAllPartners();
        
        assertTrue(client.isGetByOrderandNameAvailable());
        
        //testing with a search parameter based on initialized values
        client.getByPartnerNameAndOrdering("A", "-name");
        assertEquals("Auburn",client.partnerData().get(0).getName());
    }


    @Test void test_update_partner() throws IOException {
        client.start();
        client.initializeDatabase();

        client.start();
        client.getAllPartners();

        client.setPartnerCursor(0);
        client.getSinglePartner();
        assertEquals("Auburn", client.partnerData().get(0).getName());

        assertTrue(client.isUpdatePartnerAllowed());

        var partner = new PartnerClientModel("Not Auburn","Different USA", "Liberal Arts", "alabama.edu", "John FakeGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7));

        client.updateSinglePartner(partner);
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetSinglePartnerAllowed());
        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Not Auburn", client.partnerData().get(0).getName());
    }

    @Test void test_delete_partner() throws IOException {
        client.start();
        var partner = new PartnerClientModel("Auburn","USA", "Engineering", "auburn.edu", "John RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7));
        client.createPartner(partner);

        client.getSinglePartner();

        assertTrue(client.isDeletePartnerAllowed());
        
        client.deleteSinglePartner();
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetAllPartnersAllowed());
        client.getAllPartners();

        assertThrows(IllegalStateException.class, () -> client.partnerData());
    }

    @Test void test_create_and_get_module() throws IOException
    {
        client.start();

        var partner = new PartnerClientModel("Auburn","USA", "Engineering", "auburn.edu", "John RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7));
        assertTrue(client.isCreatePartnerAllowed());
        client.createPartner(partner);
        
        client.getSinglePartner();
        
        assertTrue(client.isCreateModuleAllowed());
        var module = new ModuleClientModel("Cool Class", 1, 3);

        client.createModule(module);
        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Cool Class", client.moduleData().get(0).getName());
    }

    @Test void test_create_multiple_modules_and_get_all() throws IOException
    {
        
        client.start();
        client.initializeDatabase();

        client.start();
        client.getAllPartners();
       
        client.getSinglePartner();

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();

    }


    @Test void test_next_and_prev_modules() throws IOException
    {
        
        client.start();
        client.initializeDatabase();
        
        for( int i=0; i<15; i++ ) {
            client.start();
            client.getAllPartners();
            client.getSinglePartner();

            var person = new ModuleClientModel();
            person.setName(faker.name().firstName());
            person.setCredits(1);
            person.setSemester(2);
            client.createModule(person);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        client.getAllPartners();

        client.getSinglePartner();
        
        client.getAllModules();
        assertTrue(client.isGetNextModuleAvailable());
        assertTrue(!client.isGetPrevModuleAvailable());

        client.getNextModulePage();
        assertEquals( 200, client.getLastStatusCode());
        assertEquals(5, client.moduleData().size());

        assertTrue(!client.isGetNextAvailable());
        assertTrue(client.isGetPrevAvailable());

        client.getPrevModulePage();
        assertEquals( 200, client.getLastStatusCode());
        assertEquals(10, client.moduleData().size());
    }

    @Test void test_update_module() throws IOException {
        client.start();
        client.initializeDatabase();

        client.start();
        client.getAllPartners();

        client.setPartnerCursor(0);
        client.getSinglePartner();
        
        client.createModule(new ModuleClientModel("Crazy Class", 1, 2));

        client.getSingleModule();
        
        assertTrue(client.isUpdateModuleAllowed());

        client.updateSingleModule(new ModuleClientModel("Not So Crazy Class", 2, 4));
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Not So Crazy Class", client.moduleData().get(0).getName());
    }

    @Test void test_delete_module() throws IOException {
        client.start();
        var partner = new PartnerClientModel("Auburn","USA", "Engineering", "auburn.edu", "John RealGuy", 100, 10, LocalDate.of(2024,10,7), LocalDate.of(2025,2,7));
        client.createPartner(partner);

        client.getSinglePartner();

        client.createModule(new ModuleClientModel("class", 2, 3));

        client.getSingleModule();
        assertTrue(client.isDeleteModuleALlowed());
        
        client.deleteSingleModule();
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();

        assertThrows(IllegalStateException.class, () -> client.moduleData());
    }
}
