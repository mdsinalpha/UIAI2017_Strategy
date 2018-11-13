package pkg12.men.s.morris;

public class MoveStrategy 
{
    public static void execute(Game thatGame)
    {
        System.out.println("This is a sample move output!");
        LineList ll = new LineList(thatGame.board.cells);
        
        // STRATEGY 1
         {
           System.out.println("STRATEGY 1 Started.");
           for(Line tmpL : ll.lines)
           {
               Cell vacant = tmpL.getVacantWhenFilledTwice(true);
               if(vacant!=null)
               {
                   Relateds rt = new Relateds();
                   Object [] relatedPositions = rt.relateds(vacant.position).toArray();
                   for(Object relatedPos : relatedPositions)
                   {
                        Position that = (Position) relatedPos ;
                        if(!tmpL.contains(that)&&thatGame.board.cells[that.x][that.y].checker!=null)
                        {
                            if(thatGame.board.cells[that.x][that.y].checker.isMyChecker
                                    &&ll.doozed(that, true))
                            {
                            System.out.println("STRATEGY 1 Finished.");
                            thatGame.moveChecker(that, vacant.position);
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
           for(Line tmpL : ll.lines)
           {
               Cell vacant = tmpL.getVacantWhenFilledTwice(true);
               if(vacant!=null)
               {
                   Relateds rt = new Relateds();
                   Object [] relatedPositions = rt.relateds(vacant.position).toArray();
                   for(Object relatedPos : relatedPositions)
                   {
                        Position that = (Position) relatedPos ;
                        if(!tmpL.contains(that)&&thatGame.board.cells[that.x][that.y].checker!=null)
                        {
                            if(thatGame.board.cells[that.x][that.y].checker.isMyChecker)
                            {
                            System.out.println("STRATEGY 2 Finished.");
                            thatGame.moveChecker(that, vacant.position);
                            return ;
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
               Cell vacant = tmpL.getVacantWhenFilledTwice(false);
               if(vacant!=null)
               {
                   Relateds rt = new Relateds();
                   Object [] relatedPositions = rt.relateds(vacant.position).toArray();
                   Position oppPos = null ;
                   Position myPos = null ;
                   for(Object relatedPos : relatedPositions)
                   {
                        Position that = (Position) relatedPos ;
                        if(!tmpL.contains(that)&&thatGame.board.cells[that.x][that.y].checker!=null)
                        {
                            if(thatGame.board.cells[that.x][that.y].checker.isMyChecker)
                               myPos = that ;
                            else oppPos = that ;
                        }
                   }
                   if(oppPos!=null&&myPos!=null)
                   {
                       if(!ll.doozed(myPos, true))
                       {
                        System.out.println("STRATEGY 3 Finished.");
                        thatGame.moveChecker(myPos, vacant.position);
                        return ;
                       }
                       boolean canMove = true ;
                       Object [] myPosRelateds = rt.relateds(myPos).toArray();
                       for(Object myRelatedPos : myPosRelateds)
                       {
                           Position that = (Position) myRelatedPos ;
                           if(thatGame.board.cells[that.x][that.y].checker!=null)
                                if(!thatGame.board.cells[that.x][that.y].checker.isMyChecker)
                                {
                                    canMove = false ;
                                    break ;
                                }
                       }
                       if(canMove)
                       {
                        System.out.println("STRATEGY 3 Finished.");
                        thatGame.moveChecker(myPos, vacant.position);
                        return ;
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
               if(tmpL.dooz(true))
               {
                   for(Cell tmpC : tmpL.cells)
                   {
                       Relateds rt = new Relateds();
                       Object [] cellRelateds = rt.relateds(tmpC.position).toArray();
                       boolean oppExists = false ;
                       Position vacantPos = null ;
                       for(Object cellRelated : cellRelateds)
                       {
                           Position that = (Position) cellRelated ;
                           if(thatGame.board.cells[that.x][that.y].checker==null)
                               vacantPos = that;
                           else if(!thatGame.board.cells[that.x][that.y].checker.isMyChecker)
                               oppExists = true ;
                       }
                       if(!oppExists&&vacantPos!=null)
                       {
                        System.out.println("STRATEGY 4 Finished.");
                        thatGame.moveChecker(tmpC.position,vacantPos);
                        return ;
                       }
                   }
               }
           }
        }
    }
}
