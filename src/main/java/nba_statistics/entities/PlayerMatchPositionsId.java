package nba_statistics.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public
class PlayerMatchPositionsId implements Serializable {

    @Column(name = "player_id")
    int playerId;

    @Column(name = "match_id")
    int matchId;

    @Column(name = "position_id")
    int positionId;

    public PlayerMatchPositionsId(){}

    public PlayerMatchPositionsId(int playerId, int matchId, int positionId) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.positionId = positionId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerMatchPositionsId)) return false;
        PlayerMatchPositionsId that = (PlayerMatchPositionsId) o;
        return Objects.equals(playerId, that.playerId) &&
                Objects.equals(positionId, that.positionId)&&
                Objects.equals(matchId, that.matchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, positionId, matchId);
    }
}
