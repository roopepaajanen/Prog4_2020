
public interface Command {
    Container execute();
    void undo(Container container);
    void redo(Container container);
}