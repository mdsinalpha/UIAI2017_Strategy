package pkg12.men.s.morris;
/**
 *
 * @author Mohsen DB
 */
public class Main 
{
    public static void main(String[] args) 
    {
        Game game = new Game("127.0.0.1",9999,"Lightning");
        try
        {
            game.startClient();
            game.startGame();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
