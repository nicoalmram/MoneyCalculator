package moneycalculator;

import control.CalculateCommand;
import persistence.CurrencyListLoader;
import persistence.files.FileCurrencyListLoader;
import persistence.rest.RestExchangeRateLoader;

public class Main {

    public static void main(String[] args) {
        CurrencyListLoader currencyLoader = new FileCurrencyListLoader("currencies");
        RestExchangeRateLoader exchangeRateLoader = new RestExchangeRateLoader();
        MainFrame mainFrame = new MainFrame(currencyLoader.currencies());
        mainFrame.add(new CalculateCommand(mainFrame.getMoneyDisplay(), mainFrame.getMoneyDialog(),exchangeRateLoader));
    }

}
