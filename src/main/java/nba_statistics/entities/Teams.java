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
 * @author Przemek
 */
@Entity
@Table(name = "teams")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    int id;

    @Column(name = "division")
    private String division;

    @Column(name = "conference")
    private String conference;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "hostTeam", cascade={CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH})
    private List<Matches> matchesHost;

    @OneToMany(mappedBy = "guestTeam", cascade={CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH})
    private List<Matches> matchesGuest;
    
    @OneToMany(mappedBy = "team", cascade={CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH})
    private List<PlayerTeamsHistory> playerTeamsHistory;
    
    //druzyny - players
    @OneToMany(mappedBy = "team", cascade={CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH})
    private List<Players> players;

    public Teams() {
    }

    public Teams(String division, String conference, String name, String location) {
        this.division = division;
        this.conference = conference;
        this.name = name;
        this.location = location;
    }

    public void addMatchesHost(Matches mecz) {
        if (matchesHost == null) {
            matchesHost = new ArrayList<>();
        } else {
            matchesHost.add(mecz);

            mecz.setHostTeam(this);
        }
    }

    public void addMatchesGuest(Matches mecz) {
        if (matchesGuest == null) {
            matchesGuest = new ArrayList<>();
        } else {
            matchesGuest.add(mecz);

            mecz.setHostTeam(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Teams{" + "id=" + id + ", division=" + division + ", conference=" + conference + ", name=" + name + ", location=" + location + '}';
    }


}
