import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    static JTextField t, t2;
    static JFrame f;
    static JButton b;
    static JLabel l;

    Main()
    {
    }

    public static void main(String[] args) {
        f = new JFrame("Day counter");

        l = new JLabel();

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

        f.add(p);

        f.setSize(500, 500);

        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dates dates = new Dates();

        try{

            dates.getBeginDate(t.getText());
            dates.getEndDate(t2.getText());

            String days = String.valueOf(dates.countDays());
            String s = e.getActionCommand();
            if (s.equals("Count")) {
                l.setText(days);
            }

        } catch (Exception ex){
            l.setText("Nem érvényes dátum. Formátum: DD.MM.YYYY");
        }
    }
}