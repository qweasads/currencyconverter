import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class obmen extends JFrame implements ActionListener {
    // Components
    private JTextField amountField;
    private JComboBox<String> initialCurrencyBox, targetCurrencyBox;
    private JLabel resultLabel;

    // Rates between USD, EUR and RUB
    private double rateToUSD = 1;
    private double rateToEUR = 0.84;
    private double rateToRUB = 81.45;

    public obmen() {
        // Set window title
        setTitle("Currency Exchange");

        // Set window size
        setSize(400, 200);

        // Create a panel with a flow layout
        JPanel panel = new JPanel(new FlowLayout());

        // Add components to the panel
        panel.add(new JLabel("Amount:"));
        amountField = new JTextField(10);
        panel.add(amountField);

        panel.add(new JLabel("Initial Currency:"));
        initialCurrencyBox = new JComboBox<String>(new String[] { "USD", "EUR", "RUB" });
        panel.add(initialCurrencyBox);

        panel.add(new JLabel("Target Currency:"));
        targetCurrencyBox = new JComboBox<String>(new String[] { "USD", "EUR", "RUB" });
        panel.add(targetCurrencyBox);

        JButton exchangeButton = new JButton("Exchange");
        exchangeButton.addActionListener(this);
        panel.add(exchangeButton);

        resultLabel = new JLabel("");
        panel.add(resultLabel);

        // Add panel to the window
        add(panel);

        // Center window on screen
        setLocationRelativeTo(null);

        // Set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = Double.parseDouble(amountField.getText());
        String initialCurrency = initialCurrencyBox.getSelectedItem().toString();
        String targetCurrency = targetCurrencyBox.getSelectedItem().toString();

        double initialAmount = 0;
        double targetAmount = 0;

        switch (initialCurrency.toUpperCase()) {
            case "USD":
                initialAmount = amount;
                break;
            case "EUR":
                initialAmount = amount / rateToEUR;
                break;
            case "RUB":
                initialAmount = amount / rateToRUB;
                break;
            default:
                resultLabel.setText("Invalid initial currency");
                return;
        }

        switch (targetCurrency.toUpperCase()) {
            case "USD":
                targetAmount = initialAmount;
                break;
            case "EUR":
                targetAmount = initialAmount * rateToEUR;
                break;
            case "RUB":
                targetAmount = initialAmount * rateToRUB;
                break;
            default:
                resultLabel.setText("Invalid target currency");
                return;
        }

        resultLabel.setText(amount + " " + initialCurrency + " is equivalent to " + targetAmount + " " + targetCurrency);
    }

    public static void main(String[] args) {
        // Create and show the GUI on the event dispatch thread
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                obmen gui = new obmen();
                gui.setVisible(true);
            }
        });
    }
}
