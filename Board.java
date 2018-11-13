package pkg12.men.s.morris;

import java.util.ArrayList;

public class Board 
{
    public Cell [][] cells ;
    public ArrayList<Cell> myCells ;
    public ArrayList<Cell> oppCells;
    public ArrayList<Cell> filledCells ;
    public ArrayList<Cell> emptyCells ;
    public Cell lastOppCell ;
    public Cell lastMyCell ;
    public Cell lastPopped ;
    
    public Board()
    {
        cells = new Cell[8][3];
        for(int i=0;i<8;i++)
            for(int j=0;j<3;j++)
                cells[i][j] = new Cell(i,j);
        myCells = new ArrayList<>();
        oppCells = new ArrayList<>();        
        filledCells = new ArrayList<>();
        emptyCells = new ArrayList<>();
        lastOppCell = null ;
        lastMyCell = null ;
        lastPopped = null ;
    }
    
    public void update()
    {
        for(int i=0;i<8;i++)
        {
            boolean flag = true ;
            for(int j=0;j<3;j++)
            {
                Checker tmpCh = cells[i][j].checker ;
                if(tmpCh!=null&&!tmpCh.isMyChecker&&!oppCells.contains(cells[i][j]))
                {
                    lastOppCell = cells[i][j] ;
                    flag = false ;
                    break ;
                }
            }
            if(!flag) break ;
        }
        
        for(int i=0;i<8;i++)
        {
            boolean flag = true ;
            for(int j=0;j<3;j++)
            {
                Checker tmpCh = cells[i][j].checker ;
                if(tmpCh!=null&&tmpCh.isMyChecker&&!myCells.contains(cells[i][j]))
                {
                    if(PutStrategy.isCorner(i)&&(j==2||j==0))
                    {
                    lastMyCell = cells[i][j] ;
                    flag = false ;
                    break ;
                    }
                }
            }
            if(!flag) break ;
        }
        
        for(int i=0;i<8;i++)
        {
            boolean flag = true ;
            for(int j=0;j<3;j++)
            {
                Checker tmpCh = cells[i][j].checker ;
                if(tmpCh==null&&myCells.contains(cells[i][j]))
                {
                    lastPopped = cells[i][j] ;
                    flag = false ;
                    break ;
                }
            }
            if(!flag) break ;
        }
         
        myCells.clear();
        oppCells.clear();
        filledCells.clear();
        emptyCells.clear();
        for(int i=0;i<8;i++)
            for(int j=0;j<3;j++)
            {
                Checker tmpCh = cells[i][j].checker ;
                if(tmpCh==null) emptyCells.add(cells[i][j]);
                else 
                {
                    filledCells.add(cells[i][j]);
                    if(tmpCh.isMyChecker) myCells.add(cells[i][j]);
                    else oppCells.add(cells[i][j]);
                }
            }
    }
}
