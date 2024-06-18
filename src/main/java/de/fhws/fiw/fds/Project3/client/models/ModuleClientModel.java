package de.fhws.fiw.fds.Project3.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import java.time.LocalDate;

public class ModuleClientModel extends AbstractClientModel{
    private String name;
    private int semester;//1 for spring, 2 for autumn
    private int credits;// only an int, must be bigger than 1


    public ModuleClientModel() {
        // make JPA happy
    }

    public ModuleClientModel(final String name, final int semester, final int credits) {
        this.name = name;
        this.semester = semester;
        this.credits = credits;
    }

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLink;

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(final Link selfLink) {
        this.selfLink = selfLink;
    }

    public String getName(){
        return name;
    }

    public void setName(final String name){
        this.name = name;
    }

    public int getSemester(){
        return semester;
    }

    public void setSemester(final int semester){
        this.semester = semester;
    }

    public int getCredits(){
        return credits;
    }

    public void setCredits(final int credits){
        this.credits = credits;
    }

}
