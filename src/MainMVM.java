
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Guilherme Bacca, Peterson Boni
 */
public class MainMVM {
    static boolean bLimiteArray = false;

    public static void main(String[] args) throws IOException {
        new TelaCarregamento().setVisible(true);
        
        //MVMConsole();
    }
    
    public static void MVMConsole() throws IOException{
        short mem[] = new short[1025];
        boolean bSoInstrucoes = false;
        boolean bOpcaoValida = false;
        
        for (int i = 0; i < mem.length; i++) {
            mem[i] = -1;
        }
        
        String sOpcao = "0";
        int opcao = 0;
        int programa = 0;
        int iPosicao = 0;
        
        while(!bOpcaoValida){
            sOpcao = (JOptionPane.showInputDialog("Escolher programa definido - 1\nEscolher arquivo do computador - 2"));
            if(sOpcao == null)
                break;
            else if(sOpcao.equals("1") || sOpcao.equals("2")){
                opcao = Integer.parseInt(sOpcao);
                break;
            }
        }
        
        bOpcaoValida = true;
        if(opcao == 1){ 
            boolean bOpcaoCerta = false;
            while(!bOpcaoCerta){   
                String sPrograma = "0";
                sPrograma = JOptionPane.showInputDialog("Escolha um Programa: ");
                
                if(sPrograma == null || sPrograma.equals("")){
                    bOpcaoValida = false;
                    break;
                }
                else
                    programa = Integer.parseInt(sPrograma);
                
                //Botao.main(args, mem);
                int enderecoDeCarga = 0;

                switch (programa) {
                    case 1:
                        mem[0 + enderecoDeCarga] = 0;
                        mem[1 + enderecoDeCarga] = 44;
                        mem[2 + enderecoDeCarga] = 3;
                        mem[3 + enderecoDeCarga] = 19;
                        mem[4 + enderecoDeCarga] = 9;
                        mem[5 + enderecoDeCarga] = 2;
                        mem[6 + enderecoDeCarga] = 40;
                        break;
                    case 2:
                        mem[0 + enderecoDeCarga] = 0;
                        mem[1 + enderecoDeCarga] = 19;
                        mem[2 + enderecoDeCarga] = 19;
                        mem[3 + enderecoDeCarga] = 19;
                        mem[4 + enderecoDeCarga] = 19;
                        mem[5 + enderecoDeCarga] = 19;
                        mem[6 + enderecoDeCarga] = 40;
                        break;
                    case 3:
                        mem[0 + enderecoDeCarga] = 26;
                        mem[1 + enderecoDeCarga] = 18;
                        mem[10 + enderecoDeCarga] = 0;
                        mem[11 + enderecoDeCarga] = 9;
                        mem[12 + enderecoDeCarga] = 2;
                        mem[13 + enderecoDeCarga] = 9;
                        mem[14 + enderecoDeCarga] = 3;
                        mem[15 + enderecoDeCarga] = 28;
                        mem[18 + enderecoDeCarga] = 0;
                        mem[19 + enderecoDeCarga] = 44;
                        mem[20 + enderecoDeCarga] = 9;
                        mem[21 + enderecoDeCarga] = 48;
                        mem[22 + enderecoDeCarga] = 27;
                        mem[23 + enderecoDeCarga] = 10;
                        mem[24 + enderecoDeCarga] = 27;
                        mem[25 + enderecoDeCarga] = 10;
                        mem[26 + enderecoDeCarga] = 40;     
                        break;
                    case 4:
                        mem[0 + enderecoDeCarga] = 26;
                        mem[1 + enderecoDeCarga] = 18;
                        mem[10 + enderecoDeCarga] = 19;
                        mem[11 + enderecoDeCarga] = 9;
                        mem[12 + enderecoDeCarga] = 2;
                        mem[13 + enderecoDeCarga] = 9;
                        mem[14 + enderecoDeCarga] = 3;
                        mem[15 + enderecoDeCarga] = 28;
                        mem[18 + enderecoDeCarga] = 0;
                        mem[19 + enderecoDeCarga] = 44;
                        mem[20 + enderecoDeCarga] = 9;
                        mem[21 + enderecoDeCarga] = 48;
                        mem[22 + enderecoDeCarga] = 27;
                        mem[23 + enderecoDeCarga] = 10;
                        mem[24 + enderecoDeCarga] = 27;
                        mem[25 + enderecoDeCarga] = 10;
                        mem[26 + enderecoDeCarga] = 40; 
                        break;
                    case 5:
                        mem[0 + enderecoDeCarga] = 26; 
                        mem[1 + enderecoDeCarga] = 18; 
                        mem[8 + enderecoDeCarga] = 34;
                        mem[9 + enderecoDeCarga] = 11;
                        mem[10 + enderecoDeCarga] = 8;
                        mem[11 + enderecoDeCarga] = 3;
                        mem[12 + enderecoDeCarga] = 19;
                        mem[13 + enderecoDeCarga] = 19;
                        mem[14 + enderecoDeCarga] = 43;
                        mem[15 + enderecoDeCarga] = 3;
                        mem[16 + enderecoDeCarga] = 35;
                        mem[17 + enderecoDeCarga] = 28;
                        mem[18 + enderecoDeCarga] = 44;
                        mem[19 + enderecoDeCarga] = 7;
                        mem[20 + enderecoDeCarga] = 48;
                        mem[21 + enderecoDeCarga] = 0;
                        mem[22 + enderecoDeCarga] = 31;
                        mem[23 + enderecoDeCarga] = 27;
                        mem[24 + enderecoDeCarga] = 8;
                        mem[25 + enderecoDeCarga] = 31;
                        mem[26 + enderecoDeCarga] = 27;
                        mem[27 + enderecoDeCarga] = 8;
                        mem[28 + enderecoDeCarga] = 40;
                        break;
                    case 6:
                        mem[10 + enderecoDeCarga] = 0;
                        mem[11 + enderecoDeCarga] = 26;
                        mem[12 + enderecoDeCarga] = 10;
                        mem[13 + enderecoDeCarga] = 40;
                        break;
                    case 25:
                        mem[0 + enderecoDeCarga] = 26;
                        mem[1 + enderecoDeCarga] = 2;
                        mem[2 + enderecoDeCarga] = 40;
                        break;   
                        

                    default:
                        JOptionPane.showMessageDialog(null, "Programa inválido!");
                        programa = 0;
                        break;
                }
                if(programa != 0)
                    bOpcaoCerta = true;
            }
        }
        else if(opcao == 2){
            boolean bEscolheu = false;
            while(!bEscolheu){
                JFileChooser chooserArquivo = new JFileChooser();
                int iEscolha = chooserArquivo.showOpenDialog(chooserArquivo);
                if(iEscolha == 1){
                    int iContinuar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a ação?\nClique em 'não' para continuar a escolher.");
                    if(iContinuar == 1 || iContinuar == -1)
                        continue;
                    else
                        return;
                }
                else
                    bEscolheu = true;
            
                String sArquivo = chooserArquivo.getSelectedFile().getAbsolutePath();
                sOpcao = "";
                
                while(sOpcao.equals("")){
                    sOpcao = JOptionPane.showInputDialog(null, "Informe a posição da memória a iniciar o programa,\nVerifique se o número de "
                        + "bytes do programa não\nultrapassa o tamanho da memória:", "Atenção",1);
                    if(sOpcao == null || sOpcao.equals(""))
                        break;
                    else
                        iPosicao = Integer.parseInt(sOpcao);
                }

                short shPosicao = (short)iPosicao;
                //MVM.codificador(mem, sArquivo, shPosicao, null);
                bSoInstrucoes = true;
            }
        }
        else{
            bOpcaoValida = false;
        }

        opcao = 0;
        SalvarPrograma prog = null;
        if(!bLimiteArray && bOpcaoValida){
            sOpcao = JOptionPane.showInputDialog("Escolha uma opção: \n(1) exibir no console\n(2) salvar programa\n(outro) sair");
            if(sOpcao == null || sOpcao.isEmpty())
                System.out.println("Saiu");
            else{
                opcao = Integer.parseInt(sOpcao);
                if(opcao == 1){
                    System.out.println("");
                    for(int i = 0; i < mem.length; ++i){
                        if(mem[i] == -1)
                            continue;
                        System.out.println(mem[i]);
                    }

                    MVM.decodificador(mem, programa, iPosicao, null);
                }
                else if(opcao == 2){
                    prog.salvarPrograma(mem, programa, 0, bSoInstrucoes);
                }
                else{
                    System.out.println("Saiu");
                }
            }
        }
        else{
            System.out.println("Programa encerrado");
        }     
    }
}