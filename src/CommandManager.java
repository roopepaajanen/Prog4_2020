import javax.swing.plaf.nimbus.State;
import java.util.Stack;

/**
 * Class that manages the Commands and Containers given that changes the undo and redo stacks respectively.
 */
public class CommandManager {
    private Command lastCommand;
    private Stack< Command> undoStack = new Stack<>();
    private Stack<Command>redoStack = new Stack<>();
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

    /** Performs the basic function of the chosen command and pushes it to the undoStack for when undo will be used*/
    public void executeCommand(Command com) {
        lastContainer = com.execute();
        undoStack.push(com); //add....
        lastCommand = com;
        undoContainerStack.push(lastContainer);
    }

    /** Perform the undo function where it pushes and pops so that the user can both undo and redo to the
     * same commands*/
    public void undo() {
        lastCommand.undo(lastContainer);
        redoStack.push(lastCommand);
        redoContainerStack.push(lastContainer);
        lastCommand = undoStack.pop();
        lastContainer = undoContainerStack.pop();
    }

    /** Perform the redo function where it pushes and pops so that the user can both undo and redo to the
     * same commands*/
    public void redo(){
        undoStack.push(lastCommand);
        undoContainerStack.push(lastContainer);
        lastCommand = redoStack.pop();
        lastContainer = redoContainerStack.pop();
        lastCommand.redo(lastContainer);
    }
}
