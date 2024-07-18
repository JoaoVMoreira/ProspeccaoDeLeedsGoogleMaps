package br.com.moreira.googleMapsLeeds.view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindCommerces extends JFrame{
    public FindCommerces(){
        setTitle("Prospecção de comercios");
        setVisible(true);
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        Menu sidePanel = new Menu();

        // Painel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel superior (input e botões INICIAR e CANCELAR)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Campo de entrada de coordenadas
        JTextField coordinatesField = new JTextField();
        coordinatesField.setPreferredSize(new Dimension(550, 30));
        coordinatesField.setMaximumSize(new Dimension(500, 30));
        topPanel.add(coordinatesField, BorderLayout.CENTER);

        // Painel de botões na parte superior direita
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton startButton = new JButton("INICIAR");
        startButton.setBackground(new Color(0, 0, 255));
        startButton.setForeground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setMaximumSize(new Dimension(200, 50));

        JButton cancelButton = new JButton("CANCELAR");
        cancelButton.setBackground(new Color(255, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(200, 50));
        cancelButton.setMaximumSize(new Dimension(200, 50));

        topRightPanel.add(startButton);
        topRightPanel.add(cancelButton);
        topPanel.add(topRightPanel, BorderLayout.EAST);

        centerPanel.add(topPanel, BorderLayout.NORTH);

        // Tabela
        String[] columnNames = {"Nome", "Segmento", "Cidade", "Contato", "Site", "Excluir"};
        Object[][] data = {
                {"Data1", "Data2", "Data3", "Data4", "Data5", null},
                {"Data6", "Data7", "Data8", "Data9", "Data10", null}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        JTable table = new JTable(model);
        table.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
        table.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(400, 200));
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Botão "ADICIONAR AO DB" na parte inferior central
        JButton addToDbButton = new JButton("ADICIONAR AO DB");
        addToDbButton.setBackground(new Color(0, 128, 0));
        addToDbButton.setForeground(Color.WHITE);
        centerPanel.add(addToDbButton, BorderLayout.SOUTH);

        // Adiciona os painéis ao frame principal
        JFrame.add(sidePanel, BorderLayout.WEST);
        JFrame.add(centerPanel, BorderLayout.CENTER);

        // Torna o frame visível
        setVisible(true);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(Color.RED);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (table.getValueAt(row, 0) != null) {
                setEnabled(true);
            } else {
                setEnabled(false);
            }
            return this;
        }
    }

    // Classe para editar botões na tabela
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(Color.RED);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Ação ao clicar no botão
                System.out.println("Button clicked");
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
