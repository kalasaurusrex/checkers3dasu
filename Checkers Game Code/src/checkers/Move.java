/*
 * The Move class helps facilitate communication between the Referee and
 * GameScreen class while processing moves in 3D Checkers
 *
 * Author: David Clark
 */ 

package checkers;

import java.util.ArrayList;

public class Move
{
    private ArrayList<Square> jump;
    private boolean validMove;
    private boolean moreJumps;
    private boolean requiredJump;
    private boolean warped;
    private boolean landedOnMine;
    private int gameOver;

    public Move()
    {
        jump = new ArrayList();
        validMove = false;
        moreJumps = false;
        requiredJump = false;
        warped = false;
        landedOnMine = false;
        gameOver = 0;
    }

    public void clearMove()
    {
        jump.clear();
        validMove = false;
        moreJumps = false;
        requiredJump = false;
        warped = false;
        landedOnMine = false;
    }

    public void addJump(Square s)
    {
        jump.add(s);
    }

     public ArrayList<Square> getJump()
    {
        return jump;
    }

    public void clearJump()
    {
        jump.clear();
    }

    public int jumpSize()
    {
        return jump.size();
    }

    public void setValidMove(boolean valid)
    {
        validMove = valid;
    }

    public boolean isValidMove()
    {
        return validMove;
    }

    public void setMoreJumps(boolean more)
    {
        moreJumps = more;
    }

    public boolean moreJumps()
    {
        return moreJumps;
    }

    public void setRequiredJump(boolean required)
    {
        requiredJump = required;
    }

    public boolean requiredJump()
    {
        return requiredJump;
    }

    public void setWarp(boolean warp)
    {
        warped = warp;
    }

    public boolean hasWarped()
    {
        return warped;
    }

    public void setGameOver(int over)
    {
        gameOver = over;
    }

    public int getGameOver()
    {
        return gameOver;
    }

    public void setLandedOnMine(boolean mine)
    {
        landedOnMine = mine;
    }

    public boolean landedOnMine()
    {
        return landedOnMine;
    }
}
