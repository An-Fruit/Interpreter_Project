import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PEELLang {
    /**
     * Starts executing from given path to file
     * @param path the path to a given file containing PEEL code
     * @throws IOException if errors reading in the file are encountered
     */
    private static void runFile(String fpath) throws IOException {
        //read in the entire file into a single byte array
        byte[] bytes = Files.readAllBytes(Paths.get(fpath));
        //execute the program
        run(new String(bytes, Charset.defaultCharset()));
    }

    private static void runPrompt(){
        //read commands in from the terminal and execute
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean killShell = false;
        while(!killShell){
            System.out.print("PEEL $- ");
            String line = reader.readLine();
            killShell = line == null;
            run(line);
        }
    }
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: PEEL [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }
}