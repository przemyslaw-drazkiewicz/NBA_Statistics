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
    private int id;

    @Column(name="view")
    private String view;

    @Column(name="text_help")
    private String text;

    public Help(){}
    public Help(String text){
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getView(){return view; }

    public void setView(String view){this.view=view; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
