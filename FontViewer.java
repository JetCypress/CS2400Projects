//
//   Name:      Mah, Dean
//   Project:   #3
//   Due:       8 October 2021
//   Course:    CS-2450-01-F21
//
//   Description:
//                The program will create an application that allows the user to choose
//                the size, font, style, and effects that are applied to the preview text underneath.

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class FontViewer implements ActionListener {
   
   private JPanel SizePanel;
   private JLabel SizeLabel;
   private JSlider SizeSlider;

   private JPanel FontPanel;
   private JLabel FontLabel;

   private JPanel StylePanel;
   private JLabel StyleLabel;
   private ButtonGroup StyleButtons;

   private JPanel EffectsPanel;
   private JLabel EffectsLabel;

   private JPanel PreviewPanel;
   private JLabel PreviewText;

   public FontViewer() {
      JFrame jfrm = new JFrame("Font Viewer");
      jfrm.setSize(600, 300);
      jfrm.setLayout(new BorderLayout());
      jfrm.setLocationRelativeTo(null);
      jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jfrm.setVisible(true);

      SizeLabel = new JLabel("Size:", JLabel.LEFT);
      SizeLabel.setDisplayedMnemonic('S');
      SizePanel = new JPanel();
      SizePanel.setLayout(new BorderLayout());

      PreviewPanel = new JPanel();
      PreviewPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      jfrm.add(PreviewPanel, BorderLayout.SOUTH);
      PreviewText = new JLabel("the quick brown fox jumps over the lazy dog 0123456789");
      PreviewPanel.add(PreviewText);

      SizeSlider = new JSlider(8, 20, 12);
      SizeSlider.setMajorTickSpacing(1);
      SizeSlider.setPaintLabels(true);
      SizeSlider.setPaintTicks(true);
      SizeSlider.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent event) {
            int Size = SizeSlider.getValue();
            PreviewText.setFont(new Font(PreviewText.getFont().getName(), PreviewText.getFont().getStyle(), Size));
         }
      });

      SizePanel.setBorder(BorderFactory.createLineBorder(Color.black));
      jfrm.add(SizePanel, BorderLayout.NORTH);
      SizePanel.add(SizeLabel, BorderLayout.NORTH);
      SizePanel.add(SizeSlider, BorderLayout.SOUTH);

      FontPanel = new JPanel(new GridLayout(2, 1));
      FontPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      jfrm.add(FontPanel, BorderLayout.WEST);
      FontLabel = new JLabel("Fonts:", JLabel.LEFT);
      FontLabel.setDisplayedMnemonic('F');
      FontPanel.add(FontLabel);
      JList<String> FontList = new JList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
      FontPanel.add(new JScrollPane(FontList));
      FontList.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent evt) {
            if (!FontList.getValueIsAdjusting()) {
               PreviewText.setFont(new Font(FontList.getSelectedValue(), PreviewText.getFont().getStyle(), PreviewText.getFont().getSize()));
            }
         }
      });

      StylePanel = new JPanel(new GridLayout(4, 1));
      StylePanel.setBorder(BorderFactory.createLineBorder(Color.black));
      jfrm.add(StylePanel, BorderLayout.CENTER);
      StyleLabel = new JLabel("Style:", JLabel.LEFT);
      StyleLabel.setDisplayedMnemonic('S');
      StylePanel.add(StyleLabel);
      JRadioButton RegularButton = new JRadioButton("Regular");
      JRadioButton ItalicButton = new JRadioButton("Italic");
      JRadioButton BoldButton = new JRadioButton("Bold", true);
      StyleButtons = new ButtonGroup();
      StyleButtons.add(RegularButton);
      StyleButtons.add(ItalicButton);
      StyleButtons.add(BoldButton);
      StylePanel.add(RegularButton);
      StylePanel.add(ItalicButton);
      StylePanel.add(BoldButton);
      RegularButton.addActionListener(this);
      ItalicButton.addActionListener(this);
      BoldButton.addActionListener(this);

      EffectsPanel = new JPanel(new GridLayout(2, 1));
      EffectsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      jfrm.add(EffectsPanel, BorderLayout.EAST);
      EffectsLabel = new JLabel("Effects:", JLabel.LEFT);
      EffectsLabel.setDisplayedMnemonic('E');
      EffectsPanel.add(EffectsLabel);
      JCheckBox CapsBox = new JCheckBox("All caps");
      CapsBox.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent event) {
            if(CapsBox.isSelected()){
               PreviewText.setText(PreviewText.getText().toUpperCase());
            }
            else {
               PreviewText.setText(PreviewText.getText().toLowerCase());
            }
         }
      });
      EffectsPanel.add(CapsBox);
   }

   public static void main (String [] args){

      // run program
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new FontViewer();
         }
      });
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      switch(e.getActionCommand()) {
         case "Regular":
            PreviewText.setFont(new Font(PreviewText.getFont().getName(), Font.PLAIN, PreviewText.getFont().getSize()));
            break;
         case "Italic":
            PreviewText.setFont(new Font(PreviewText.getFont().getName(), Font.ITALIC, PreviewText.getFont().getSize()));
            break;
         case "Bold":
         PreviewText.setFont(new Font(PreviewText.getFont().getName(), Font.BOLD, PreviewText.getFont().getSize()));
         break;
      }
   }
}