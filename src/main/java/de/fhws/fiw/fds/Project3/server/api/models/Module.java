package de.fhws.fiw.fds.Project3.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SecondarySelfLink;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SelfLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@JsonRootName("module")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "module")
public class Module extends AbstractModel {
    private String name;
    private int semester;//1 for spring, 2 for autumn
    private int credits;// only an int, must be bigger than 1

    @SecondarySelfLink(
            primaryPathElement =  "partners",
            secondaryPathElement = "modules"
    )
    private transient Link selfLink;



    public Module() {
        // make JPA happy
    }

    public Module(final String name, final int semester, final int credits) {
        this.name = name;
        this.semester = semester;
        this.credits = credits;
    }

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
