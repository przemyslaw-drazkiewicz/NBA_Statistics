/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "matches")
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "host_team_id")
    private Teams hostTeam;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "guest_team_id")
    private Teams guestTeam;

    @Column(name = "date")
    private String date;

    @Column(name = "host_points")
    private int hostPoints;

    @Column(name = "guest_points")
    private int guestPoints;

    @Column(name = "extra_time_count")
    private int extraTimeCount;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "season_id")
    private Seasons season;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<PlayerMatchPositions> playerMatchPositions;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<PlayerMatchAchievements> playerMatchAchievements;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    private List<MatchSubstitutionHistory> matchSubstitutionHistory;

    public Matches() {

    }

    public Matches(String date, int hostPoints, int guestPoints, int extraTimeCount) {
        this.date = date;
        this.hostPoints = hostPoints;
        this.guestPoints = guestPoints;
        this.extraTimeCount = extraTimeCount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teams getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(Teams hostTeam) {
        this.hostTeam = hostTeam;
    }

    public Teams getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Teams guestTeam) {
        this.guestTeam = guestTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHostPoints() {
        return hostPoints;
    }

    public void setHostPoints(int hostPoints) {
        this.hostPoints = hostPoints;
    }

    public int getGuestPoints() {
        return guestPoints;
    }

    public void setGuestPoints(int guestPoints) {
        this.guestPoints = guestPoints;
    }

    public int getExtraTimeCount() {
        return extraTimeCount;
    }

    public void setExtraTimeCount(int extraTimeCount) {
        this.extraTimeCount = extraTimeCount;
    }

    public Seasons getSeason() {
        return season;
    }

    public void setSeason(Seasons season) {
        this.season = season;
    }

    public List<PlayerMatchAchievements> getPlayerMatchAchievements() {
        return playerMatchAchievements;
    }

    public void setPlayerMatchAchievements(List<PlayerMatchAchievements> playerMatchAchievements) {
        this.playerMatchAchievements = playerMatchAchievements;
    }

    public List<MatchSubstitutionHistory> getMatchSubstitutionHistory() {
        return matchSubstitutionHistory;
    }

    public void setMatchSubstitutionHistory(List<MatchSubstitutionHistory> matchSubstitutionHistory) {
        this.matchSubstitutionHistory = matchSubstitutionHistory;
    }

    public void dodajHistZmianWMeczu (MatchSubstitutionHistory newMatchSubstitutionHistory){
        if (matchSubstitutionHistory == null){
            matchSubstitutionHistory = new ArrayList<>();
        }
        matchSubstitutionHistory.add(newMatchSubstitutionHistory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matches)) return false;
        Matches that = (Matches) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Matches{" + "id=" + id + ", date=" + date + ", hostPoints=" + hostPoints + ", guestPoints=" + guestPoints + ", extraTimeCount=" + extraTimeCount + '}';
    }
}
