package nba_statistics.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity(name = "PlayerMatchPositions")
@Table(name = "player_match_positions")
public class PlayerMatchPositions {

    @EmbeddedId
    private PlayerMatchPositionsId id;

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

    @ManyToOne
    @MapsId("position_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "position_id")
    private Positions position;

    public PlayerMatchPositions(){};

    public PlayerMatchPositions(Players player, Matches match, Positions position) {
        id = new PlayerMatchPositionsId();
        this.player = player;
        this.match = match;
        this.position = position;
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

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "PlayerMatchPositions{" +
                "id=" + id +
                ", player=" + player +
                ", match=" + match +
                ", position=" + position +
                '}';
    }
}
