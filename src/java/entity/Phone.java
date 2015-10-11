package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Phone {
    @Id
    private String number;
    private String description;
    private InfoEntity IE;

    public Phone() {
    }

    public Phone(String number, String description, InfoEntity IE) {
        this.number = number;
        this.description = description;
        this.IE = IE;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InfoEntity getIE() {
        return IE;
    }

    public void setIE(InfoEntity IE) {
        this.IE = IE;
    }
    
    
    
}
