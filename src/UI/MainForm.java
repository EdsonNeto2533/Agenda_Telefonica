package UI;

import business.contactBusiness;
import entity.contactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton NewContact;
    private JButton RemoverContato;
    private JTable TabelasContatos;
    private JLabel LBLquant;
    private String auxNome, auxTelefone;
    private contactBusiness mcontactbusiness;

    //construtor
    public MainForm() {
        //nome do painel a ser chamado na criação
        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        //Encerra programa qnd fechar a janela
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //instancia a contact list e chama os metodos dos eventos dos botoes e carrega contatos
        mcontactbusiness = new contactBusiness();
        setListeners();
        loadcontacts();
    }

    private void setListeners() {
        NewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                //fecha o jframe
                dispose();
            }
        });
        //evento para pegar a seleção da linha na tabela
        TabelasContatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            // o e ta dentro do parametro, essa função é pra pegar exatamente onde vc selecionou
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {

                    if (TabelasContatos.getSelectedRow() != -1) {
                        //pegando a linha seleciona e especificando a coluna
                        //lembrar de declarar nome e telefone global
                        auxNome = TabelasContatos.getValueAt(TabelasContatos.getSelectedRow(), 0).toString();
                        auxTelefone = TabelasContatos.getValueAt(TabelasContatos.getSelectedRow(), 1).toString();

                    }

                }

            }
        });


        RemoverContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mcontactbusiness.delete(auxNome, auxTelefone);
                    auxTelefone = "";
                    auxNome = "";
                    loadcontacts();
                } catch (Exception exception) {
                    //get message na exceção vai pegar a mensagem q definimos la no validate
                    JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
                }

            }
        });
    }

    //recebendo a lista de contatos
    private void loadcontacts() {
        //puxando a lista de contatos
        List<contactEntity> contactlist = mcontactbusiness.getList();
        //nome das colunas
        String[] columName = {"Nome", "Telefone"};
        //modelo de tabela para associar a tabela
        //object[] [] é o numero de colunas
        DefaultTableModel table = new DefaultTableModel(new Object[0][0], columName);

        //alimentando a tabela
        for (contactEntity i : contactlist) {
            //especificando as posições
            Object[] o = new Object[2];

            o[0] = i.getNome();
            o[1] = i.getTelefone();

            table.addRow(o);

        }
        //sempre é bom limpar a seleção para evitar problemas
        TabelasContatos.clearSelection();
        // aqui eu seto a tabela q eu alimentei antes na tabela de contatos
        TabelasContatos.setModel(table);

        LBLquant.setText(String.valueOf(contactlist.size()));


    }
}
