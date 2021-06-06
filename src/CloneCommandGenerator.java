import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Serhat G. (sero583)
 */
public class CloneCommandGenerator {
    public static void main(String[] main) {
        System.out.println("Welcome to CloneCommandGenerator. This utility program was written by sero583.");

        Scanner s = new Scanner(System.in);

        Boolean writeToFile = null;

        while(writeToFile==null) {
            System.out.println("Should the result be written to a file? Choosing no makes the result get output in the logger [writing to logger in bulk is usually much slower than writing to file]. (y/n)");

            String input = s.next().toLowerCase();

            switch(input) {
                case "yes":
                case "y":
                case "true":
                case "t":
                    writeToFile = true;

                    System.out.println("The output will be written to a file.");
                break;
                case "no":
                case "n":
                case "false":
                case "f":
                    writeToFile = false;

                    System.out.println("The output will come in the logger.");
                break;
            }
        }

        Boolean slashPrefix = null;

        while(slashPrefix==null) {
            System.out.println("Do you want the command to begin with a slash? (y/n)");
            String input = s.next().toLowerCase();

            switch(input) {
                case "yes":
                case "y":
                case "true":
                case "t":
                    slashPrefix = true;

                    System.out.println("The generated commands will begin with a slash.");
                break;
                case "no":
                case "n":
                case "false":
                case "f":
                    slashPrefix = false;

                    System.out.println("The generated commands won't begin with a slash");
                break;
            }
        }

        Long swapChache = null;

        System.out.println("Enter X of copied block.");
        long copy_x = getUserInputNumber(s);

        System.out.println("Enter Y of copied block.");
        long copy_y = getUserInputNumber(s);

        System.out.println("Enter Z of copied block.");
        long copy_z = getUserInputNumber(s);

        System.out.println("Enter start X of destination.");
        long start_dest_x = getUserInputNumber(s);

        System.out.println("Enter end X of destination.");
        long end_dest_x = getUserInputNumber(s);

        if(start_dest_x>end_dest_x) {
            // swap otherwise for loop wont work

            swapChache = start_dest_x;

            start_dest_x = end_dest_x;
            end_dest_x = swapChache;

            swapChache = null;
        }

        System.out.println("Enter start Y of destination.");
        long start_dest_y = getUserInputNumber(s);

        System.out.println("Enter end Y of destination.");
        long end_dest_y = getUserInputNumber(s);

        if(start_dest_y>end_dest_y) {
            swapChache = start_dest_y;

            start_dest_y = end_dest_y;
            end_dest_y = swapChache;

            swapChache = null;
        }

        System.out.println("Enter start Z of destination.");
        long start_dest_z = getUserInputNumber(s);

        System.out.println("Enter end Z of destination.");
        long end_dest_z = getUserInputNumber(s);

        if(start_dest_z>end_dest_z) {
            swapChache = start_dest_z;

            start_dest_z = end_dest_z;
            end_dest_z = swapChache;

            swapChache = null;
        }

        long startTime = System.currentTimeMillis();

        System.out.println("Generating each commands...");

        if(writeToFile==true) {
            File file = new File("result.txt");

            StringBuilder builder = new StringBuilder();

            for(long destX = start_dest_x; destX <= end_dest_x; destX++) {
                for(long destY = start_dest_y; destY <= end_dest_y; destY++) {
                    for(long destZ = start_dest_z; destZ <= end_dest_z; destZ++) {
                        builder.append(slashPrefix == true ? "/clone " : "clone ");

                        builder.append(copy_x);
                        builder.append(" ");
                        builder.append(copy_y);
                        builder.append(" ");
                        builder.append(copy_z);
                        builder.append(" ");

                        // repeat due to command syntax
                        builder.append(copy_x);
                        builder.append(" ");
                        builder.append(copy_y);
                        builder.append(" ");
                        builder.append(copy_z);
                        builder.append(" ");

                        builder.append(destX);
                        builder.append(" ");
                        builder.append(destY);
                        builder.append(" ");
                        builder.append(destZ);

                        builder.append("\n");
                    }
                }
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(builder.toString());
                writer.close();

                long diff = System.currentTimeMillis() - startTime;
                System.out.println("Generated all commands! For the results open the results file at " + file.getPath() + ". (execution duration: " + diff + " ms)");
            } catch(Exception e) {
                System.err.println("Coudln't write result to file (at path " + file.getAbsolutePath() + ").");
            }
        } else for(long destX = start_dest_x; destX <= end_dest_x; destX++) {
            for(long destY = start_dest_y; destY <= end_dest_y; destY++) {
                for(long destZ = start_dest_z; destZ <= end_dest_z; destZ++) {
                    StringBuilder builder = (slashPrefix == true) ? new StringBuilder("/clone ") : new StringBuilder("clone ");
                    builder.append(copy_x);
                    builder.append(" ");
                    builder.append(copy_y);
                    builder.append(" ");
                    builder.append(copy_z);
                    builder.append(" ");

                    // repeat due to command syntax
                    builder.append(copy_x);
                    builder.append(" ");
                    builder.append(copy_y);
                    builder.append(" ");
                    builder.append(copy_z);
                    builder.append(" ");

                    builder.append(destX);
                    builder.append(" ");
                    builder.append(destY);
                    builder.append(" ");
                    builder.append(destZ);

                    System.out.println(builder.toString());
                }
            }

            long diff = System.currentTimeMillis() - startTime;
            System.out.println("Generated all commands! (execution duration: " + diff + " ms)");
        }
    }

    /**
     * Asks users for long eger number and repeats until a valid one has been received
     * @param scanner
     * @return long (input result)
     */
    public static long getUserInputNumber(Scanner scanner) {
        try {
            return scanner.nextLong();
        } catch(Exception e) {
            return scanner.nextLong();
        }
    }
}