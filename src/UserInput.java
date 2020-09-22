import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput { //only plays inside the console
    public String getUserInput(String prompt){ //function to get user input
        String inputLine = null;
        System.out.println(prompt + " ");
        try{
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if(inputLine.length()==0) return null;
        } catch(IOException e){
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }

    public void showMessage(String out){
        System.out.println(out);
    }
}
