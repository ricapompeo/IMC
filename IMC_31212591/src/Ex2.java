import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ex2 extends JFrame {

    private String sexo[] = {"Mulher", "Homem"};
    int tipo, foto;
    double altura, peso, imc;
    double imc_homens[] = {20.7, 26.4};
    double imc_mulheres[] = {19.1, 25.8};
    private String string = "";

    private FlowLayout flowLayout = new FlowLayout();
    private GridLayout gridLayout = new GridLayout(4, 2, 10, 1);
    private JPanel gridJPanel = new JPanel();

    private JButton butao = new JButton("Calcular");
    private JButton butao2 = new JButton("Limpar Dados");
    private JLabel Lsexo = new JLabel("Escolha o sexo:");
    private JLabel Laltura = new JLabel("Altura em cm:");
    private JLabel Lpeso = new JLabel("Peso em Kg:");
    private JLabel Lresultado = new JLabel("");
    private JLabel Lfoto = new JLabel("");
    private JTextField Faltura = new JTextField("", 5);
    private JTextField Fpeso = new JTextField("", 5);

    private JComboBox escolha = new JComboBox(sexo);

    private Icon limpar = new ImageIcon(getClass().getResource("blank.gif"));
    private Icon imagemM[] = {new ImageIcon(getClass().getResource("esqueleto.gif")), new ImageIcon(getClass().getResource("normalM.jpg")), new ImageIcon(getClass().getResource("obesa.jpg"))};
    private Icon imagemH[] = {new ImageIcon(getClass().getResource("esqueleto.gif")), new ImageIcon(getClass().getResource("normalH.jpg")), new ImageIcon(getClass().getResource("obeso.jpg"))};

    //construtor de Exsem argumentos
    public Ex2() {
        //título  da janela
        super("Calculo do IMC(índice de massa corporal)");
        //alinhamento do frame com o uso do objeto flowLayout
        super.setLayout(flowLayout);
        //tamanho da janela
        setSize(370, 160);
        //inclusão dos componentes de maximinizar, miniminizar e fechar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //exibe linhas da caixa de seleção ao clicá-la
        escolha.setMaximumRowCount(2);
        //alinha o painel com o objeto gridLayout
        gridJPanel.setLayout(gridLayout);
        //adiciona os componentes
        gridJPanel.add(Lsexo);
        gridJPanel.add(escolha);
        gridJPanel.add(Laltura);
        gridJPanel.add(Faltura);
        gridJPanel.add(Lpeso);
        gridJPanel.add(Fpeso);
        gridJPanel.add(butao);
        gridJPanel.add(butao2);

        //adiciona a janela principal
        super.add(gridJPanel);
        super.add(Lfoto);
        super.add(Lresultado);

        //criação de uma classe interna anônima para butao
        butao.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        switch (escolha.getSelectedIndex()) {
                            /*caso seja a primeira opção que foi selecionada na caixa de seleção
                             configure tipo=0 e vá para o método Calculos*/
                            case 0: {
                                tipo = 0; //mulher
                                Calculos();
                                break;
                            }
                            //caso seja a segunda opção, configura tipo como e vá para o método Calculos.
                            case 1: {
                                tipo = 1; //homem
                                Calculos();
                                break;
                            }
                        }
                    }
                }//Fim da classe interna anônima
        );//fim da chamada para addActionListerner

        //classe interna anônima para butao
        butao2.addActionListener(
                new ActionListener() {
                    //ao clicar no butaode nome limpar chama o método limpar
                    public void actionPerformed(ActionEvent event) {
                        limpar();
                    }
                }//Fim da classe interna anônima
        );//fim da chamada para addActionListerner
    }

    //método que realiza os calculos
    public void calcularIMC(){
        imc = peso/(altura*altura);
    }
    
    public void Calculos() {
        try //tratador de erros com try e catch
        {
            calcularIMC();
            
            if (tipo == 0) {
                tratarIMCMulher();
            } else if (tipo == 1) {
                tratarImcHomem();
            }
            //configure a foto conforme a posição da variável foto
            Lfoto.setIcon(imagemM[foto]);
            //reconfigure o tamanho da tela
            setSize(370, 500);
            //configure a label Lresultado com a variável string
            Lresultado.setText(string);
        } //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem
        catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "No número inválido!\nEx: Use '.' ao invés de ',' para separar as casas decimais.", "ERROR FATAL!!!", JOptionPane.ERROR_MESSAGE);
            //limpe s campos e variáveis
            Fpeso.setText("");
            Faltura.setText("");
            peso = 0;
            altura = 0;
        }
    }

    public void tratarImcHomem() {
        if (imc_homens[0] > imc) {
            string = String.format("CUIDADO!!!Voce estar abaixo do peso! IMC %.2f", imc);
            //configura a posição que será exibido a imagem
            foto = 0;
        } else if ((imc_homens[0] < imc) && (imc < imc_homens[1])) {
            string = String.format("PARABENS!!Voce estar com o peso ideal! IMC %.2f", imc);
            foto = 1;
        } else {
            string = String.format("CUIDADO!!Voce estar obeso! IMC %.2f", imc);
            foto = 2;
        }
    }

    public void tratarIMCMulher() {
        if (imc_mulheres[0] > imc) {
            string = String.format("CUIDADO!!!Voce estar abaixo do peso! IMC %.2f", imc);
            foto = 0;
        } else if ((imc_mulheres[0] < imc) && (imc <= imc_mulheres[1])) {
            string = String.format("PARABENS!!Voce estar com o peso ideal! IMC %.2f", imc);
            foto = 1;
        } else {
            string = String.format("CUIDADO!!Voce estar obesa! IMC %.2f", imc);
            foto = 2;
        }
    }

    //método para limpar os dados da tela e retornar a tela ao seu tamanho original

    private void limpar() {
        Fpeso.setText("");
        Faltura.setText("");
        Lresultado.setText("");
        //substitua a imagem atual por essa
        Lfoto.setIcon(limpar);
        setSize(300, 160);
    }
}//Fim da classe Ex2
