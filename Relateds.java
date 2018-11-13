package pkg12.men.s.morris;

import java.util.ArrayList;

public class Relateds 
{
    public ArrayList<Position> relateds ;
    
    public Relateds()
    {
        relateds = new ArrayList<>();
    }
    
    public ArrayList<Position> relateds(Position that)
    {
        int x = that.x , y = that.y ;
        switch (y) 
        {
            case 0:
                switch(x)
                {
                    case 0 :
                        add(1,0);
                        add(0,1);
                        add(7,0);
                        break ;
                    case 1 :
                        add(0,0);
                        add(1,1);
                        add(2,0);
                        break ;
                    case 2 :
                        add(1,0);
                        add(2,1);
                        add(3,0);
                        break ;
                    case 3 :
                        add(2,0);
                        add(3,1);
                        add(4,0);
                        break ;
                    case 4 :
                        add(3,0);
                        add(4,1);
                        add(5,0);
                        break ;
                    case 5 :
                        add(4,0);
                        add(5,1);
                        add(6,0);
                        break ;
                    case 6 :
                        add(5,0);
                        add(6,1);
                        add(7,0);
                        break ;
                    case 7 :
                        add(6,0);
                        add(7,1);
                        add(0,0);
                }  
                break;
            case 1:
                switch(x)
                {
                    case 0 :
                        add(0,0);
                        add(1,1);
                        add(0,2);
                        add(7,1);
                        break ;
                    case 1 :
                        add(1,0);
                        add(2,1);
                        add(1,2);
                        add(0,1);
                        break ;
                    case 2 :
                        add(1,1);
                        add(2,0);
                        add(2,2);
                        add(3,1);
                        break ;
                    case 3 :
                        add(2,1);
                        add(3,2);
                        add(3,0);
                        add(4,1);
                        break ;
                    case 4 :
                        add(3,1);
                        add(4,0);
                        add(4,2);
                        add(5,1);
                        break ;
                    case 5 :
                        add(5,0);
                        add(4,1);
                        add(5,2);
                        add(6,1);
                        break ;
                    case 6 :
                        add(5,1);
                        add(6,0);
                        add(6,2);
                        add(7,1);
                        break ;
                    case 7 :
                        add(6,1);
                        add(7,0);
                        add(7,2);
                        add(1,0);
                }  
                break;
            default:
                switch(x)
                {
                    case 0 :
                        add(1,0);
                        add(1,2);
                        add(7,2);
                        break ;
                    case 1 :
                        add(0,2);
                        add(1,1);
                        add(2,2);
                        break ;
                    case 2 :
                        add(1,2);
                        add(2,1);
                        add(3,2);
                        break ;
                    case 3 :
                        add(2,2);
                        add(3,1);
                        add(4,2);
                        break ;
                    case 4 :
                        add(3,2);
                        add(4,1);
                        add(5,2);
                        break ;
                    case 5 :
                        add(4,2);
                        add(5,1);
                        add(6,2);
                        break ;
                    case 6 :
                        add(5,2);
                        add(6,1);
                        add(7,2);
                        break ;
                    case 7 :
                        add(6,2);
                        add(7,1);
                        add(0,2);
                }  
        }
         return relateds ;
    }
    
    public void add(int x , int y)
    {
        relateds.add(new Position(x, y));
    }
}
