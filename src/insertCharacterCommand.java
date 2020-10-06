public class insertCharacterCommand extends command {
    //members..

    public InsertCharacterCommand()
    {
        //instantiate
    }

    @Override public void execute()
    {
        Moment moment = new Moment();

        //create Memento before executing
        //set new state
    }

    @Override public void unExecute()
    {
        this.tree = moment.getState();
    }
}