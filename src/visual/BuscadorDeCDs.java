package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import jcf.Ordenacao;
import objetos.Busca;
import objetos.CD;
import proj_patterns.PersquisaPrecosFacade;

public class BuscadorDeCDs extends JFrame {

    private PersquisaPrecosFacade facadePesquisa;
    private Busca buscaAtual;

    private JTextField tfBusca;
    private JButton btnBuscar;

    private JComboBox<Ordenacao> cbOrdenacao;
    private JTable tabelaResultados;
    private DefaultTableModel tabelaResultadosModel;
    private static String[] tabelaResultadosColunas = { "T\u00EDtulo", "Banda", "Loja", "Pre\u00E7o" };
    private JButton btnSalvarBusca;

    private JList<Busca> listaBuscas;
    private DefaultListModel<Busca> listaBuscasModel;

    private JButton btnDeletarbusca;
    private JButton btnCarregarBusca;

    public BuscadorDeCDs() {
        setSize(new Dimension(1024, 768));
        setResizable(false);
        setTitle("Trabalho 02 - Busca de CDs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel pnMeio = new JPanel();
        pnMeio.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(pnMeio, BorderLayout.CENTER);
        pnMeio.setLayout(new BorderLayout(0, 0));

        JPanel pnBusca = new JPanel();
        pnMeio.add(pnBusca, BorderLayout.NORTH);
        pnBusca.setLayout(new BorderLayout(0, 0));

        JPanel pnTituloBuscar = new JPanel();
        pnBusca.add(pnTituloBuscar, BorderLayout.NORTH);
        GridBagLayout gbl_pnTituloBuscar = new GridBagLayout();
        gbl_pnTituloBuscar.columnWidths = new int[] { 20, 0, 0 };
        gbl_pnTituloBuscar.rowHeights = new int[] { 30, 0 };
        gbl_pnTituloBuscar.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_pnTituloBuscar.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        pnTituloBuscar.setLayout(gbl_pnTituloBuscar);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
        gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
        gbc_horizontalStrut.gridx = 0;
        gbc_horizontalStrut.gridy = 0;
        pnTituloBuscar.add(horizontalStrut, gbc_horizontalStrut);

        JLabel lblBuscarCds = new JLabel("Buscar CDs:");
        lblBuscarCds.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc_lblBuscarCds = new GridBagConstraints();
        gbc_lblBuscarCds.gridx = 1;
        gbc_lblBuscarCds.gridy = 0;
        pnTituloBuscar.add(lblBuscarCds, gbc_lblBuscarCds);

        JPanel pnBuscar = new JPanel();
        pnBusca.add(pnBuscar);
        GridBagLayout gbl_pnBuscar = new GridBagLayout();
        gbl_pnBuscar.columnWidths = new int[] { 20, 0, 20, 0, 20, 0 };
        gbl_pnBuscar.rowHeights = new int[] { 0, 20, 0 };
        gbl_pnBuscar.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_pnBuscar.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        pnBuscar.setLayout(gbl_pnBuscar);

        Component horizontalStrut1 = Box.createHorizontalStrut(20);
        GridBagConstraints gbc_horizontalStrut1 = new GridBagConstraints();
        gbc_horizontalStrut1.insets = new Insets(0, 0, 5, 5);
        gbc_horizontalStrut1.gridx = 0;
        gbc_horizontalStrut1.gridy = 0;
        pnBuscar.add(horizontalStrut1, gbc_horizontalStrut1);

        tfBusca = new JTextField();
        tfBusca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Se pressionar enter faz a busca
                realizaBusca();
            }
        });
        tfBusca.setHorizontalAlignment(SwingConstants.LEFT);
        tfBusca.setMinimumSize(new Dimension(6, 30));
        tfBusca.setPreferredSize(new Dimension(6, 30));
        GridBagConstraints gbc_tfBusca = new GridBagConstraints();
        gbc_tfBusca.insets = new Insets(0, 0, 5, 5);
        gbc_tfBusca.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfBusca.gridx = 1;
        gbc_tfBusca.gridy = 0;
        pnBuscar.add(tfBusca, gbc_tfBusca);
        tfBusca.setColumns(10);

        Component horizontalStrut2 = Box.createHorizontalStrut(20);
        GridBagConstraints gbc_horizontalStrut2 = new GridBagConstraints();
        gbc_horizontalStrut2.insets = new Insets(0, 0, 5, 5);
        gbc_horizontalStrut2.gridx = 2;
        gbc_horizontalStrut2.gridy = 0;
        pnBuscar.add(horizontalStrut2, gbc_horizontalStrut2);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(63, 30));
        btnBuscar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //busca com chave
                realizaBusca();
            }
        });
        GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
        gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
        gbc_btnBuscar.gridx = 3;
        gbc_btnBuscar.gridy = 0;
        pnBuscar.add(btnBuscar, gbc_btnBuscar);

        Component horizontalStrut3 = Box.createHorizontalStrut(20);
        GridBagConstraints gbc_horizontalStrut3 = new GridBagConstraints();
        gbc_horizontalStrut3.insets = new Insets(0, 0, 5, 0);
        gbc_horizontalStrut3.gridx = 4;
        gbc_horizontalStrut3.gridy = 0;
        pnBuscar.add(horizontalStrut3, gbc_horizontalStrut3);

        Component verticalStrut6 = Box.createVerticalStrut(20);
        GridBagConstraints gbc_verticalStrut6 = new GridBagConstraints();
        gbc_verticalStrut6.insets = new Insets(0, 0, 0, 5);
        gbc_verticalStrut6.gridx = 0;
        gbc_verticalStrut6.gridy = 1;
        pnBuscar.add(verticalStrut6, gbc_verticalStrut6);

        JPanel pnResultado = new JPanel();
        pnMeio.add(pnResultado, BorderLayout.CENTER);
        pnResultado.setLayout(new BorderLayout(0, 0));

        JPanel pnTituloResultados = new JPanel();
        pnResultado.add(pnTituloResultados, BorderLayout.NORTH);
        GridBagLayout gbl_pnTituloResultados = new GridBagLayout();
        gbl_pnTituloResultados.columnWidths = new int[] { 0, 0, 0 };
        gbl_pnTituloResultados.rowHeights = new int[] { 30, 0 };
        gbl_pnTituloResultados.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_pnTituloResultados.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        pnTituloResultados.setLayout(gbl_pnTituloResultados);

        Component horizontalStrut5 = Box.createHorizontalStrut(20);
        GridBagConstraints gbc_horizontalStrut5 = new GridBagConstraints();
        gbc_horizontalStrut5.insets = new Insets(0, 0, 0, 5);
        gbc_horizontalStrut5.gridx = 0;
        gbc_horizontalStrut5.gridy = 0;
        pnTituloResultados.add(horizontalStrut5, gbc_horizontalStrut5);

        JLabel lblResultadosDaBusca = new JLabel("Resultados da Busca");
        lblResultadosDaBusca.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc_lblResultadosDaBusca = new GridBagConstraints();
        gbc_lblResultadosDaBusca.anchor = GridBagConstraints.WEST;
        gbc_lblResultadosDaBusca.gridx = 1;
        gbc_lblResultadosDaBusca.gridy = 0;
        pnTituloResultados.add(lblResultadosDaBusca, gbc_lblResultadosDaBusca);

        JPanel pnResultados = new JPanel();
        pnResultado.add(pnResultados, BorderLayout.CENTER);
        GridBagLayout gbl_pnResultados = new GridBagLayout();
        gbl_pnResultados.columnWidths = new int[] { 20, 1, 20, 0 };
        gbl_pnResultados.rowHeights = new int[] { 40, 0, 5, 0 };
        gbl_pnResultados.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_pnResultados.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        pnResultados.setLayout(gbl_pnResultados);

        JPanel pnOrdenacao = new JPanel();
        GridBagConstraints gbc_pnOrdenacao = new GridBagConstraints();
        gbc_pnOrdenacao.anchor = GridBagConstraints.SOUTHEAST;
        gbc_pnOrdenacao.insets = new Insets(0, 0, 5, 5);
        gbc_pnOrdenacao.gridx = 1;
        gbc_pnOrdenacao.gridy = 0;
        pnResultados.add(pnOrdenacao, gbc_pnOrdenacao);

        JLabel lblOrdenarPor = new JLabel("Ordenar por:");
        pnOrdenacao.add(lblOrdenarPor);

        cbOrdenacao = new JComboBox<Ordenacao>();
        cbOrdenacao.setModel(new DefaultComboBoxModel(new String[] { "Pre\u00E7o crescente", "Pre\u00E7o decrescente", "Ordem alfab\u00E9tica pelo t\u00EDtulo e crescente de pre\u00E7o", "Ordem alfab\u00E9tica pelo banda e decrescente de pre\u00E7o" }));
        cbOrdenacao.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent arg0) {
                reordenaResultados();
            }
        });
        pnOrdenacao.add(cbOrdenacao);

        JScrollPane scrollTabelaResultados = new JScrollPane();
        GridBagConstraints gbc_scrollTabelaResultados = new GridBagConstraints();
        gbc_scrollTabelaResultados.insets = new Insets(0, 0, 5, 5);
        gbc_scrollTabelaResultados.fill = GridBagConstraints.BOTH;
        gbc_scrollTabelaResultados.gridx = 1;
        gbc_scrollTabelaResultados.gridy = 1;
        pnResultados.add(scrollTabelaResultados, gbc_scrollTabelaResultados);

        tabelaResultados = new JTable();
        tabelaResultadosModel = new TableModelNonEditable(tabelaResultadosColunas, 0);
        tabelaResultados.setModel(tabelaResultadosModel);
        scrollTabelaResultados.setViewportView(tabelaResultados);

        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setMinimumSize(new Dimension(0, 10));
        verticalStrut.setPreferredSize(new Dimension(0, 10));
        GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
        gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
        gbc_verticalStrut.gridx = 1;
        gbc_verticalStrut.gridy = 2;
        pnResultados.add(verticalStrut, gbc_verticalStrut);

        JPanel pnBotoesResultados = new JPanel();
        pnResultado.add(pnBotoesResultados, BorderLayout.SOUTH);
        GridBagLayout gbl_pnBotoesResultados = new GridBagLayout();
        gbl_pnBotoesResultados.columnWidths = new int[] { 20, 93, 20, 0 };
        gbl_pnBotoesResultados.rowHeights = new int[] { 23, 5, 0 };
        gbl_pnBotoesResultados.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_pnBotoesResultados.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        pnBotoesResultados.setLayout(gbl_pnBotoesResultados);

        btnSalvarBusca = new JButton("Salvar Busca");
        btnSalvarBusca.setPreferredSize(new Dimension(96, 30));
        btnSalvarBusca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                salvaBuscaAtual();
            }

        });
        btnSalvarBusca.setEnabled(false);
        GridBagConstraints gbc_btnSalvarBusca = new GridBagConstraints();
        gbc_btnSalvarBusca.insets = new Insets(0, 0, 5, 5);
        gbc_btnSalvarBusca.anchor = GridBagConstraints.NORTHEAST;
        gbc_btnSalvarBusca.gridx = 1;
        gbc_btnSalvarBusca.gridy = 0;
        pnBotoesResultados.add(btnSalvarBusca, gbc_btnSalvarBusca);

        Component verticalStrut7 = Box.createVerticalStrut(20);
        GridBagConstraints gbc_verticalStrut7 = new GridBagConstraints();
        gbc_verticalStrut7.insets = new Insets(0, 0, 0, 5);
        gbc_verticalStrut7.gridx = 1;
        gbc_verticalStrut7.gridy = 1;
        pnBotoesResultados.add(verticalStrut7, gbc_verticalStrut7);

        JPanel pnDireita = new JPanel();
        pnDireita.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnDireita.setMinimumSize(new Dimension(350, 10));
        pnDireita.setPreferredSize(new Dimension(350, 10));
        pnDireita.setSize(new Dimension(350, 0));
        getContentPane().add(pnDireita, BorderLayout.EAST);
        pnDireita.setLayout(new BorderLayout(0, 0));

        JPanel pnLista = new JPanel();
        pnDireita.add(pnLista, BorderLayout.CENTER);
        GridBagLayout gbl_pnLista = new GridBagLayout();
        gbl_pnLista.columnWidths = new int[] { 25, 53, 25, 0 };
        gbl_pnLista.rowHeights = new int[] { 45, 48, 10, 0 };
        gbl_pnLista.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_pnLista.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        pnLista.setLayout(gbl_pnLista);

        JLabel lblBuscasRecentes = new JLabel("Buscas Recentes");
        GridBagConstraints gbc_lblBuscasRecentes = new GridBagConstraints();
        gbc_lblBuscasRecentes.insets = new Insets(0, 0, 5, 5);
        gbc_lblBuscasRecentes.gridx = 1;
        gbc_lblBuscasRecentes.gridy = 0;
        pnLista.add(lblBuscasRecentes, gbc_lblBuscasRecentes);
        lblBuscasRecentes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblBuscasRecentes.setHorizontalTextPosition(SwingConstants.LEFT);

        GridBagConstraints gbc_verticalStrut2 = new GridBagConstraints();
        gbc_verticalStrut2.insets = new Insets(0, 0, 5, 0);
        gbc_verticalStrut2.gridx = 2;
        gbc_verticalStrut2.gridy = 0;

        JScrollPane scrollListaBuscas = new JScrollPane();
        GridBagConstraints gbc_scrollListaBuscas = new GridBagConstraints();
        gbc_scrollListaBuscas.insets = new Insets(0, 0, 5, 5);
        gbc_scrollListaBuscas.fill = GridBagConstraints.BOTH;
        gbc_scrollListaBuscas.gridx = 1;
        gbc_scrollListaBuscas.gridy = 1;
        scrollListaBuscas.setBorder(new LineBorder(new Color(0, 0, 0)));
        scrollListaBuscas.setSize(new Dimension(250, 100));
        scrollListaBuscas.setPreferredSize(new Dimension(250, 100));
        scrollListaBuscas.setMinimumSize(new Dimension(250, 100));
        pnLista.add(scrollListaBuscas, gbc_scrollListaBuscas);

        listaBuscas = new JList<Busca>();
        listaBuscas.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!listaBuscas.isSelectionEmpty()) {
                    btnDeletarbusca.setEnabled(true);
                    btnCarregarBusca.setEnabled(true);
                } else {
                    btnDeletarbusca.setEnabled(false);
                    btnCarregarBusca.setEnabled(false);
                }
            }
        });
        listaBuscasModel = new DefaultListModel<Busca>();
        listaBuscas.setModel(listaBuscasModel);
        listaBuscas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaBuscas.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                JList<Busca> list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    carregarBuscaSelecionada(list.getSelectedValue());
                }
            }
        });
        scrollListaBuscas.setViewportView(listaBuscas);

        JPanel pnBotoesBuscas = new JPanel();
        pnDireita.add(pnBotoesBuscas, BorderLayout.SOUTH);
        GridBagLayout gbl_pnBotoesBuscas = new GridBagLayout();
        gbl_pnBotoesBuscas.columnWidths = new int[] { 30, 102, 111, 30, 0 };
        gbl_pnBotoesBuscas.rowHeights = new int[] { 55, 0 };
        gbl_pnBotoesBuscas.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_pnBotoesBuscas.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        pnBotoesBuscas.setLayout(gbl_pnBotoesBuscas);

        btnCarregarBusca = new JButton("Carregar Busca");
        btnCarregarBusca.setPreferredSize(new Dimension(111, 30));
        btnCarregarBusca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Carregar Busca Selecionada
                Busca b = listaBuscas.getSelectedValue();
                carregarBuscaSelecionada(b);
            }

        });

        btnDeletarbusca = new JButton("Deletar Busca");
        btnDeletarbusca.setPreferredSize(new Dimension(102, 30));
        btnDeletarbusca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                //Deletar Busca Selecionada
                Busca b = listaBuscas.getSelectedValue();
                removeBuscaDaLista(b);
            }
        });
        btnDeletarbusca.setEnabled(false);
        GridBagConstraints gbc_btnDeletarbusca = new GridBagConstraints();
        gbc_btnDeletarbusca.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnDeletarbusca.insets = new Insets(0, 0, 0, 5);
        gbc_btnDeletarbusca.gridx = 1;
        gbc_btnDeletarbusca.gridy = 0;
        pnBotoesBuscas.add(btnDeletarbusca, gbc_btnDeletarbusca);
        btnCarregarBusca.setEnabled(false);
        GridBagConstraints gbc_btnCarregarBusca = new GridBagConstraints();
        gbc_btnCarregarBusca.insets = new Insets(0, 0, 0, 5);
        gbc_btnCarregarBusca.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnCarregarBusca.gridx = 2;
        gbc_btnCarregarBusca.gridy = 0;
        pnBotoesBuscas.add(btnCarregarBusca, gbc_btnCarregarBusca);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                //Programa fechando
                metodosAposFinalizacaoPrograma();
            }
        });

        inicializaVariaveis();
    }

    private void inicializaVariaveis() {
        facadePesquisa = new PersquisaPrecosFacade();

        metodosAposInicializacaoPrograma();
    }

    private void reordenaResultados() {
        //Reordenar busca
        int selectedIndex = cbOrdenacao.getSelectedIndex();
        switch (selectedIndex) {
            case 0:
                //Preco crescente
                buscaAtual.ordena(Ordenacao.PRECO_ASC);
                break;
            case 1:
                //Preco decrescente
                buscaAtual.ordena(Ordenacao.PRECO_DESC);
                break;
            case 2:
                //Ordem de titulo e preco crescente
                buscaAtual.ordena(Ordenacao.TITULO_PRECO_CRESC);
                break;
            case 3:
                //Ordem de banda e preco decrescente
                buscaAtual.ordena(Ordenacao.BANDA_PRECO_DESC);
                break;

            default:
                //Preco crescente
                buscaAtual.ordena(Ordenacao.PRECO_ASC);
                break;
        }
        carregaResultadosDaBusca();
    }

    private void realizaBusca() {
        //Reseta a tabela
        resetaTabela();

        if (tfBusca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não é possível realizar uma busca vazia.", "Busca vazia!", JOptionPane.ERROR_MESSAGE);
        } else {
            //Buscas
            buscaAtual = new Busca();
            if (tfBusca.getText().isEmpty()) {
                facadePesquisa.pesquisar("");
                buscaAtual.getCdsDaBusca().addAll(facadePesquisa.getCdsBusca());
            } else {
                String chave = tfBusca.getText();
                buscaAtual.setChaveDeBusca(chave);
                facadePesquisa.pesquisar(chave);
                buscaAtual.getCdsDaBusca().addAll(facadePesquisa.getCdsBusca());
            }

            //Ordena pela ordenacao de preco asc por padrao
            cbOrdenacao.setSelectedIndex(0);
            buscaAtual.ordena(Ordenacao.PRECO_ASC);

            carregaResultadosDaBusca();

            btnSalvarBusca.setEnabled(true);

            if (buscaAtual.getQuantidadeDeCDsEncontrados() == 0) {
                JOptionPane.showMessageDialog(null, "Busca realizada, sem nenhum resultado encontrado.");
            } else {
                JOptionPane.showMessageDialog(null, "Busca realizada com sucesso, foram encontrados " + buscaAtual.getQuantidadeDeCDsEncontrados() + " resultados.");
            }
        }
    }

    private void carregaBuscasAnteriores() {
        //Le o arquivo primeiro
        facadePesquisa.ler();

        //Busca as buscas lidas
        ArrayList<Busca> buscasAnteriores = facadePesquisa.getBuscas();

        //Adiciona na lista de buscas
        for (Busca b : buscasAnteriores) {
            addBuscaNaLista(b);
        }
    }

    private void salvarBuscasAnteriores() {
        //Janela / programa fechando
        //Salvar Buscas 
        ArrayList<Busca> buscas = new ArrayList<Busca>();

        for (int i = 0; i < listaBuscasModel.size(); i++) {
            Busca b = listaBuscasModel.get(i);
            buscas.add(b);
        }

        //Seta as buscas na facade
        facadePesquisa.setBuscas(buscas);

        //Faz a persistencia do arquivo via facade
        facadePesquisa.salvar();
    }

    private void addBuscaNaLista(Busca b) {
        if (b.getChaveDeBusca() == null) {
            JOptionPane.showMessageDialog(null, "Não é possével salvar uma busca sem uma chave definida.", "Busca sem chave!", JOptionPane.ERROR_MESSAGE);
        } else {
            listaBuscasModel.addElement(b);
        }
    }

    private void removeBuscaDaLista(Busca b) {
        listaBuscasModel.removeElement(b);

        JOptionPane.showMessageDialog(null, "Busca removida.");
    }

    private void carregarBuscaSelecionada(Busca b) {
        buscaAtual = b;

        //Seta o texto de busca
        tfBusca.setText(b.getChaveDeBusca());

        cbOrdenacao.setSelectedIndex(Ordenacao.getOrdenacaoID(b.getOrdem()));

        //Insere os CDs na tabela
        carregaResultadosDaBusca();

        //Tabelas carregadas nao podem ser salvas novamente
        btnSalvarBusca.setEnabled(false);

        JOptionPane.showMessageDialog(null, "A busca \"" + b.getChaveDeBusca() + "\" de " + b.getFormatedDtSalva() + " foi carregada.");
    }

    private void resetaTabela() {
        tabelaResultadosModel = new TableModelNonEditable(tabelaResultadosColunas, 0);
        tabelaResultados.setModel(tabelaResultadosModel);
    }

    private void carregaResultadosDaBusca() {
        DecimalFormat dcFormat = new DecimalFormat("R$ #,###.00");

        //Reseta a tabela
        resetaTabela();

        for (CD cd : buscaAtual.getCdsDaBusca()) {
            Object[] o = { cd.getTitulo(), cd.getBanda(), cd.getLoja(), dcFormat.format(cd.getPreco()) };
            tabelaResultadosModel.addRow(o);
        }
    }

    private void salvaBuscaAtual() {
        //Apenas adiciona a Busca atual na lista de buscas anteriores
        //A persistencia da busca sera feita quando o software fechar
        buscaAtual.setDtSalvo(new Date());
        buscaAtual.setOrdem(Ordenacao.getOrdenacao(cbOrdenacao.getSelectedIndex()));
        addBuscaNaLista(buscaAtual);
        
        //Desabilita o botao para nao salvar mais de uma vez a mesma lista
        btnSalvarBusca.setEnabled(false);
    }

    private void metodosAposInicializacaoPrograma() {
        //Conectar aos servidores
        facadePesquisa.abreConexoes();

        //Carregar busca anterior
        carregaBuscasAnteriores();
    }

    private void metodosAposFinalizacaoPrograma() {
        //Desconectar dos servidores
        facadePesquisa.fechaConexoes();

        //Salvar buscas anteriores
        salvarBuscasAnteriores();
    }
}
