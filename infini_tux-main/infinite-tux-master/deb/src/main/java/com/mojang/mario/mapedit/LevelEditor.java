package com.mojang.mario.mapedit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.*;

import com.mojang.mario.level.*;


public class LevelEditor extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 7461321112832160393L;

    private JButton loadButton;
    private JButton saveButton;
    private JButton newButton;
    private JTextField nameField;
    private LevelEditView levelEditView;
    private TilePicker tilePicker;
    private JLabel coordinates;
    private String coordinateText="X=P , Y=Q";
    
    private JCheckBox[] bitmapCheckboxes = new JCheckBox[8];

    public LevelEditor()
    {
        super("Map Edit");
        
        try
        {
            Level.loadBehaviors(new DataInputStream(new FileInputStream("tiles.dat")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.toString(), "Failed to load tile behaviors", JOptionPane.ERROR_MESSAGE);
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 8 / 10, screenSize.height * 8 / 10);
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tilePicker = new TilePicker();
        JPanel tilePickerPanel = new JPanel(new BorderLayout());
        tilePickerPanel.add(BorderLayout.WEST, tilePicker);
        tilePickerPanel.add(BorderLayout.CENTER, buildBitmapPanel());
        tilePickerPanel.setBorder(new TitledBorder(new EtchedBorder(), "Tile picker"));

        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.add(BorderLayout.WEST, tilePickerPanel);

        JPanel borderPanel = new JPanel(new BorderLayout());
        levelEditView = new LevelEditView(tilePicker);
        borderPanel.add(BorderLayout.CENTER, new JScrollPane(levelEditView));
        borderPanel.add(BorderLayout.SOUTH, lowerPanel);
        borderPanel.add(BorderLayout.NORTH, buildButtonPanel());
        setContentPane(borderPanel);

        tilePicker.addTilePickChangedListener(this);
    }

    public JPanel buildBitmapPanel()
    {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        for (int i=0; i<8; i++)
        {
            bitmapCheckboxes[i] = new JCheckBox(Level.BIT_DESCRIPTIONS[i]);
            panel.add(bitmapCheckboxes[i]);
            if (Level.BIT_DESCRIPTIONS[i].startsWith("- ")) bitmapCheckboxes[i].setEnabled(false);
            
            final int id = i;
            bitmapCheckboxes[i].addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    int bm = Level.TILE_BEHAVIORS[tilePicker.pickedTile&0xff]&0xff;
                    bm&=255-(1<<id);
                    if (bitmapCheckboxes[id].isSelected()) bm|=(1<<id);
                    Level.TILE_BEHAVIORS[tilePicker.pickedTile&0xff] = (byte)bm;

                    try
                    {
                        Level.saveBehaviors(new DataOutputStream(new FileOutputStream("tiles.dat")));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(LevelEditor.this, e.toString(), "Failed to load tile behaviors", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
        return panel;
    }

    public JPanel buildButtonPanel()
    {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        newButton = new JButton("New");
        nameField = new JTextField("test.lvl", 10);
        coordinates = new JLabel(coordinateText,10);
        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
        newButton.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.add(nameField);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(newButton);
        panel.add(coordinates);
        return panel;
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if (e.getSource() == loadButton)
            {
                levelEditView.setLevel(Level.load(new DataInputStream(new FileInputStream(nameField.getText().trim()))));
            }
            if (e.getSource() == saveButton)
            {
                levelEditView.getLevel().save(new DataOutputStream(new FileOutputStream(nameField.getText().trim())));
            }
            if (e.getSource() == newButton)
            {
                levelEditView.getLevel().save(new DataOutputStream(new FileOutputStream(nameField.getText().trim())));
            }            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.toString(), "Failed to load/save", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void setCoordinates(int x , int y)
    {
        coordinateText="X=" + x +" , " +"Y="+y;
        coordinates.setText(coordinateText);
    
    }
    
    
    public static void main(String[] args)
    {
        new LevelEditor().setVisible(true);
    }

    public void setPickedTile(byte pickedTile)
    {
        int bm = Level.TILE_BEHAVIORS[pickedTile&0xff]&0xff;
        
        for (int i=0; i<8; i++)
        {
            bitmapCheckboxes[i].setSelected((bm&(1<<i))>0);
        }
    }
}
