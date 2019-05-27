package nba_statistics.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Przemek
 */

@Embeddable
public class PlayerMatchAchievementsId implements Serializable {

    //field representing part of composite PK and at the same time FK
    @Column(name = "player_id")
    int playerId;

    //field representing part of composite PK and at the same time FK
    @Column(name = "match_id")
    int matchId;

    public PlayerMatchAchievementsId(){};

    public PlayerMatchAchievementsId(int playerId, int matchId) {
        this.playerId = playerId;
        this.matchId = matchId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerMatchAchievementsId)) return false;
        PlayerMatchAchievementsId that = (PlayerMatchAchievementsId) o;
        return Objects.equals(getPlayerId(), that.getPlayerId()) &&
                Objects.equals(getMatchId(), that.getMatchId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, matchId);
    }
}
