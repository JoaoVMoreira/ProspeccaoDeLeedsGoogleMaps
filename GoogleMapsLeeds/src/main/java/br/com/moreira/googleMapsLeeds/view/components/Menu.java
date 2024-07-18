package br.com.moreira.googleMapsLeeds.view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Menu extends JPanel{
    public Menu(){
        // Painel lateral
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(230, 230, 230)); // Cor de fundo cinza claro
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Botões do painel lateral
        for (int i = 0; i < 4; i++) {
            JButton sideButton = new JButton("H");
            sideButton.setFont(new Font("Arial", Font.BOLD, 20));
            sideButton.setPreferredSize(new Dimension(50, 50));
            sideButton.setMaximumSize(new Dimension(50, 50));
            add(sideButton);
            add(Box.createVerticalStrut(10)); // Espaçamento entre os botões
        }
    }
}
