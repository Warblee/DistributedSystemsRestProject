package de.fhws.fiw.fds.Project3.client.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
public class PartnerClientModel  extends AbstractClientModel {
    private String name;
    private String country;
    private String department;
    private String websiteURL;
    private String contactPerson;
    private int allowedStudents;
    private int acceptedStudents;
    private LocalDate nextSpringSem = LocalDate.of(1970, 1, 1);;
    private LocalDate nextFallSem = LocalDate.of(1971, 1, 1);;
    
    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link module;

    public PartnerClientModel(){}

    public PartnerClientModel(final String name, final String country, final String department, final String websiteURL, 
            final String contactPerson, final int allowedStudents, final int acceptedStudents, 
            final LocalDate nextSpringSem, final LocalDate nextFallSem) {

        this.name = name;
        this.country = country;
        this.department = department;
        this.websiteURL = websiteURL;
        this.contactPerson = contactPerson;
        this.allowedStudents = allowedStudents;
        this.acceptedStudents = acceptedStudents;
        this.nextSpringSem = nextSpringSem;
        this.nextFallSem = nextFallSem;
    }

    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    @JsonIgnore
    public Link getModules() {
        return module;
    }

    @JsonIgnore
    public String getNameLower() {
        return name.toLowerCase();
    }

    public void setModules(Link module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name){
        this.name = name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(final String country){
        this.country = country;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(final String department){
        this.department = department;
    }

    public String getWebsiteURL(){
        return websiteURL;
    }

    public void setWebsiteURL(final String websiteURL){
        this.websiteURL = websiteURL;
    }

    public String getContactPerson(){
        return contactPerson;
    }

    public void setContactPerson(final String contactPerson){
        this.contactPerson = contactPerson;
    }

    public int getAcceptedStudents(){
        return acceptedStudents;
    }

    public void setAcceptedStudents(final int acceptedStudents) throws IllegalArgumentException{
        if(acceptedStudents >= 0){
            this.acceptedStudents = acceptedStudents;
        }
        else{
            throw new IllegalArgumentException("Accepted Students must be greater than or equal to zero");
        }
    }

    public int getAllowedStudents(){
        return allowedStudents;
    }

    public void setAllowedStudents(final int allowedStudents) throws IllegalArgumentException{
        if(allowedStudents >= 0){
            this.allowedStudents = acceptedStudents;
        }
        else{
            throw new IllegalArgumentException("Allowed Students must be greater than or equal to zero");
        }
    }

    public LocalDate getNextSpringSem(){
        return nextSpringSem;
    }

    public void setNextSpringSem(final LocalDate nextSpringSem){
        this.nextSpringSem = nextSpringSem;
    }

    public LocalDate getNextFallSem(){
        return nextFallSem;
    }

    public void setNextFallSem(final LocalDate nextFallSem){
        this.nextFallSem = nextFallSem;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", name='" + name + '\''
                + ", country='" + country + '\''
                + ", department=" + department + '\''
                + ", websiteURL='" + websiteURL + '\''
                + ", contactPerson='" + contactPerson + '\''
                + ", acceptedStudents='" + acceptedStudents + '\''
                + ", allowedStudents='" + allowedStudents + '\''
                + ", nextSpringSem='" + nextSpringSem + '\''
                + ", nextFallSem='" + nextFallSem + '\''
                + '}';
    }
}
