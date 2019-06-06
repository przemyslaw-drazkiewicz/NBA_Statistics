/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity(name="PlayerMatchAchievements")
@Table(name = "player_match_achievements")
public class PlayerMatchAchievements {
    
    @EmbeddedId
    private PlayerMatchAchievementsId id;

    @ManyToOne
    @MapsId("player_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "player_id")
    private Players player;

    @ManyToOne
    @MapsId("match_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "match_id")
    private Matches match;
    
    @Column(name = "scored_points")
    private int scoredPoints;
    
    @Column(name = "steals")
    private int steals;

    @Column(name = "blocks")
    private int blocks;

    @Column(name = "rebounds")
    private int rebounds;

    @Column(name = "fouls")
    private int fouls;
    
    @Column(name = "technical_fouls")
    private int technicalFouls;
    
    public PlayerMatchAchievements(){};

    public PlayerMatchAchievements(Players player, Matches match, int scoredPoints, int steals, int rebounds, int blocks, int fouls, int technicalFouls) {
        this.player = player;
        this.match = match;
        this.scoredPoints = scoredPoints;
        this.steals = steals;
        this.rebounds = rebounds;
        this.blocks = blocks;
        this.fouls = fouls;
        this.technicalFouls = technicalFouls;
        this.id = new PlayerMatchAchievementsId();
    }

    public PlayerMatchAchievements(Players player, Matches match) {
        this.player = player;
        this.match = match;
        this.id = new PlayerMatchAchievementsId(player.getId(), match.getId());
    }

    public PlayerMatchAchievementsId getId() {
        return id;
    }

    public void setId(PlayerMatchAchievementsId id) {
        this.id = id;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public void setScoredPoints(int scoredPoints) {
        this.scoredPoints = scoredPoints;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getTechnicalFouls() {
        return technicalFouls;
    }

    public void setTechnicalFouls(int technicalFouls) {
        this.technicalFouls = technicalFouls;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public Matches getMatch() {
        return match;
    }

    public void setMatch(Matches match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "PlayerMatchAchievements{" +
                "idPlayer=" + id.playerId +
                ", idMatch=" + id.matchId +
                ", scoredPoints=" + scoredPoints +
                ", steals=" + steals +
                ", rebounds=" + rebounds +
                ", blocks=" + blocks +
                ", fouls=" + fouls +
                ", technicalFouls=" + technicalFouls +
                '}';
    }
}