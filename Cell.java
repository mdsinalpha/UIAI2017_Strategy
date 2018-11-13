package pkg12.men.s.morris;

public class Cell 
{
    public final Position position ;
    public Checker checker ;
    
    public Cell(int x , int y)
    {
        position = new Position(x, y);
        checker = null ;
    }
    
    @Override
    public boolean equals(Object thatCell)
    {
        Cell that = (Cell) thatCell ;
        return position.equals(that.position);
    }
}
