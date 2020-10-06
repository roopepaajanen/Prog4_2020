import javax.swing.*;

public class Moment {
    JTree tree;

    public JTree getState()
    {
        return tree;
    }

    public void setState(JTree myObject)
    {
        this.tree = myObject;
    }

}
