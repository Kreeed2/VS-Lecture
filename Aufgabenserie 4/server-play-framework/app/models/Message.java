package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message extends Model {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;
    public Integer version = 0;

    @Constraints.Required
    public String author;
    @Constraints.Required
    public String message;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date creation = new Date();
    public String creator;

    public static Finder<Integer, Message> finder = new Finder<>(Message.class);

}
