package pkg12.men.s.morris;

import java.util.ArrayList;

public class LineList 
{
    public Cell [][] cells ;
    public Line [] lines ;
    
    public LineList(Cell[][] cells)
    {
        this.cells = cells ;
        
        lines = new Line[20];
        for(int i=0;i<16;i++) lines[i] = new Line(false);
        for(int i=16;i<20;i++) lines[i] = new Line(true);
        
        lines[0].setCells(cells[0][2], cells[1][2], cells[2][2]);
        lines[1].setCells(cells[2][2], cells[3][2], cells[4][2]);
        lines[2].setCells(cells[4][2], cells[5][2], cells[6][2]);
        lines[3].setCells(cells[6][2], cells[7][2], cells[0][2]);
        
        lines[4].setCells(cells[0][1], cells[1][1], cells[2][1]);
        lines[5].setCells(cells[2][1], cells[3][1], cells[4][1]);
        lines[6].setCells(cells[4][1], cells[5][1], cells[6][1]);
        lines[7].setCells(cells[6][1], cells[7][1], cells[0][1]);
        
        lines[8].setCells(cells[0][0], cells[1][0], cells[2][0]);
        lines[9].setCells(cells[2][0], cells[3][0], cells[4][0]);
        lines[10].setCells(cells[4][0], cells[5][0], cells[6][0]);
        lines[11].setCells(cells[6][0], cells[7][0], cells[0][0]);
        
        lines[12].setCells(cells[1][0], cells[1][1], cells[1][2]);
        lines[13].setCells(cells[3][0], cells[3][1], cells[3][2]);
        lines[14].setCells(cells[5][0], cells[5][1], cells[5][2]);
        lines[15].setCells(cells[7][0], cells[7][1], cells[7][2]);
        
        lines[16].setCells(cells[0][0], cells[0][1], cells[0][2]);
        lines[17].setCells(cells[2][0], cells[2][1], cells[2][2]);
        lines[18].setCells(cells[4][0], cells[4][1], cells[4][2]);
        lines[19].setCells(cells[6][0], cells[6][1], cells[6][2]);
    }
    
    public Position linesEmptyUnion(Line line1 , Line line2)
    {
        Cell [] cells1 = line1.cells ;
        Cell [] cells2 = line2.cells ;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(cells1[i].position.equals(cells2[j].position) 
                        &&cells1[i].checker==null) 
                    return cells1[i].position ;
        return null ;
    }
    
    public ArrayList<Line> findLinesByCell(Cell thatCell)
    {
        ArrayList<Line> linesAL = new ArrayList<>();
        for(Line tmpL : lines)
            if(tmpL.contains(thatCell.position)) linesAL.add(tmpL);
        return linesAL ;
    }
    
    public boolean doozed(Position that ,boolean isMine)
    {
        Object [] lines = findLinesByCell(cells[that.x][that.y]).toArray();
        for(Object tmpL : lines)
        {
            Line line = (Line) tmpL ;
            if(line.dooz(isMine)) return true ; 
        }
        return false ;
    }
}
