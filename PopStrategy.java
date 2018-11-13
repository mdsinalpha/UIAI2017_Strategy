package pkg12.men.s.morris;

import java.util.ArrayList;
import java.util.Random;

public class PopStrategy 
{
    public static void execute(Game thatGame)
    {
        System.out.println("This is a sample pop output!");    
        Random random = new Random();
        
        // STRATEGY 1
        if(!thatGame.moveStarted)
        {
           System.out.println("STRATEGY 1 Started.");
           System.out.println("STRATEGY 1 Finished.");
           thatGame.popChecker(thatGame.board.lastOppCell.position);
           return ;
        }
        
        LineList ll = new LineList(thatGame.board.cells);
        
        // STRATEGY 2
        {
           System.out.println("STRATEGY 2 Started.");
           for(Line tmpL : ll.lines)
           {
               Cell vacant = tmpL.getVacantWhenFilledTwice(false);
               if(vacant!=null)
               {
                   Relateds rt = new Relateds();
                   Object [] relatedPositions = rt.relateds(vacant.position).toArray();
                   for(Object relatedPos : relatedPositions)
                   {
                        Position that = (Position) relatedPos ;
                        if(!tmpL.contains(that)&&thatGame.board.cells[that.x][that.y].checker!=null)
                        {
                            if(!thatGame.board.cells[that.x][that.y].checker.isMyChecker)
                            {
                              ArrayList<Cell> availableForPop = new ArrayList<>();
                              for(Cell tmpCell : tmpL.cells)
                                  if(!tmpCell.equals(vacant)) availableForPop.add(tmpCell);
                              
                              if(ll.doozed(availableForPop.get(0).position, false)
                                      &&!ll.doozed(availableForPop.get(1).position, false))
                              {
                                System.out.println("STRATEGY 2 Finished.");
                                thatGame.popChecker(availableForPop.get(1).position);
                                return ;
                              }
                              if(!ll.doozed(availableForPop.get(0).position, false)
                                      &&ll.doozed(availableForPop.get(1).position, false))
                              {
                                System.out.println("STRATEGY 2 Finished.");
                                thatGame.popChecker(availableForPop.get(0).position);
                                return ;
                              }
                              if(!ll.doozed(availableForPop.get(0).position, false)
                                      &&!ll.doozed(availableForPop.get(1).position, false))
                              {
                                Cell first = availableForPop.get(0);
                                Cell second = availableForPop.get(1);
                         
                                for(Object tmpL2 : ll.findLinesByCell(first).toArray())
                                    if(!tmpL.equals(tmpL2)) 
                                    {
                                        if(((Line)tmpL2).getVacantWhenFilledTwice(false)!=null)
                                        {
                                            System.out.println("STRATEGY 2 Finished.");
                                            thatGame.popChecker(first.position);
                                            return ;
                                        }
                                    }
                                
                                for(Object tmpL2 : ll.findLinesByCell(second).toArray())
                                    if(!tmpL.equals(tmpL2)) 
                                    {
                                        if(((Line)tmpL2).getVacantWhenFilledTwice(false)!=null)
                                        {
                                            System.out.println("STRATEGY 2 Finished.");
                                            thatGame.popChecker(second.position);
                                            return ;
                                        }
                                    }
                                
                                System.out.println("STRATEGY 2 Finished.");
                                thatGame.popChecker(availableForPop.get(random.nextInt(2)).position);
                                return ;
                              }
                              if(ll.doozed(availableForPop.get(0).position, false)
                                      &&ll.doozed(availableForPop.get(1).position, false))
                              {
                                if(!ll.doozed(that, false))
                                {
                                System.out.println("STRATEGY 2 Finished.");
                                thatGame.popChecker(that);
                                return ;
                                }
                              }
                            }
                        }
                   }
                   
               }
           }
        }
        
        // STRATEGY 3
        {
           System.out.println("STRATEGY 3 Started.");
           for(Line tmpL : ll.lines)
           {
               Cell opp = tmpL.getOppWhenFilledTwice();
               if(opp!=null)
               {
                if(!ll.doozed(opp.position, false))
                {
                    Relateds rt = new Relateds();
                    Object [] oppRelateds = rt.relateds(opp.position).toArray();
                    for(Object oppRelated : oppRelateds)
                    {
                        Position that = (Position) oppRelated ;
                        if(!tmpL.contains(that)&&thatGame.board.cells[that.x][that.y].checker!=null)
                        {
                            if(thatGame.board.cells[that.x][that.y].checker.isMyChecker)
                            {
                                System.out.println("STRATEGY 3 Finished.");
                                thatGame.popChecker(opp.position);
                                return ;
                            }
                        }
                    }
                }
               }
           }
        }
        
        // STRATEGY 4
        {
            System.out.println("STRATEGY 4 Started.");
            for(Line tmpL : ll.lines)
            {
                Cell vacant = tmpL.getVacantWhenFilledTwice(true);
                if(vacant!=null)
                {
                    Relateds rt = new Relateds();
                    Object [] thatRelateds = rt.relateds(vacant.position).toArray();
                    Position mine = null ;
                    Position opp = null ;
                    for(Object thatR : thatRelateds)
                    {
                        Position that = (Position) thatR ;
                        if(!tmpL.contains(that)&&thatGame.board.cells[that.x][that.y].checker!=null)
                        {
                            if(thatGame.board.cells[that.x][that.y].checker.isMyChecker)
                                mine = that ;
                            else opp = that ;
                        }
                    }
                    if(mine!=null&&opp!=null)
                    {
                        System.out.println("STRATEGY 4 Finished.");
                        thatGame.popChecker(opp);
                        return ;
                    }
                }
                
            }
        }
        
        // STRATEGY 5
        {
        System.out.println("STRATEGY 5 Started.");
        System.out.println("STRATEGY 5 Finished.");
        thatGame.popChecker(thatGame.board.lastOppCell.position);
        }
    }
}
