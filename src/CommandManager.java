import javax.swing.plaf.nimbus.State;
import java.util.Stack;

/**Class that manages the Commands and Containers given that changes the undo and redo stacks respectively.
 *
 */

public class CommandManager {
    private Command lastCommand;
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();
    private Stack<Container> undoContainerStack = new Stack<>();
    private Stack<Container> redoContainerStack = new Stack<>();
    private Container lastContainer;
    //private MusicOrganizerWindow view2;

    public CommandManager(/*MusicOrganizerWindow view*/) {
        //view2 = view;
    }

    public Stack<Command> getUndoStack(){
        return undoStack;
    }

    public Stack<Command> getRedoStack(){
        return redoStack;
    }

    public void executeCommand(Command com) {
        lastContainer = com.execute();
        undoStack.push(com); //add....
        lastCommand = com;
        undoContainerStack.push(lastContainer);
    }

    public void undo() {
        lastCommand.undo(lastContainer);
        redoStack.push(lastCommand);
        redoContainerStack.push(lastContainer);
        lastCommand = undoStack.pop();
        lastContainer = undoContainerStack.pop();
    }

    public void redo(){
        undoStack.push(lastCommand);
        undoContainerStack.push(lastContainer);
        lastCommand = redoStack.pop();
        lastContainer = redoContainerStack.pop();
        lastCommand.redo(lastContainer);
    }

}
