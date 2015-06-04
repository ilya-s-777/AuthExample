package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Post extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String title;

    @Constraints.Required
    public String text;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dueDate = new Date();

    public static Finder<Long,Post> find = new Finder<Long,Post>(
            Long.class, Post.class
    );

}
