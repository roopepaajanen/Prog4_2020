
public interface Command {  /**Interface... nothing more, nothing less :) */
    Container execute();
    void undo(Container container);
    void redo(Container container);
}