import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    static JTextField t, t2;
    static JFrame f;
    static JButton b;
    static JLabel l, l2, l3;

    Main()
    {
    }

    public static void main(String[] args) {
        f = new JFrame("Day counter");

        l = new JLabel();

        l2 = new JLabel("Date format: DD.MM.YYYY");
        l3 = new JLabel("                    ");

        b = new JButton("Count");

        Main main = new Main();

        b.addActionListener(main);

        t = new JTextField("Enter start date", 16);
        t2 = new JTextField("Enter end date", 16);

        JPanel p = new JPanel();

        p.add(t);
        p.add(t2);
        p.add(b);
        p.add(l);
        p.add(l3);
        p.add(l2);

        f.add(p);

        f.setSize(500, 500);

        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dates dates = new Dates();

        dates.getBeginDate(t.getText());
        dates.getEndDate(t2.getText());

        String days = String.valueOf(dates.countDays());

        String s = e.getActionCommand();
        if (s.equals("Count")) {
            l.setText(days);
        }
    }
}