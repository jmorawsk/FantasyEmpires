import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class TheGame
{
    public static void main(String[] args)
    {
        String name = "Races.txt";

        //System.out.print(Generator.RandomCiv().getName());
        World world = Generator.WorldGen();
        world.CivList();
        GameSim mySim = new GameSim(world);
        try{
            System.out.println("Enter the number of the team to play.");
            
            int num = Reader.pickOption(world.getCivs().size());
            world.setPlayer(num);
          //reading one line at a time from the console
            BufferedReader input  = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please press enter ' ' to go one step, or 'q' to quit.");
            String line = input.readLine();

            //"\q" tells the simulator to quit
            while (line.compareTo("q") != 0){
                if (line.length() == 0) {
                    System.out.println("empty entry not allowed; options are 's', 'm <number>', 'l' or 'q'");
                }

                else {
                    //the first character is the command (s, m, l)
                    char cmd = line.charAt(0);
                    switch (cmd){
                        //one step only
                        case ' ': {
                            mySim.simulateOneStep();
                            break;
                        }
                        //long simulation
//                        case 'l': {
//                            mySim.runLongSimulation();
//                            break;                                          
//                        }
                        //multiple steps specified by the user
                        case 'm': {
                            if (line.length() == 1)
                            {
                                System.out.println("invalid command:  e.g., m 20");
                                break;
                            }
                            try
                            {
                                int steps = Integer.parseInt(line.substring(1).trim());
                                mySim.simulate(steps);
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("invalid command:  e.g., m 20");
                            }
                            break;
                        }
                        default:{
                            System.out.println("invalid command; options are 's', 'm <number>', 'l' or 'q'");
                        }
                    } // end of switch
                } // end of else

                line = input.readLine();
            }
        }
        // thrown by read if some I/O error
        catch (IOException e) {
            System.err.println("Simulation, error: problem reading from console.");
            System.exit(-1);
        }
    }
}
