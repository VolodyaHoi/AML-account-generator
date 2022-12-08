import javax.swing.*;

public class Program {
    JMenu menu;
    JMenuItem a1,a2;
    Program()
    {
        JMenu menu;
        JMenuItem a1,a2;
        JFrame a = new JFrame("AML project | account constructor");
        
        JButton b = new JButton("click me"); // button example

        JTextField c = new JTextField("edureka"); // input text example

        menu = new JMenu("options"); // menu example
        JMenuBar m1 = new JMenuBar();
        a1 = new JMenuItem("example");
        a2 = new JMenuItem("example1");
        menu.add(a1);
        menu.add(a2);

        JLabel b1; // text example
        b1 = new JLabel("Cum");
        b1.setBounds(40,40,90,20);

        String[] courses = {"core java", "advance java", "java servlet"}; // list example
        JComboBox d = new JComboBox(courses);
        d.setBounds(70,50,90,30);
        a.add(d);

        a.add(b1);
        m1.add(menu);
        a.setJMenuBar(m1);
        c.setBounds(50,100,200,30);
        b.setBounds(0,0,85,20);
        a.add(b);
        a.add(c);
        a.setSize(700,500);
        a.setLayout(null);
        a.setVisible(true);
    }
    public static void main(String args[]) {
        new Program();
    }
}
