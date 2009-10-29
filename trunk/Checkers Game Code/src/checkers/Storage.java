package checkers;

import java.util.*;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.489A3A6B-B4A5-65DC-5BA2-96A9B4A68D83]
// </editor-fold> 
public class Storage
{
    private ArrayList<User> users;
    private ArrayList games;

    // generic Storage constructor.  for testing purposes, a user list
    // containing player1 and player 2 is created.
    // 
    public Storage () 
    {
        ArrayList<User> users = new ArrayList<User>();
        User player1 = new User("player1");
        User player2 = new User("player2");
        users.add(player1);
        users.add(player2);
        users = new ArrayList<User>();
    }

    public ArrayList getGames ()
    {
        return games;
    }

    public void setGames (ArrayList val)
    {
        this.games = val;
    }
    // the getUsers method returns an ArrayList of all users that have been
    // previously created in the system.  for testing purposes, this method now
    // returns a list containing two generic Users, player1 & player2.
    public ArrayList getUsers ()
    {
       return users;
    }

    public void setUsers (ArrayList val)
    {
        this.users = val;
    }
}

