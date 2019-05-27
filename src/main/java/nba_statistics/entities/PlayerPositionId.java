package nba_statistics.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Adam
 */

@Embeddable
public
class PlayerPositionId implements Serializable {

    @Column(name = "player_id")
    int playerId;

    @Column(name = "position_id")
    int positionId;

    public PlayerPositionId() {
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public PlayerPositionId(int playerId, int positionId) {
        this.playerId = playerId;
        this.positionId = positionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPositionId)) return false;
        PlayerPositionId that = (PlayerPositionId) o;
        return Objects.equals(playerId, that.playerId) &&
                Objects.equals(positionId, that.positionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, positionId);
    }
}
