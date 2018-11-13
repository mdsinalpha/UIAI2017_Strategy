package pkg12.men.s.morris;
import java.util.ArrayList;
import java.util.Random;

public class PutStrategy 
{   
    public static int [] corners = {0,2,4,6};
    
    public static void execute(Game thatGame)
    {
        System.out.println("This is a sample put output!");
        Random random = new Random();
        LineList ll = new LineList(thatGame.board.cells);
        
        // STRATEGY 0
        {
            System.out.println("STRATEGY 0 Started.");
            if(thatGame.board.lastPopped!=null)
            {
                Position that = thatGame.board.lastPopped.position ;
                thatGame.board.lastPopped = null ;
                System.out.println("STRATEGY 0 Finished.");
                thatGame.putChecker(that);
                return ;
            }
        }
        
        // STRATEGY 1
        {
        System.out.println("STRATEGY 1 Started.");
        Object [] myCells = thatGame.board.myCells.toArray();
        for(int i=0;i<myCells.length;i++)
           for(int j=i+1;j<myCells.length;j++)
           {
               Cell first = (Cell) myCells[i];
               Cell second = (Cell) myCells[j];
               if(first.position.x==second.position.x 
                       || (first.position.y==second.position.y 
                       && isLinearRelative(first.position.x, second.position.x)))
               {
                   Position tripleP = triple(first.position,second.position);
                   if(thatGame.board.cells[tripleP.x][tripleP.y].checker==null)
                   {
                   System.out.println("STRATEGY 1 Finished.");
                   thatGame.putChecker(tripleP);
                   return ;
                   }
               }
           }
        }
        
        // STRATEGY 1.5
        {
        System.out.println("STRATEGY 1.5 Started.");
        Object [] oppCells = thatGame.board.oppCells.toArray();
        for(int i=0;i<oppCells.length;i++)
           for(int j=i+1;j<oppCells.length;j++)
           {
               Cell first = (Cell) oppCells[i];
               Cell second = (Cell) oppCells[j];
               if(first.position.x==second.position.x 
                       || (first.position.y==second.position.y 
                       && isLinearRelative(first.position.x, second.position.x)))
               {
                   Position tripleP = triple(first.position,second.position);
                   if(thatGame.board.cells[tripleP.x][tripleP.y].checker==null)
                   {
                     
                       Object[] lines = 
                               ll.findLinesByCell(thatGame.board.cells[tripleP.x][tripleP.y]).toArray();
                         // 1.5 1
                        for(int i1=0;i1<lines.length;i1++)
                           for(int j1=i1+1;j1<lines.length;j1++)
                        {
                        Line first1 = ll.lines[i1];
                        Line second1 = ll.lines[j1];
                        if(first1.filledOnce(true)&&second1.filledOnce(true))
                        {
                          Position unionP = ll.linesEmptyUnion(first1, second1);
                          if(unionP!=null)
                          {
                             if(tripleP.equals(unionP))
                             {
                             System.out.println("STRATEGY 1.5 1 Finished.");
                             thatGame.putChecker(tripleP);
                             return ;
                             }
                          }
                       }
                       }
                       // 1.5 2 
                       for(Object tmpL : lines)
                       {
                         Line that = (Line) tmpL ;
                         if(that.filledOnce(true))
                         { 
                          System.out.println("STRATEGY 1.5 2 Finished.");
                          thatGame.putChecker(tripleP);
                          return ;
                         }
                       }
                   }
               }
           }
        }
        
        // STRATEGY 2
        {
          System.out.println("STRATEGY 2 Started.");
          if(thatGame.board.lastMyCell==null)
          {
            int [] corners = {0,2,4,6};
            ArrayList<Integer> availableCorners = new ArrayList<>();
            for(int i=0;i<4;i++)
            if(thatGame.board.cells[corners[i]][2].checker==null) availableCorners.add(corners[i]);
            int selected = random.nextInt(availableCorners.size());
            System.out.println("STRATEGY 2 Finished.");
            thatGame.putChecker(new Position(availableCorners.get(selected),2));
            return ;
          }
          else if(thatGame.board.lastMyCell.position.y==2&&isCorner(thatGame.board.lastMyCell.position.x))
          {
              int [] ul = unrelatives(thatGame.board.lastMyCell.position.x);
              if(thatGame.board.cells[ul[0]][2].checker==null&&thatGame.board.cells[ul[1]][2].checker!=null) 
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(ul[0], 2));
                  return ;
              }
              if(thatGame.board.cells[ul[0]][2].checker!=null&&thatGame.board.cells[ul[1]][2].checker==null) 
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(ul[1], 2));
                  return ;
              }
              if(thatGame.board.cells[ul[0]][2].checker==null&&thatGame.board.cells[ul[1]][2].checker==null) 
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(ul[random.nextInt(2)], 2));
                  return ;
              }
              int diagonal = diagonal(thatGame.board.lastMyCell.position.x);
              if(thatGame.board.cells[diagonal][2].checker==null)
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(diagonal, 2));
                  return ;
              }
            int [] corners = {0,2,4,6};
            ArrayList<Integer> availableCorners = new ArrayList<>();
            for(int i=0;i<4;i++)
                if(thatGame.board.cells[corners[i]][0].checker==null) availableCorners.add(corners[i]);
            for(Object ac : availableCorners.toArray())
            {
                Integer that = (Integer) ac ;
                if(thatGame.board.cells[that][2].checker!=null)
                    if(thatGame.board.cells[that][2].checker.isMyChecker)
                    {
                       System.out.println("STRATEGY 2 Finished.");
                       thatGame.putChecker(new Position(that,0));
                       return ;
                    }
            }
          }
          else if(thatGame.board.lastMyCell.position.y==0&&isCorner(thatGame.board.lastMyCell.position.x))
          {
              int [] ul = unrelatives(thatGame.board.lastMyCell.position.x);
              if(thatGame.board.cells[ul[0]][0].checker==null&&thatGame.board.cells[ul[1]][0].checker!=null) 
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(ul[0], 0));
                  return ;
              }
              if(thatGame.board.cells[ul[0]][0].checker!=null&&thatGame.board.cells[ul[1]][0].checker==null) 
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(ul[1], 0));
                  return ;
              }
              if(thatGame.board.cells[ul[0]][0].checker==null&&thatGame.board.cells[ul[1]][0].checker==null) 
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(ul[random.nextInt(2)], 0));
                  return ;
              }
              int diagonal = diagonal(thatGame.board.lastMyCell.position.x);
              if(thatGame.board.cells[diagonal][0].checker==null)
              {
                  System.out.println("STRATEGY 2 Finished.");
                  thatGame.putChecker(new Position(diagonal, 0));
                  return ;
              }
          }
        }
        
        // STRATEGY 3
        {
        System.out.println("STRATEGY 3 Started.");
        for(int i=0;i<20;i++)
            for(int j=i+1;j<20;j++)
            {
                Line first = ll.lines[i];
                Line second = ll.lines[j];
                if(first.filledOnce(true)&&second.filledOnce(true))
                {
                    Position unionP = ll.linesEmptyUnion(first, second);
                    if(unionP!=null)
                    {
                       System.out.println("STRATEGY 3 Finished.");
                       thatGame.putChecker(unionP);
                       return ;
                    }
                }
            }
        }
        
        // STRATEGY 4
        {
        System.out.println("STRATEGY 4 Started.");
        Object [] oppCells = thatGame.board.oppCells.toArray();
        for(int i=0;i<oppCells.length;i++)
           for(int j=i+1;j<oppCells.length;j++)
           {
               Cell first = (Cell) oppCells[i];
               Cell second = (Cell) oppCells[j];
               if(first.position.x==second.position.x 
                       || (first.position.y==second.position.y 
                       && isLinearRelative(first.position.x, second.position.x)))
               {
                   Position tripleP = triple(first.position,second.position);
                   if(thatGame.board.cells[tripleP.x][tripleP.y].checker==null)
                   {
                   System.out.println("STRATEGY 4 Finished.");
                   thatGame.putChecker(tripleP);
                   return ;
                   }
               }
           }
        }
        
        // STRATEGY 5
        {
        System.out.println("STRATEGY 5 Started.");
        for(int i=0;i<20;i++)
            for(int j=i+1;j<20;j++)
            {
                Line first = ll.lines[i];
                Line second = ll.lines[j];
                if(first.filledOnce(false)&&second.filledOnce(false))
                {
                    Position unionP = ll.linesEmptyUnion(first, second);
                    if(unionP!=null)
                    {
                       System.out.println("STRATEGY 5 Finished.");
                       thatGame.putChecker(unionP);
                       return ;
                    }
                }
            }
        }
        
        // STRATEGY 6
        {
            System.out.println("STRATEGY 6 Started.");
            Object [] myCells = thatGame.board.myCells.toArray() ;
            
            for(int a=2;a>-1;a--)
            {
            Line line1 = null ;
            Line line2 = null ;
            for(Object tmpC : myCells)
            {
                Cell tmpCell = (Cell) tmpC ;
                if(tmpCell.position.y==a)
                {
                    Object [] lines = ll.findLinesByCell(tmpCell).toArray() ;
                    for(Object tmpL : lines)
                    {
                         Line tmpLine = (Line) tmpL ;
                         if(tmpLine.filledOnce(true))
                         {
                             if(line1==null) line1 = tmpLine ;
                             else
                             {
                                 if(!line1.hasConstantY()&&tmpLine.hasConstantY())
                                     line1 = tmpLine ;
                             }
                         }
                    }
                }
            }
            if(line1!=null)
            {
                Cell [] line1Cells = line1.cells ;
                ArrayList<Line> wholeLines = new ArrayList<>();
                for(Cell tmpC : line1Cells)
                {
                    Object [] tmpCellLines = ll.findLinesByCell(tmpC).toArray();
                    for(Object tmpL : tmpCellLines)
                    {
                        Line tmpLine = (Line) tmpL ;
                        wholeLines.add(tmpLine);
                    }
                }
                for(Object tmpL : wholeLines.toArray())
                {
                    Line tmpLine = (Line) tmpL ;
                    if(tmpLine.isVacant())
                    {
                        if(line2==null) line2 = tmpLine ;
                        else
                        {
                            if(!line2.hasConstantY()&&tmpLine.hasConstantY())
                                     line2 = tmpLine ;
                            else if(line2.isDiagonal&&!tmpLine.isDiagonal)
                                line2 = tmpLine ;
                        }
                    } 
                }
            }
            if(line2!=null)
            {
                ArrayList<Position> availablePositions = new ArrayList<>();
                Position unionP = ll.linesEmptyUnion(line1, line2);
                Cell [] line2Cells = line2.cells ;
                for(Cell tmpCell : line2Cells)
                {
                    if(!tmpCell.position.equals(unionP))
                        availablePositions.add(tmpCell.position);
                }
                int selected = random.nextInt(2);
                System.out.println("STRATEGY 6 Finished.");
                thatGame.putChecker(availablePositions.get(selected));
                return ;
            }
            }
        }
       
        // STRATEGY 7
        {
           System.out.println("STRATEGY 7 Started.");
           for(Line tmpL : ll.lines)
           {
               if(tmpL.filledOnce(true))
               {
                   ArrayList<Cell> vacant = tmpL.dualVacant(true);
                   int selected = random.nextInt(2);
                   System.out.println("STRATEGY 7 Finished.");
                   thatGame.putChecker(vacant.get(selected).position);
                   return ;
               }
           }
        }
    }
    
    public static boolean isCorner(int number)
    {
        switch(number)
        {
            case 0 :
                return true ;
            case 2 :
                return true ;
            case 4 :
                return true ;
            case 6 :
                return true ;
        }
        return false ;
    }
    
    public static int diagonal(int number)
    {
        switch(number)
        {
            case 0 :
                return 4 ;
            case 2 :
                return 6 ;
            case 4 :
                return 0 ;
            case 6 :
                return 2 ;
        }
        return -1 ;
    }
    
    public static boolean isDiagonal(int number1,int number2)
    {
        if((number1==0&&number2==4)||(number1==4&&number2==0)) return true ;
        if((number1==2&&number2==6)||(number1==6&&number2==2)) return true ;
        return false ;
    }
    
    public static int [] unrelatives(int number)
    {
        int [] unrelatives = new int[2];
        switch(number)
        {
            case 0 :
                unrelatives[0] = 2 ; unrelatives[1] = 6 ;
                break ;
            case 2 :
                unrelatives[0] = 0 ; unrelatives[1] = 4 ;
                break ;
            case 4 :
                unrelatives[0] = 2  ; unrelatives[1] = 6 ;
                break ;
            case 6 :
                unrelatives[0] = 0 ; unrelatives[1] = 4 ;
        }
        return unrelatives ;
    }
    
    public static boolean isLinearRelative(int number1 , int number2)
    {
        if(number1 > number2) 
        {
            int tmp = number1;
            number1 = number2 ;
            number2 = tmp ;
        }
        if(number1==0&&number2==1) return true ;
        if(number1==0&&number2==2) return true ;
        if(number1==0&&number2==6) return true ;
        if(number1==0&&number2==7) return true ;
        if(number1==1&&number2==2) return true ;
        if(number1==2&&number2==3) return true ;
        if(number1==2&&number2==4) return true ;
        if(number1==3&&number2==4) return true ;
        if(number1==4&&number2==5) return true ;
        if(number1==4&&number2==6) return true ;
        if(number1==5&&number2==6) return true ;
        if(number1==6&&number2==7) return true ;
        return false ;
    }
    
    public static Position triple(Position p1 , Position p2)
    {
        if(p1.x==p2.x)
        {
            int selectedY = 0 ;
            if((p1.y==0&&p2.y==1)||(p1.y==1&&p2.y==0)) selectedY = 2 ;
            if((p1.y==0&&p2.y==2)||(p1.y==2&&p2.y==0)) selectedY = 1 ;
            if((p1.y==1&&p2.y==2)||(p1.y==2&&p2.y==1)) selectedY = 0 ;
            return new Position(p1.x, selectedY);
        }
        if(p1.y==p2.y)
        {
            int selectedX = 0 ;
            
            int number1 = p1.x , number2 = p2.x ;
            if(number1 > number2) 
            {
               int tmp = number1;
               number1 = number2 ;
               number2 = tmp ;
            }
            if(number1==0&&number2==1) selectedX = 2 ;
            if(number1==0&&number2==2) selectedX = 1 ;
            if(number1==0&&number2==6) selectedX = 7 ;
            if(number1==0&&number2==7) selectedX = 6 ;
            if(number1==1&&number2==2) selectedX = 0 ;
            if(number1==2&&number2==3) selectedX = 4 ;
            if(number1==2&&number2==4) selectedX = 3 ;
            if(number1==3&&number2==4) selectedX = 2 ;
            if(number1==4&&number2==5) selectedX = 6 ;
            if(number1==4&&number2==6) selectedX = 5 ;
            if(number1==5&&number2==6) selectedX = 4 ;
            if(number1==6&&number2==7) selectedX = 0 ;
            return new Position(selectedX, p1.y);
        }
        return null ;
    }
}
