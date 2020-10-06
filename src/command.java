import javax.swing.*;

public abstract class command {
    JTree tree;
    Moment moment;

    public abstract void execute();

    public abstract void unExecute();
}