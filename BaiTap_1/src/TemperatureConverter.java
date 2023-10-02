
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JTextField fahrenheitField;
    private JTextField celsiusField;
    private JButton convertButton;

    public TemperatureConverter() {
        super("Temperature Converter");

        JLabel fahrenheitLabel = new JLabel("Fahrenheit:");
        fahrenheitField = new JTextField(10);

        JLabel celsiusLabel = new JLabel("Celsius:");
        celsiusField = new JTextField(10);
        celsiusField.setEditable(false);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(fahrenheitLabel);
        panel.add(fahrenheitField);
        panel.add(celsiusLabel);
        panel.add(celsiusField);
        panel.add(convertButton);
        setSize(500, 500);
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                double fahrenheit = Double.parseDouble(fahrenheitField.getText());
                double celsius = (fahrenheit - 32) * 5 / 9;
                celsiusField.setText(String.format("%.2f", celsius));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();
        converter.setVisible(true);
    }
}
