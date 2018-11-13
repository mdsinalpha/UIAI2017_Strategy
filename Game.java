package pkg12.men.s.morris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Game 
{
    public final Board board ;
    public final String serverAddress ;
    public final int serverPort ;
    public final String teamName ; 
    
    public Socket gameClient ;
    public BufferedWriter dos ;
    public BufferedReader dis ;
    
    public String flagMessage ;
    public boolean flagDooz ;
    public boolean moveStarted ;
    
    public Scanner input ;

    public Game(String serverAddress , int serverPort , String teamName)
    {
        board = new Board();
        this.serverAddress = serverAddress ;
        this.serverPort = serverPort ;
        this.teamName = teamName ;
        gameClient = null ;
        dos = null ;
        dis = null ;
        flagMessage = "";
        flagDooz = false ;
        moveStarted = false ;
        input = new Scanner(System.in);
    }
    
    public void startClient() throws Exception
    {
        gameClient = new Socket(serverAddress,serverPort);
        dos = new BufferedWriter(new OutputStreamWriter(gameClient.getOutputStream()));
        dis = new BufferedReader(new InputStreamReader(gameClient.getInputStream()));   
    }
    
    public void startGame() 
    {    
        try
        {
          dos.write(("REGISTER "+teamName+"\n"));
          dos.flush();
          String responseLine ;
          while((responseLine = dis.readLine())!=null)
          {
            flagMessage = "";
            playARound(responseLine);
            flagMessage += "\n";
            dos.write(flagMessage);
            dos.flush();
          }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
            dos.close();
            dis.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }  
    }
    
    public void playARound(String responseLine) 
    {
        flagDooz = false ;
        String tmp1 = responseLine.substring(0,47);
        for(int i=0;i<8;i++)
            for(int j=0;j<3;j++)
            {
                board.cells[i][j].checker = null ;
                if(tmp1.charAt(0)!='e')
                    board.cells[i][j].checker
                            = new Checker(tmp1.charAt(0)=='m');
                if(tmp1.length()>=2)
                    tmp1 = tmp1.substring(2);
            }
        board.update();
        String [] tmp2 = 
                responseLine.substring(48).split(",");
        int inHandChecker = Integer.parseInt(tmp2[0]);
        if(inHandChecker>0) 
            PutStrategy.execute(this);
        else 
        {
            if(!moveStarted) moveStarted = true ;
            MoveStrategy.execute(this);
        }
        if(flagDooz) 
            PopStrategy.execute(this);
    }
    public void refreshDoozFlag(Position thatPosition)
    {
        boolean flagStatus = true;
        for (int j = 0; j < 3; j++)
            if (board.cells[thatPosition.x][j].checker == null || 
                    !board.cells[thatPosition.x][j].checker.isMyChecker) 
            {
                flagStatus = false;
                break;
            }
        if(flagStatus) 
        {
            flagDooz = true;
            return;
        }
        flagStatus = true;
        if (thatPosition.x % 2 == 0) 
        {
            for (int i = 0; i < 3; i++)
                if (board.cells[(thatPosition.x+ i) % 8][thatPosition.y].checker == null || 
                        !board.cells[(thatPosition.x+ i) % 8][thatPosition.y].checker.isMyChecker)
                    flagStatus = false;
            if(flagStatus)
            {
                flagDooz = true ;
                return ;
            }
            flagStatus = true ;
            for (int i = 0; i < 3; i++)
                if (board.cells[(thatPosition.x- i+8) % 8][thatPosition.y].checker == null || 
                        !board.cells[(thatPosition.x - i + 8) % 8][thatPosition.y].checker.isMyChecker)
                    flagStatus = false;
        } 
        else 
        {
            for (int i = -1; i < 2; i++)
                if (board.cells[(thatPosition.x + i) % 8][thatPosition.y].checker == null || 
                        !board.cells[(thatPosition.x + i) % 8][thatPosition.y].checker.isMyChecker)
                    flagStatus = false;
        }
        if (flagStatus) 
        {
            flagDooz = true;
        }
    }
    public void putChecker(Position thatPosition)
    {
        System.out.println(thatPosition.x + " : "+thatPosition.y);
        flagMessage = "put "+thatPosition.x+","+thatPosition.y;
        board.cells[thatPosition.x][thatPosition.y].checker = new Checker(true);
        board.update();
        refreshDoozFlag(thatPosition);
    }
    public void moveChecker(Position thatPosition , Position newPosition)
    {
        System.out.println(thatPosition.x + " : "+thatPosition.y+" --> "+newPosition.x+" : "+newPosition.y);
        int x = thatPosition.x , y = thatPosition.y ;
        flagMessage = "mov "+x+","+y+","+newPosition.x+","+newPosition.y;
        board.cells[newPosition.x][newPosition.y].checker = board.cells[x][y].checker;
        board.cells[x][y].checker = null ;
        board.update();
        refreshDoozFlag(newPosition);
    }
    public void popChecker(Position thatPosition)
    {
        System.out.println(thatPosition.x + " : "+thatPosition.y);
        flagMessage += " "+thatPosition.x+","+thatPosition.y;
        board.cells[thatPosition.x][thatPosition.y].checker = null ;
    }
}
