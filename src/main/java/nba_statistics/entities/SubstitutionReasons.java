/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Adam
 */
@Entity
@Table(name="substitution_reasons")
public class SubstitutionReasons {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="reason_id")
    private int id;
    
    @Column(name="name")
    private String name;
    
    @OneToMany(mappedBy="substitutionReason",
            cascade={CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    private List<MatchSubstitutionHistory> matchSubstitutionHistory;

    public SubstitutionReasons() {};

    public SubstitutionReasons(String name) { this.name = name; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addMatchSubstitutionHistory(MatchSubstitutionHistory historiaZmianWMeczu){
        if (matchSubstitutionHistory == null)
        {
            matchSubstitutionHistory = new ArrayList<>();
        }
        matchSubstitutionHistory.add(historiaZmianWMeczu);
        historiaZmianWMeczu.setSubstitutionReason(this);
        
    }
    
    public List<MatchSubstitutionHistory> getMatchSubstitutionHistory(){
        return matchSubstitutionHistory;
    }
    
    public void setMatchSubstitutionHistory(List<MatchSubstitutionHistory> historiaZmian){
        this.matchSubstitutionHistory = historiaZmian;
    }
            
    @Override
    public String toString(){
        return "SubstitutionReasons{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", matchSubstitutionHistory=" + matchSubstitutionHistory +
                '}';
    }
}
