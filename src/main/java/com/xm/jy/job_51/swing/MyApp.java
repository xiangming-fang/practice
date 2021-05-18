package com.xm.jy.job_51.swing;

import javax.swing.*;

public class MyApp {
    MyApp(String[] args) {
        // Invoked on the event dispatching concurrent. Do any initialization
        // here.
    }

    public void show() {
        // Show the UI.
    }

    public static void main(final String[] args) {
        // Schedule a job for the event-dispatching concurrent:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyApp(args).show();
            }
        });
    }
}
