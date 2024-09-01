import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> inputScale;
    private JComboBox<String> outputScale;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        ImagePanel panel = new ImagePanel(new ImageIcon("background.jpg").getImage());
        add(panel);
        panel.setLayout(null);

        JLabel inputLabel = new JLabel("Enter temperature:");
        inputLabel.setBounds(30, 30, 120, 25);
        panel.add(inputLabel);

        inputField = new JTextField(10);
        inputField.setBounds(160, 30, 100, 25);
        panel.add(inputField);

        inputScale = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        inputScale.setBounds(270, 30, 100, 25);
        panel.add(inputScale);

        JLabel outputLabel = new JLabel("Convert to:");
        outputLabel.setBounds(30, 70, 100, 25);
        panel.add(outputLabel);

        outputScale = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        outputScale.setBounds(160, 70, 100, 25);
        panel.add(outputScale);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(160, 110, 100, 25);
        panel.add(convertButton);

        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(30, 150, 300, 25);
        panel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String fromScale = (String) inputScale.getSelectedItem();
            String toScale = (String) outputScale.getSelectedItem();

            double resultTemp = 0;

            if (fromScale.equals("Celsius")) {
                if (toScale.equals("Fahrenheit")) {
                    resultTemp = (inputTemp * 9 / 5) + 32;
                } else if (toScale.equals("Kelvin")) {
                    resultTemp = inputTemp + 273.15;
                } else {
                    resultTemp = inputTemp;
                }
            } else if (fromScale.equals("Fahrenheit")) {
                if (toScale.equals("Celsius")) {
                    resultTemp = (inputTemp - 32) * 5 / 9;
                } else if (toScale.equals("Kelvin")) {
                    resultTemp = (inputTemp - 32) * 5 / 9 + 273.15;
                } else {
                    resultTemp = inputTemp;
                }
            } else if (fromScale.equals("Kelvin")) {
                if (toScale.equals("Celsius")) {
                    resultTemp = inputTemp - 273.15;
                } else if (toScale.equals("Fahrenheit")) {
                    resultTemp = (inputTemp - 273.15) * 9 / 5 + 32;
                } else {
                    resultTemp = inputTemp;
                }
            }

            resultLabel.setText("Result: " + resultTemp + " " + toScale);

        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }
}


class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
