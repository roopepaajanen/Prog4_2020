import javax.swing.*;

public class UserInputGUI extends UserInput { //enables a pop-up game, does not play inside the console
    public String getUserInput(String prompt){
        return JOptionPane.showInputDialog(prompt);

    }
    public void showMessage(String out){
        JOptionPane.showMessageDialog(null,out);
    }
}
