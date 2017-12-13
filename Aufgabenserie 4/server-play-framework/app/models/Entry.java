package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Entry extends Model {
    @Id
    public Integer id;
    public Integer version;

    @Constraints.Required
    public String author;
    @Constraints.Required
    public String message;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date creation = new Date();

    public Entry(Integer version, String author, String message) {
        this.version = version;
        this.author = author;
        this.message = message;
    }
}
