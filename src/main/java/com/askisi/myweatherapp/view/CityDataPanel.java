package com.askisi.myweatherapp.view;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import Pojos.City;
import com.askisi.myweatherapp.controller.CityRepo;
import com.askisi.myweatherapp.controller.WeatherRepo;
import java.io.IOException;
import java.util.Date;
import javax.swing.table.TableModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class CityDataPanel extends JDialog{
    
    private JComboBox<String> cityComboBox;
    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;
    private JButton searchButton;
    private JButton exportButton;
    private JTable weatherTable=new JTable();
    
    public CityDataPanel(JFrame parent) {
        super(parent, "Weather Search", true);
        CityRepo repo=new CityRepo();
        WeatherRepo wRepo=new WeatherRepo();
        setLocationRelativeTo(parent);
        setSize(600, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // City ComboBox
        JLabel cityLabel = new JLabel("City:");
        panel.add(cityLabel, gbc);

        cityComboBox = new JComboBox<>();
        List<City> cities = repo.getAllCities();
        for (City city : cities) {
            cityComboBox.addItem(city.getName());
        }
        gbc.gridx++;
        panel.add(cityComboBox, gbc);

        // Start Date Picker
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel startDateLabel = new JLabel("Start Date:");
        panel.add(startDateLabel, gbc);

        UtilDateModel startDateModel = new UtilDateModel();
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startDateModel);
        startDatePicker = new JDatePickerImpl(startDatePanel);
        gbc.gridx++;
        panel.add(startDatePicker, gbc);

        // End Date Picker
        gbc.gridx++;
        JLabel endDateLabel = new JLabel("End Date:");
        panel.add(endDateLabel, gbc);

        UtilDateModel endDateModel = new UtilDateModel();
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel);
        endDatePicker = new JDatePickerImpl(endDatePanel);
        gbc.gridx++;
        panel.add(endDatePicker, gbc);

        // Search Button
        gbc.gridy++;
        gbc.gridx=0;
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wRepo.searchWeatherData(cityComboBox.getSelectedItem().toString(),(Date) startDatePicker.getModel().getValue(),(Date) endDatePicker.getModel().getValue(),weatherTable);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
        panel.add(searchButton, gbc);
        
        // Export Button
        gbc.gridx++;
        exportButton = new JButton("Export");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cityName = cityComboBox.getSelectedItem().toString();
                String filePath = cityName + "_weather_data.pdf";
                try {
                    PDDocument document = new PDDocument();
                    PDPage page = new PDPage();
                   
                    document.addPage(page);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);

                    // Write city name on top
                    contentStream.beginText();
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
                    contentStream.newLineAtOffset(100, 700); // Adjust coordinates as needed
                    contentStream.showText("City: " + cityName);
                    contentStream.endText();

                    // Write table data
                    TableModel model = weatherTable.getModel();
                    int rowCount = model.getRowCount();
                    int colCount = model.getColumnCount();
                    float yPosition = 680; // Initial y position below city name

                    for (int row = 0; row < rowCount; row++) {
                        for (int col = 0; col < colCount; col++) {
                            String cellValue = model.getValueAt(row, col).toString();
                            contentStream.beginText();
                            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 10);
                            contentStream.newLineAtOffset(100 + col * 100, yPosition - row * 20); // Adjust x and y coordinates as needed
                            contentStream.showText(cellValue);
                            contentStream.endText();
                        }
                    }

                    contentStream.close();
                    document.save(filePath);
                    document.close();

                    JOptionPane.showMessageDialog(CityDataPanel.this, "PDF exported successfully to: " + filePath, "Export Successful", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CityDataPanel.this, "Error exporting PDF: " + ex.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(exportButton, gbc);
       
        // Weather Table
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(weatherTable);
        panel.add(scrollPane, gbc);
        getContentPane().add(panel);
    }
}
