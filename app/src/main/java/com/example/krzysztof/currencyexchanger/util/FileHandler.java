package com.example.krzysztof.currencyexchanger.util;

import android.content.Context;

import com.example.krzysztof.currencyexchanger.model.SpinnerItem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {

    private static final String FILENAME = "CurrencyExchangerItems.data";

    public static ArrayList<SpinnerItem> getSpinnerItems(Context context) {
        ArrayList<SpinnerItem> items = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            items = (ArrayList<SpinnerItem>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        if (items == null)
            items = getInitialList();

        return items;
    }

    public static void saveSpinnerItems(Context context, ArrayList<SpinnerItem> items) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static ArrayList<SpinnerItem> getInitialList() {
        String[] currencies = new String[]{"EUR", "USD", "GBP", "JPY", "AED", "ALL", "ANG",
                "ARS", "AUD", "AWG", "BBD", "BCE", "BDT", "BEA", "BGN", "BHD", "BIF", "BMD",
                "BND", "BOB", "BRL", "BSD", "BTN", "BWP", "BYR", "BZD", "CAD", "CHF", "CLP",
                "CNY", "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "ECS",
                "EEK", "EGP", "ERN", "ETB", "FJD", "FKP", "GHC", "GIP", "GMD",
                "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "INR",
                "IQD", "IRR", "ISK", "JMD", "JOD", "KES", "KHR", "KMF", "KPW", "KRW",
                "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD",
                "MAD", "MDL", "MKD", "MMK", "MNT", "MOP", "MRO", "MTL", "MUR", "MVR", "MWK",
                "MXN", "MYR", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "Off", "PAB",
                "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RUB", "RWF", "SAR",
                "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SIT", "SKK", "SLL", "SOS", "STD",
                "SVC", "SYP", "SZL", "THB", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH",
                "UGX", "UYU", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAL", "XAU",
                "XCD", "XCP", "XOF", "XPD", "XPF", "XPT", "YER", "ZAR", "ZMK", "ZWD"};

        ArrayList<SpinnerItem> list = new ArrayList<>();
        for (String s : currencies)
            list.add(new SpinnerItem(s));

        return list;
    }
}