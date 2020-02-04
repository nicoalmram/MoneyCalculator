package moneycalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import control.Command;
import model.Currency;
import ui.MoneyDialog;
import ui.MoneyDisplay;
import ui.swing.SwingMoneyDialog;
import ui.swing.SwingMoneyDisplay;

public class MainFrame extends JFrame{
    private final Currency[] currencies;
    private final Map<String,Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    
    public MainFrame(Currency[] currencies) {
        this.currencies = currencies;
        setTitle("Money Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        
        add(moneyDialog(), BorderLayout.NORTH);
        add(moneyDisplay(), BorderLayout.CENTER);
        add(toolbar(), BorderLayout.SOUTH);
        
        setVisible(true);
    }

    public void add (Command command) {
        commands.put(command.name(), command);
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }
    
    private Component moneyDialog() {
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(currencies);
        this.moneyDialog = moneyDialog;
        return moneyDialog;
    }
    
    private Component moneyDisplay() {
        SwingMoneyDisplay moneyDisplay = new SwingMoneyDisplay();
        this.moneyDisplay = moneyDisplay;
        return moneyDisplay;
    }
    
    private Component toolbar() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(calculateButton(panel));
        return panel;
    }

    private JButton calculateButton(JPanel panel) {
        JButton button = new JButton("Exchange");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("calculate").execute();
            }
        };
    }
    
}
