/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author Przemek
 */


@Entity
@Table(name = "players")
public class Players {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "height")
    private float height;

    @Column(name = "weight")
    private float weight;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "team_id")
    private Teams team;

    // tutaj usuwanie kaskadowe musi byc
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlayerTeamsHistory> playerTeamsHistory;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<PlayerPosition> playerPosition;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<PlayerMatchPositions> playerMatchPositions;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<PlayerMatchAchievements> playerMatchAchievements;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "leaving_player_id")
    private List<MatchSubstitutionHistory> matchSubstitutionHistoryLeaving;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entering_player_id")
    private List<MatchSubstitutionHistory> matchSubstitutionHistoryEntering;

    public Players() {
    }

    public Players(String name, String surname, String dateOfBirth, float height, float weight) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public List<PlayerTeamsHistory> getPlayerTeamsHistory() {
        return playerTeamsHistory;
    }

    public void setPlayerTeamsHistory(List<PlayerTeamsHistory> playerTeamsHistory) {
        this.playerTeamsHistory = playerTeamsHistory;
    }

    public List<PlayerPosition> getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(List<PlayerPosition> zawPoz) {
        this.playerPosition = zawPoz;
    }

    public List<PlayerMatchPositions> getPlayerMatchPositions() {
        return playerMatchPositions;
    }

    public void setPlayerMatchPositions(List<PlayerMatchPositions> playerMatchPositions) {
        this.playerMatchPositions = playerMatchPositions;
    }

    public List<PlayerMatchAchievements> getPlayerMatchAchievements() {
        return playerMatchAchievements;
    }

    public void setPlayerMatchAchievements(List<PlayerMatchAchievements> playerMatchAchievements) {
        this.playerMatchAchievements = playerMatchAchievements;
    }

    public List<MatchSubstitutionHistory> getMatchSubstitutionHistoryLeaving() {
        return matchSubstitutionHistoryLeaving;
    }

    public void setMatchSubstitutionHistoryLeaving(List<MatchSubstitutionHistory> matchSubstitutionHistoryLeaving) {
        this.matchSubstitutionHistoryLeaving = matchSubstitutionHistoryLeaving;
    }

    public List<MatchSubstitutionHistory> getMatchSubstitutionHistoryEntering() {
        return matchSubstitutionHistoryEntering;
    }

    public void setMatchSubstitutionHistoryEntering(List<MatchSubstitutionHistory> matchSubstitutionHistoryEntering) {
        this.matchSubstitutionHistoryEntering = matchSubstitutionHistoryEntering;
    }

    public void addPlayerPosition(PlayerPosition playerPosition) {
        if (this.playerPosition == null) {
            this.playerPosition = new ArrayList<>();
        }

        this.playerPosition.add(playerPosition);
        playerPosition.setPlayer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Players)) return false;
        Players that = (Players) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Players{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + ", height=" + height + ", weight=" + weight + '}';
    }

}