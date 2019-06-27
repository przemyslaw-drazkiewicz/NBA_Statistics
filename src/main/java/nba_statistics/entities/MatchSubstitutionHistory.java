/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Przemek
 */


@Entity
@Table(name = "match_substitution_history")
public class MatchSubstitutionHistory {

    //tutaj zamiast Bi-directional jest Uni-directional a wiec brak adnotacji
    //@ManyToOne co oznacza ze np. usuwajac jakis mecz usunie sie rowniez rekord
    //z tej encji czyli MatchSubstitutionHistory(to samo dla Zadownikow itd.)
    //Trzeba by sprawdzic czy w pozostalych klasach tez nie powinnismy zamienic z Bi-directional 
    //na Uni-directional
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_substitution_history_id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name= "match_id")
    private Matches match;

    @Column(name = "leaving_player_id")
    private int leavingPlayerId;

    @Column(name = "substitution_time")
    private String substitutionTime;

    @Column(name = "entering_player_id")
    private int enteringPlayerId;
    

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name= "substitution_reason_id")
    private SubstitutionReasons substitutionReason;

    public MatchSubstitutionHistory() {
    }

    public MatchSubstitutionHistory(int leavingPlayerId, String substitutionTime, int enteringPlayerId, SubstitutionReasons substitutionReason, Matches match) {
        this.leavingPlayerId = leavingPlayerId;
        this.substitutionTime = substitutionTime;
        this.enteringPlayerId = enteringPlayerId;
        this.substitutionReason = substitutionReason;
        this.match=match;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matches getMatch() {
        return match;
    }

    public void setMatch(Matches match) {
        this.match = match;
    }

    public int getLeavingPlayerId() {
        return leavingPlayerId;
    }

    public void setLeavingPlayerId(int leavingPlayerId) {
        this.leavingPlayerId = leavingPlayerId;
    }

    public String getSubstitutionTime() {
        return substitutionTime;
    }

    public void setSubstitutionTime(String substitutionTime) {
        this.substitutionTime = substitutionTime;
    }

    public int getEnteringPlayerId() {
        return enteringPlayerId;
    }

    public void setEnteringPlayerId(int enteringPlayerId) {
        this.enteringPlayerId = enteringPlayerId;
    }

    public SubstitutionReasons getsubstitutionReason()
    {
        return substitutionReason;
    }

    public void setSubstitutionReason(SubstitutionReasons substitutionReason)
    {
        this.substitutionReason = substitutionReason;
    }

    @Override
    public String toString() {
        return "MatchSubstitutionHistory{" + "id=" + id  + ", leavingPlayerId=" + leavingPlayerId + ", substitutionTime=" + substitutionTime + ", enteringPlayerId=" + enteringPlayerId +
                "powod zejscia= "+ substitutionReason +'}';
    }

}
