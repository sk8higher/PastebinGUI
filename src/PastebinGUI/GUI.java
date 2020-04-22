package PastebinGUI;

import java.awt.*;
import javax.swing.*;


public class GUI {

    public static void Gui() {
        JFrame frame = new JFrame();
        frame.setTitle("Pastebin Desktop Sender");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
        frame.setIconImage(icon);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Paste name:");
        label.setBounds(12, 12, 163, 20);

        final JTextField textFieldPostName = new JTextField();
        textFieldPostName.setBounds(90, 13, 273, 18);
        textFieldPostName.setToolTipText("Optional");
        textFieldPostName.setColumns(10);

        JDialog aboutDialog = new JDialog();
        aboutDialog.setLocationRelativeTo(null);
        aboutDialog.setSize(490, 255);
        aboutDialog.getContentPane().setLayout(null);
        aboutDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        aboutDialog.setAlwaysOnTop(true);
        JLabel lblPastebinDesktopSender = new JLabel("PasteBin Desktop Sender");
        lblPastebinDesktopSender.setHorizontalAlignment(SwingConstants.CENTER);
        lblPastebinDesktopSender.setFont(new Font("Liberation Serif", Font.BOLD, 15));
        lblPastebinDesktopSender.setBounds(12, 12, 476, 14);
        aboutDialog.getContentPane().add(lblPastebinDesktopSender);

        JLabel lblVersion = new JLabel("Version 1.0");
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblVersion.setBounds(12, 38, 476, 14);
        aboutDialog.getContentPane().add(lblVersion);

        JLabel lblNewLabel = new JLabel("If you have any requests, send me a pull request on Github.");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblNewLabel.setBounds(12, 78, 476, 14);
        aboutDialog.getContentPane().add(lblNewLabel);

        JLabel lblIfYouHave = new JLabel("If you have any troubles, open an issue on GitHub or contact me.");
        lblIfYouHave.setHorizontalAlignment(SwingConstants.CENTER);
        lblIfYouHave.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblIfYouHave.setBounds(12, 104, 476, 14);
        aboutDialog.getContentPane().add(lblIfYouHave);

        JButton btnOk = new JButton("Ok");
        btnOk.setBounds(201, 166, 98, 24);
        btnOk.addActionListener(e -> aboutDialog.setVisible(false));
        aboutDialog.getContentPane().add(btnOk);


        JButton btnAbout = new JButton("About");
        btnAbout.setBounds(670, 10, 98, 24);
        btnAbout.addActionListener(e -> aboutDialog.setVisible(true));

        JLabel labelPostLink = new JLabel("Paste link:");
        labelPostLink.setBounds(12, 602, 151, 29);

        final JTextField textField_1 = new JTextField();
        textField_1.setBounds(80, 606, 182, 20);
        textField_1.setColumns(10);

        JLabel lblPostText = new JLabel("Paste text:");
        lblPostText.setBounds(12, 44, 163, 14);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(textFieldPostName);
        frame.getContentPane().add(label);
        frame.getContentPane().add(btnAbout);
        frame.getContentPane().add(lblPostText);
        frame.getContentPane().add(textField_1);
        frame.getContentPane().add(labelPostLink);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 70, 756, 469);
        frame.getContentPane().add(scrollPane);

        final JEditorPane editorPane = new JEditorPane();
        scrollPane.setViewportView(editorPane);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(12, 557, 618, 33);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        frame.getContentPane().add(progressBar);

        JButton btnSend = new JButton("Send");
        btnSend.setBounds(642, 557, 126, 73);
        btnSend.addActionListener(e -> {
            try {
                new Thread(() -> {
                    Main.setApi_paste_code(editorPane.getText());
                    Main.setApi_paste_name(textFieldPostName.getText());
                    try {
                        textField_1.setText(Main.postRequest());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    for(int i = 0; i <= 100; i++) {
                        progressBar.setValue(i);
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    textField_1.requestFocus();
                    textField_1.selectAll();
                    progressBar.setValue(0);
                }).start();
            } catch (Exception var3) {
                System.err.println("Error");
            }
        });
        frame.getContentPane().add(btnSend);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(260, 604, 98, 24);
        btnClear.addActionListener(e -> {
            textFieldPostName.setText("");
            textField_1.setText("");
            editorPane.setText("");
        });
        frame.getContentPane().add(btnClear);

        frame.setSize(780, 675);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
