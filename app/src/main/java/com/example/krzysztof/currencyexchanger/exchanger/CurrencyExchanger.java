package com.example.krzysztof.currencyexchanger.exchanger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

public class CurrencyExchanger extends AsyncTask<String, Void, String> {

    private Context context;
    private TextView resultView;
    private EditText editAmount;

    public CurrencyExchanger(Context context, TextView resultView, EditText editAmount) {
        this.resultView = resultView;
        this.context = context;
        this.editAmount = editAmount;
    }

    @Override
    protected String doInBackground(String... params) {
        FxQuote rate = null;
        try {
            rate = YahooFinance.getFx(params[0] + "=X");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!isNetworkAvailable())
            return "No network connection";
        if (rate != null) {
            BigDecimal amount = getAmount();
            BigDecimal result = rate.getPrice();
            result = result.multiply(amount);
            result = result.setScale(4, RoundingMode.CEILING);
            return result.toString();
        }

        return "Error";
    }

    @Override
    protected void onPostExecute(String s) {
        resultView.setText(s);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private BigDecimal getAmount() {
        String text = editAmount.getText().toString();
        if (text.isEmpty()) {
            editAmount.setText("1.0");
            return new BigDecimal(1.0);
        }

        BigDecimal value = null;
        try {
            value = new BigDecimal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (value == null) {
            editAmount.setText("1.0");
            return new BigDecimal(1.0);
        }

        return value;
    }

}
