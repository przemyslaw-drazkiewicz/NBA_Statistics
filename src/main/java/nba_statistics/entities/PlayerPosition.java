/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity(name="PlayerPosition")
@Table(name = "player_position")
public class PlayerPosition {

    @EmbeddedId
    private PlayerPositionId id;

    @ManyToOne
    @MapsId("player_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "player_id")
    private Players player;

    @ManyToOne
    @MapsId("position_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "position_id")
    private Positions position;

    public PlayerPosition(){}

    public PlayerPosition(Players player, Positions position) {
        id = new PlayerPositionId();
        this.player = player;
        this.position = position;
    }

    public PlayerPositionId getId() {
        return id;
    }

    public void setId(PlayerPositionId id) {
        this.id = id;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public Positions getPozycje() {
        return position;
    }

    public void setPozycje(Positions positions) {
        this.position = positions;
    }

    @Override
    public String toString() {
        return "PlayerPosition{" +
                "id=" + id +
                ", player=" + player +
                ", pozycje=" + position +
                '}';
    }
}
