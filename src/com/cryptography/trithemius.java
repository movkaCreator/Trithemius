package com.cryptography;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class trithemius extends JFrame {
    public trithemius() {
        setTitle("Øèôð Òðèòåìèóñà");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ïàíåëü JPanel — ýòî ýëåìåíò óïðàâëåíèÿ, ïðåäñòàâëÿþùèé ñîáîé ïðÿìîóãîëüíîå
        // ïðîñòðàíñòâî, íà êîòîðîì ìîæíî ðàçìåùàòü äðóãèå ýëåìåíòû.
        // Ó êàæäîé ïàíåëè åñòü òàê íàçûâàåìûé ìåíåäæåð ðàçìåùåíèÿ,
        // êîòîðûé îïðåäåëÿåò ñòðàòåãèþ âçàèìíîãî ðàñïîëîæåíèÿ ýëåìåíòîâ, äîáàâëÿåìûõ íà ïàíåëü.
        // Åãî ìîæíî èçìåíèòü ìåòîäîì setLayout(LayoutManager manager).

        // Ìåíåäæåð ãðàíè÷íîãî ðàçìåùåíèÿ BorderLayout
        // Ìåíåäæåð ðàçìåùåíèÿ BorderLayout ðàçäåëÿåò ïàíåëü íà ïÿòü îáëàñòåé:
        // öåíòðàëüíóþ, âåðõíþþ, íèæíþþ, ïðàâóþ è ëåâóþ.
        // Â êàæäóþ èç ýòèõ îáëàñòåé ìîæíî äîáàâèòü ðîâíî ïî îäíîìó êîìïîíåíòó,
        // ïðè÷åì êîìïîíåíò áóäåò çàíèìàòü âñþ îòâåäåííóþ äëÿ íåãî îáëàñòü.
        // Êîìïîíåíòû, äîáàâëåííûå â âåðõíþþ è íèæíþþ îáëàñòè, áóäóò ðàñòÿíóòû ïî øèðèíå,
        // äîáàâëåííûå â ïðàâóþ è ëåâóþ — ïî âûñîòå, à êîìïîíåíò, äîáàâëåííûé â öåíòð, áóäåò ðàñòÿíóò òàê,
        // ÷òîáû ïîëíîñòüþ çàïîëíèòü îñòàâøååñÿ ïðîñòðàíñòâî ïàíåëè.

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(30, 30, 30, 30));
        content.setLayout(new BorderLayout());

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new BorderLayout());
        upPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // EtchedBorder — ðàìêà ñ òèñíåíèåì.
        // TitledBorder — ðàìêà ñ çàãîëîâêîì.
        // JScrollPane — ïàíåëü ïðîêðóòêè

        JTextArea textInput = new JTextArea(10, 40);
        JScrollPane textInputScroll = new JScrollPane(textInput);
        textInputScroll.setBorder(new TitledBorder(new EtchedBorder(),
                "Èñõîäíûé òåêñò"));

        JTextArea textOutput = new JTextArea(10, 40);
        JScrollPane textOutputScroll = new JScrollPane(textOutput);
        textOutputScroll.setBorder(new TitledBorder(new EtchedBorder(),
                "Ðåçóëüòàò"));

        upPanel.add(textInputScroll, BorderLayout.WEST);
        upPanel.add(textOutputScroll, BorderLayout.EAST);

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new BorderLayout());
        downPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(3, 2));
        keyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Êëþ÷è äëÿ ñìåùåíèÿ k = Ap\u00B2 + Bp + C"));
        JTextField[] keyFields = new JTextField[3];
        char keyFieldName = 'A';
        for (int i = 0; i < keyFields.length; i++) {
            keyFields[i] = new JTextField();
            keyPanel.add(new JLabel("Êîýôôèöèåíò " + keyFieldName++));
            keyPanel.add(keyFields[i]);
        }

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1));

        JButton encryptButton = new JButton("Çàøèôðîâàòü");
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textOutput.setText("");
                String text = textInput.getText();
                int[] keys = new int[3];
                for (int i = 0; i < keys.length; i++) {
                    keys[i] = Integer.parseInt(keyFields[i].getText());
                }
                textOutput.append(encrypt(text, keys));
            }
        });

        JButton decryptButton = new JButton("Ðàñøèôðîâàòü");
        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textOutput.setText("");
                String text = textInput.getText();
                int[] keys = new int[3];
                for (int i = 0; i < keys.length; i++) {
                    keys[i] = Integer.parseInt(keyFields[i].getText());
                }
                textOutput.append(decrypt(text, keys));
            }
        });

        buttonsPanel.add(encryptButton);
        buttonsPanel.add(decryptButton);

        downPanel.add(keyPanel, BorderLayout.NORTH);
        downPanel.add(buttonsPanel, BorderLayout.SOUTH);

        content.add(upPanel, BorderLayout.NORTH);
        content.add(downPanel, BorderLayout.SOUTH);

        // Ìåòîä setContentPane(JPanel panel) ïîçâîëÿåò çàìåíèòü ïàíåëü ñîäåðæèìîãî îêíà.
        // Åñëè âìåñòî ÿâíîãî óêàçàíèÿ ðàçìåðîâ îêíà, âûçâàòü ìåòîä pack(),
        // îíè áóäóò ïîäîáðàíû îïòèìàëüíûì îáðàçîì ñ ó÷åòîì ïðåäïî÷òåíèé âñåõ ýëåìåíòîâ, ðàçìåùåííûõ â ýòîì îêíå

        setContentPane(content);
        pack();
        setLocationRelativeTo(null);
    }

    private String encrypt(String text, int[] keys) {
        int A = keys[0];
        int B = keys[1];
        int C = keys[2];
        char[] openText = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < openText.length; i++) {
            int k = A * (i * i) + B * i + C;
            int L = ((openText[i]) + k) % 65536;
            sb.append((char) L);
        }
        return sb.toString();
    }

    private String decrypt(String text, int[] keys) {
        int A = keys[0];
        int B = keys[1];
        int C = keys[2];
        char[] openText = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < openText.length; i++) {
            int k = A * (i * i) + B * i + C;
            int tmp = ((openText[i]) - k);
            while (tmp < 0) {
                tmp += 65536;
            }
            int L = tmp % 65536;
            sb.append((char) L);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new trithemius().setVisible(true);
    }
}
