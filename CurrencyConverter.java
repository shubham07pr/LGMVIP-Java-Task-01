import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JLabel lblTitle, lblAmount, lblFrom, lblTo, lblResult;
    private JTextField txtAmount;
    private JComboBox<String> cbFrom, cbTo;
    private JButton btnConvert;

    private double[][] exchangeRates = {
            {1.0, 0.013, 0.00018, 0.0091, 0.75, 0.013, 0.017, 78.92},    // USD to Other Currencies
            {75.95, 1.0, 0.013, 0.63, 52.73, 0.89, 1.19, 5566.75},     // INR to Other Currencies
            // Add more exchange rates here for other currencies
    };

    private String[] currencyNames = {"USD", "INR", "PKR", "JPY", "IDR", "CNY", "CAD", "RUB"};

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        lblTitle = new JLabel("Currency Converter");
        lblAmount = new JLabel("Amount:");
        lblFrom = new JLabel("From:");
        lblTo = new JLabel("To:");
        lblResult = new JLabel();

        txtAmount = new JTextField(10);

        cbFrom = new JComboBox<>(currencyNames);
        cbTo = new JComboBox<>(currencyNames);

        btnConvert = new JButton("Convert");
        btnConvert.addActionListener(this);

        setLayout(new GridLayout(6, 2, 10, 10));

        add(lblTitle);
        add(new JLabel()); // Empty label for spacing

        add(lblAmount);
        add(txtAmount);

        add(lblFrom);
        add(cbFrom);

        add(lblTo);
        add(cbTo);

        add(new JLabel()); // Empty label for spacing
        add(btnConvert);

        add(lblResult);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConvert) {
            try {
                double amount = Double.parseDouble(txtAmount.getText());
                int fromIndex = cbFrom.getSelectedIndex();
                int toIndex = cbTo.getSelectedIndex();

                double convertedAmount = amount * exchangeRates[fromIndex][toIndex];
                DecimalFormat df = new DecimalFormat("#.##");
                lblResult.setText("Converted Amount: " + df.format(convertedAmount) + " " + currencyNames[toIndex]);
            } catch (NumberFormatException ex) {
                lblResult.setText("Please enter a valid amount.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}
