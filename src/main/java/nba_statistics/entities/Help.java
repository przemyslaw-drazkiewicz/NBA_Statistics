package nba_statistics.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "help")
public class Help {
    @Id
    @Column(name = "help_id")
    private String id;

    @Column(name="text_help")
    private String text;

    public Help(){}
    public Help(String text){
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
