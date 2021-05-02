package UI;

import business.contactBusiness;
import entity.contactEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {
    private JPanel ContactFormPanel;
    private JTextField NomeTextField;
    private JTextField TelefoneTextField;
    private JButton SalvarBTN;
    private JButton CancelarBTN;
    private contactBusiness mcontactbussiness;

    public ContactForm() {
        //nome do painel a ser chamado na criação
        setContentPane(ContactFormPanel);
        setSize(500, 250);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mcontactbussiness = new contactBusiness();
        setListeners();
    }

    private void setListeners() {
        SalvarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mcontactbussiness.save(NomeTextField.getText(), TelefoneTextField.getText());
                    new MainForm();
                    dispose();
                }   catch (Exception exception){
                    //get message na exceção vai pegar a mensagem q definimos la no validate
                    JOptionPane.showMessageDialog(new JFrame() , exception.getMessage());
                }

            }
        });

        CancelarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // botao de cancelar vai so abrir o mainForm e se fechar
                new MainForm();
                //fecha o jframe
                dispose();
            }
        });

    }

    public void SalvarContato() {

    }
}
