package pkg12.men.s.morris;

import java.util.ArrayList;

public class Line 
{
    public Cell [] cells ;
    public boolean isDiagonal ;
    
    public Line(boolean isDiagonal)
    {
        cells = new Cell[3] ;
        this.isDiagonal = isDiagonal ;
    }
    
    public void setCells(Cell cell1,Cell cell2,Cell cell3)
    {
        cells[0] = cell1 ;
        cells[1] = cell2 ;
        cells[2] = cell3 ;
    }
    
    public boolean contains(Position thatPosition)
    {
        for(Cell tmpC : cells)
            if(tmpC.position.equals(thatPosition)) return true ;
        return false ;
    }
    
    public boolean dooz(boolean isMine)
    {
        if(cells[0].checker!=null&&cells[1].checker!=null&&cells[2].checker!=null)
          if(cells[0].checker.isMyChecker==isMine&&cells[1].checker.isMyChecker==isMine
                  &&cells[2].checker.isMyChecker==isMine) return true ;
        return false ;
    }
    
    public boolean hasConstantY()
    {
        return cells[0].position.y==cells[1].position.y&&cells[1].position.y==cells[2].position.y ;
    }
    
    public boolean isVacant()
    {
        return cells[0].checker==null&&cells[1].checker==null&&cells[2].checker==null;
    }
    
    public boolean filledOnce(boolean isMine)
    {
        if(cells[0].checker==null&&cells[1].checker==null&&cells[2].checker!=null)
            if(cells[2].checker.isMyChecker==isMine) return true ;
        if(cells[1].checker==null&&cells[2].checker==null&&cells[0].checker!=null)
            if(cells[0].checker.isMyChecker==isMine) return true ;
        if(cells[0].checker==null&&cells[2].checker==null&&cells[1].checker!=null)
            if(cells[1].checker.isMyChecker==isMine) return true ;
        return false ;
    }
       
    public Cell getVacantWhenFilledTwice(boolean isMine)
    {
        if(cells[0].checker!=null&&cells[1].checker!=null&&cells[2].checker==null)
            if(cells[0].checker.isMyChecker==isMine&&cells[1].checker.isMyChecker==isMine) 
                return cells[2] ;
        if(cells[0].checker==null&&cells[1].checker!=null&&cells[2].checker!=null)
            if(cells[1].checker.isMyChecker==isMine&&cells[2].checker.isMyChecker==isMine) 
                return cells[0] ;
        if(cells[0].checker!=null&&cells[1].checker==null&&cells[2].checker!=null)
            if(cells[0].checker.isMyChecker==isMine&&cells[2].checker.isMyChecker==isMine) 
                return cells[1] ;
        return null ;
    }
    
    public Cell getOppWhenFilledTwice()
    {
        if(cells[0].checker!=null&&cells[1].checker!=null&&cells[2].checker!=null)
        {
            if(cells[0].checker.isMyChecker&&cells[1].checker.isMyChecker
                    &&!cells[2].checker.isMyChecker) return cells[2] ;
            if(cells[0].checker.isMyChecker&&!cells[1].checker.isMyChecker
                    &&cells[2].checker.isMyChecker) return cells[1] ;
            if(!cells[0].checker.isMyChecker&&cells[1].checker.isMyChecker
                    &&cells[2].checker.isMyChecker) return cells[0] ;
        }
        return null ;
    }
    
    public ArrayList<Cell> dualVacant(boolean isMine)
    {
        ArrayList<Cell> dualVacantAL = new ArrayList<>();
        if(cells[0].checker==null&&cells[1].checker==null)
            if(cells[2].checker.isMyChecker==isMine) 
            {
                dualVacantAL.add(cells[0]);
                dualVacantAL.add(cells[1]);
            }
        if(cells[1].checker==null&&cells[2].checker==null)
            if(cells[0].checker.isMyChecker==isMine)
            {
                dualVacantAL.add(cells[1]);
                dualVacantAL.add(cells[2]);
            }
        if(cells[0].checker==null&&cells[2].checker==null)
            if(cells[1].checker.isMyChecker==isMine) 
            {
                dualVacantAL.add(cells[0]);
                dualVacantAL.add(cells[2]);
            }
        return dualVacantAL ;
    }
}
