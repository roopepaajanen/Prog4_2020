import java.util.Stack;

public class CommandManager {

    private Stack<Command> undos = new Stack<Command>();
    private Stack<Command> redos = new Stack<Command>();
    private Command lastCommand;


    public void executeCommand(Command c){
        c.execute();
        undos.push(c);
        redos.clear();
    }
    public CommandManager(){

    }


    public boolean isUndoAvailable(){
        return  !undos.empty();
    }

    public void undo(){
        assert(lastCommand != null);
         lastCommand.undo();
         lastCommand = null;

    }

    public boolean isRedoAvailable(){
        return  !redos.empty();
    }

    public void redo(){
        assert (!redos.empty());
         Command command = redos.pop();
         command.execute();
         undos.push(command);
    }
}
