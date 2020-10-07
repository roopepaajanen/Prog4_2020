import javax.swing.*;

public class insertCharacterCommand extends command {
    //members..

    /*public insertCharacterCommand() {
        //instantiate
    }*/

    @Override public void execute() {
        Moment moment = new Moment();

        moment.setState(tree);

        //create Memento before executing
        //set new state
    }

    @Override public void unExecute()
    {
        this.tree = moment.getState();
    }
}