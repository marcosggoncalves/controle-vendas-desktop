/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Marcos Lopes G
 */
public class Utilitarios {
    
    private Color colorPrincipal = new Color(38, 54, 127);

    public void LimparTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
    }
    
    public void StyleHeaderTable (JTable table){
        JTableHeader Theader = table.getTableHeader();
        Theader.setOpaque(false);
        Theader.setBackground(colorPrincipal); 
        Theader.setForeground(Color.white);
        Theader.setFont(new Font("Arial", Font.BOLD, 16)); 
    }

    public void btnColor(JButton button) {
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(colorPrincipal);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
    }

    public void InserirIcone(JFrame frm) {
        try {
            frm.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/icone.png"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void alert(String titulo, String message) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/logado.png"));
        JOptionPane.showMessageDialog(null, message, titulo, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public void btnsColor(JTabbedPane tabConsulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
