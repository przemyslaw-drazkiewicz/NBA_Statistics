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
@Table(name = "positions")
public class Positions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="position", cascade = CascadeType.ALL)
    private List<PlayerPosition> playerPosition;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<PlayerMatchPositions> playerMatchPositions;

    public Positions(){}

    public Positions(String name) {
        this.name = name;
    }

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

    public List<PlayerPosition> getPlayerPosition(){
        return playerPosition;
    }
    
    public void setPlayerPosition(List<PlayerPosition> zawPoz){
        this.playerPosition = zawPoz;
    }
    
    public void addPlayerPosition(PlayerPosition playerPosition){
        if (this.playerPosition == null){
            this.playerPosition = new ArrayList<>();
        }
        
        this.playerPosition.add(playerPosition);
        playerPosition.setPozycje(this);
    }

    @Override
    public String toString() {
        return "Positions{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
