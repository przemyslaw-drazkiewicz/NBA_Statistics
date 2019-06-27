package nba_statistics.entities;

import javax.persistence.*;

@Entity
@Table(name = "help")
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "help_id")
    private int id;

    @Column(name="view")
    private String view;

    @Column(name="text_help")
    private String text;

    public Help(){}
    public Help(String view, String text_help){
        this.text = text_help;
        this.view = view;
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
