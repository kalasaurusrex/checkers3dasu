package checkers;

import java.io.Serializable;
import java.util.HashMap;
/**
 *
 * @author kalamath
 */
// the StatRecord class is used to keep track of pertinent statistics for a
// single user of the game.
// total wins, losses, and ties are maintained.
// the pvpRecords mapping enables
public class StatRecord implements Serializable {
    int wins;
    int losses;
    int ties;
    HashMap<User, Record> pvpRecords;

    public StatRecord() {
        wins = 0;
        losses = 0;
        ties = 0;
        pvpRecords = new HashMap<User, Record>();
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
    public int getTies() {
        return ties;
    }
    public void addWin() {
        wins++;
    }
    public void addLoss() {
        losses++;
    }
    public void addTie() {
        ties++;
    }
    public void removeWin() {
        wins--;
        if (wins < 0) {
            wins = 0;
        }
    }
    public void removeLoss() {
        losses--;
        if (losses < 0) {
            losses = 0;
        }
    }
    public void removeTie() {
        ties--;
        if (ties < 0) {
            ties = 0;
        }
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public void setTies(int ties) {
        this.ties = ties;
    }
    public String toString() {
        return wins + " " + losses + " " + ties;
    }
}
