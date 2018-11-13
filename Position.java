package pkg12.men.s.morris;

public class Position 
{
    public final int x , y ;
    public Position(int x,int y)
    {
        this.x = x ;
        this.y = y ; 
    }
    @Override
    public boolean equals(Object thatObject)
    {
        Position that = (Position) thatObject ;
        if(x==that.x && y==that.y) return true ;
        return false ;
    }
}
