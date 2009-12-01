package checkers;

import java.io.Serializable;

// the Record class represents a record of wins, losses, and ties.
// it is used as part of a StatRecord object to keep track of a player's record
// vs another player.
public class Record implements Serializable
{
    private int wins;
    private int losses;
    private int ties;
    
    public Record() {
        wins = 0;
        losses = 0;
        ties = 0;
    }

    public Record(int wins, int losses, int ties)
    {
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
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
        return "(" + wins + ", " + losses + ", " + ties + ")";
    }
}

