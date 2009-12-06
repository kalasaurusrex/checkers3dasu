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
    private boolean kinged;
    private int gameOver;

    //default constructor
    public Move()
    {
        jump = new ArrayList();
        validMove = false;
        moreJumps = false;
        requiredJump = false;
        warped = false;
        landedOnMine = false;
        kinged = false;
        gameOver = 0;
    }

    //reset variables
    public void clearMove()
    {
        jump.clear();
        validMove = false;
        moreJumps = false;
        requiredJump = false;
        warped = false;
        landedOnMine = false;
        kinged = false;
    }

    //add "jumpable" square to the list
    public void addJump(Square s)
    {
        jump.add(s);
    }

    //return the list of "jumpable" squares
     public ArrayList<Square> getJump()
    {
        return jump;
    }

     //clear the list of "jumpable" squares
    public void clearJump()
    {
        jump.clear();
    }

    //return the size of the "jumpable" list
    public int jumpSize()
    {
        return jump.size();
    }

    //set the "valid move" flag
    public void setValidMove(boolean valid)
    {
        validMove = valid;
    }

    //return the "valid move" flag
    public boolean isValidMove()
    {
        return validMove;
    }

    //set the "more jumps" flag
    public void setMoreJumps(boolean more)
    {
        moreJumps = more;
    }

    //return the "more jumps" flag
    public boolean moreJumps()
    {
        return moreJumps;
    }

    //set the "required jumps" flag
    public void setRequiredJump(boolean required)
    {
        requiredJump = required;
    }

    //return the "required jumps" flag
    public boolean requiredJump()
    {
        return requiredJump;
    }

    //set the "warped" flag
    public void setWarp(boolean warp)
    {
        warped = warp;
    }

    //return the "warped" flag
    public boolean hasWarped()
    {
        return warped;
    }

    //set the "game over" flag
    public void setGameOver(int over)
    {
        gameOver = over;
    }

    //return the "game over" flag
    public int getGameOver()
    {
        return gameOver;
    }

    //set the "landed on mine" flag
    public void setLandedOnMine(boolean mine)
    {
        landedOnMine = mine;
    }

    //return the "landed on mine" flag
    public boolean landedOnMine()
    {
        return landedOnMine;
    }

    //set the "kinged" flag
    public void setKinged(boolean king)
    {
        kinged = king;
    }

    //return the "kinged" flag
    public boolean gotKinged()
    {
        return kinged;
    }
}
