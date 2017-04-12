
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class SalvarPrograma {

    private static File program = new File("..\\program.txt");
    private static String aux;
    private static short aux2;
    private static String tabelaRealocacao;
    public static int botao = 0;
    
    public static File getProgram() {
        return program;
    }

    public static void setProgram(File program) {
        SalvarPrograma.program = program;
    }

    public static String getAux() {
        return aux;
    }

    public static void setAux(String aux) {
        SalvarPrograma.aux = aux;
    }

    public static short getAux2() {
        return aux2;
    }

    public static void setAux2(short aux2) {
        SalvarPrograma.aux2 = aux2;
    }

    public static String getTabelaRealocacao() {
        return tabelaRealocacao;
    }

    public static void setTabelaRealocacao(String tabelaRealocacao) {
        SalvarPrograma.tabelaRealocacao = tabelaRealocacao;
    }

    public static void salvar(int inicio, int fim, short mem[]) throws IOException {
        if (getProgram() == null) {
            setProgram(new File("..\\program.txt"));
            
        }
        FileWriter fw = new FileWriter(getProgram(), false);
        BufferedWriter bw = new BufferedWriter(fw);
        int start = inicio;
        int next = inicio;
        tabelaRealocacao = "";
        while (next <= fim) {
            setAux2(mem[next]);
            //Instrução de 2 bytes com posição de memória
            if (getAux2() == 5
                    || getAux2() == 6
                    || getAux2() == 7
                    || getAux2() == 8
                    || getAux2() == 9
                    || getAux2() == 10
                    || getAux2() == 25
                    || getAux2() == 26
                    || getAux2() == 27
                    || getAux2() == 42
                    || getAux2() == 43
                    || getAux2() == 45
                    || getAux2() == 52) {
                bw.write(getAux2() + "\n");
                bw.newLine();
                next++;
                bw.write(mem[next] + "\n");
                bw.newLine();
                tabelaRealocacao += (next-start) + "\n";
            } else {
                //instruções de 2 bytes com chaves
                if (getAux2() == 44) {
                    bw.write(getAux2() + "\n");
                    bw.newLine();
                    next++;
                    bw.write(mem[next] + "\n");
                    bw.newLine();
                } else {
                    //Instrução de 1 byte
                    bw.write(getAux2() + "\n");
                    bw.newLine();
                }
            }
            next++;
        }
        //Adiciona a tabela de realocação ao final do programa separado por "|"
        bw.newLine();
        bw.write("|\n" + tabelaRealocacao);
        bw.close();
        fw.close();

    }

    public static void ler(short mem[], int posicao) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(getProgram());
        BufferedReader br = new BufferedReader(fr);
        int posicaoIni = posicao;
        boolean terminouPrograma = false;
        while (br.ready()) {
            aux = br.readLine();
            if (aux.equals("")) {
            } else {
                if (aux.equals("|")) {
                    terminouPrograma = true;
                } else {
                    if (!terminouPrograma) {
                        mem[posicao] = Short.parseShort(aux);
                        posicao++;
                    } else {
                        mem[Integer.parseInt(aux)+posicaoIni] += posicaoIni;
                    }
                    
                }
            }
        }
        br.close();
        fr.close();
    }
    
    public static void gravarPrograma(BufferedWriter bw, String instrucao) throws IOException{
        bw.write(instrucao);
        bw.newLine();
    }
    
    public static void salvarPrograma(short mem[], int programa, int aux, boolean bSoIntrucoes) throws IOException{
        int ax = 0, bx = 0, cx = 0, bp = 0, sp = 0, ip, ri;
        boolean repetir = true;
        ip = 0 + aux;
        
        FileWriter fw = new FileWriter(getProgram(), false);
        BufferedWriter bw = new BufferedWriter(fw);
        if(bSoIntrucoes){
            for (int i = 0; i < mem.length; i++) {
                if(mem[i] == -1)
                    continue;
                String instrucao = String.valueOf(mem[i]);
                gravarPrograma(bw, instrucao);
            }
        }
        else{
            while (repetir) {
                ri = mem[ip];
                switch (ri) {
                    case -1:
                        break;

                    case 0:// "init ax"
                        gravarPrograma(bw, "init ax");
                        break;

                    case 1:// "move ax,bx"
                        gravarPrograma(bw, "move ax,bx");
                        break;

                    case 2:// "move ax,cx",
                        gravarPrograma(bw, "move ax,cx");
                        break;

                    case 3:// "move bx,ax"
                        gravarPrograma(bw, "move bx,ax");
                        break;

                    case 4:// "move cx,ax"
                        gravarPrograma(bw, "move cx,ax");
                        break;

                    case 5:// "move ax,[",
                        gravarPrograma(bw, "move ax,["+ mem[ip + 1]+"]");
                        ip++;
                        break;

                    case 6:// "move ax,[bx+"
                        gravarPrograma(bw, "move ax,[bx+"+ mem[ip + 1]+"]");
                        ip++;
                        break;

                    case 7:// "move ax,[bp-"
                        gravarPrograma(bw, "move ax,[bp-"+ mem[ip + 1]+"]");
                        ip ++;
                        break;

                    case 8://"move ax,[bp+"
                        gravarPrograma(bw, "move ax,[bp+"+ mem[ip + 1]+"]");
                        ip++;
                        break;

                    case 9://"move ["
                        gravarPrograma(bw, "move ["+ mem[ip + 1]+"],ax");
                        ip++;
                        break;

                    case 10://"move [bx+"
                        gravarPrograma(bw, "move [bx+"+ mem[ip + 1]+"],ax");
                        ip ++;
                        break;

                    case 11://"move bp,sp"
                        gravarPrograma(bw, "move bp,sp");
                        break;

                    case 12://"move sp,bp"
                        gravarPrograma(bw, "move sp,bp");
                        break;

                    case 13://"add ax,bx"
                        gravarPrograma(bw, "add ax,bx");
                        break;

                    case 14://"add ax,cx"
                        gravarPrograma(bw, "add ax,cx");
                        break;

                    case 15://"add bx,cx"
                        gravarPrograma(bw, "add bx,cx");
                        break;

                    case 16://"sub ax,bx"
                        gravarPrograma(bw, "sub ax,bx");
                        break;

                    case 17://"sub ax,cx"
                        gravarPrograma(bw, "sub ax,cx");
                        break;

                    case 18://"sub bx,cx"
                        gravarPrograma(bw, "sub bx,cx");
                        break;

                    case 19://"inc ax"
                        gravarPrograma(bw, "inc ax");
                        break;

                    case 20://"inc bx"
                        gravarPrograma(bw, "inc bx");
                        break;

                    case 21://"inc cx"
                        gravarPrograma(bw, "inc cx");
                        break;

                    case 22://"dec ax"
                        //ax--;
                        gravarPrograma(bw, "dec ax");
                        break;

                    case 23://"dec bx"
                        gravarPrograma(bw, "dec bx");
                        break;

                    case 24://"dec cx"
                        gravarPrograma(bw, "dec cx");
                        break;

                    case 25://"test ax0,"   
                        gravarPrograma(bw, "test ax0,"+mem[ip+1]);
                        ip++;

                        break;

                    case 26://"jmp "
                        gravarPrograma(bw, "jmp "+mem[ip + 1]);
                        ip++;
                        break;

                    case 27://"call"
                        gravarPrograma(bw, "call "+mem[ip + 1]);
                        ip++; //para compensar a alteracao de ip

                        break;

                    case 28://"ret"
                        gravarPrograma(bw, "ret");
                        break;

                    case 29://"in ax"
                        gravarPrograma(bw, "in ax");
                        break;

                    case 30://"out ax"
                        gravarPrograma(bw, "out ax");
                        break;

                    case 31://"push ax"
                        gravarPrograma(bw, "push ax");
                        break;

                    case 32://"push bx"
                        gravarPrograma(bw, "push bx");
                        break;

                    case 33://"push cx"
                        gravarPrograma(bw, "push cx");
                        break;

                    case 34://"push bp"
                        gravarPrograma(bw, "push bp");
                        break;

                    case 35://"pop bp"
                        gravarPrograma(bw, "pop bp");
                        break;

                    case 36://"pop cx"
                        gravarPrograma(bw, "pop cx");
                        break;

                    case 37://"pop bx"
                        gravarPrograma(bw, "pop bx");
                        break;

                    case 38://           "pop ax"
                        gravarPrograma(bw, "pop ax");
                        break;

                    case 39://"nop"
                        gravarPrograma(bw, "nop");
                        break;

                    case 40: //"halt"
                        gravarPrograma(bw, "halt");
                        repetir = false;
                        break;

                    case 41://"dec sp"
                        gravarPrograma(bw, "dec sp");
                        break;

                    case 42://"move [bp-"
                        gravarPrograma(bw, "move [bp-"+mem[ip + 1]);
                        ip++;
                        break;

                    case 43://"move [bp+ ], ax"
                        gravarPrograma(bw, "move [bp+"+mem[ip + 1]+"],ax");
                        ip++;
                        break;

                    case 44://"move ax,{"
                        gravarPrograma(bw, "move ax,{"+mem[ip + 1]+"}");
                        ip++;
                        break;

                    case 45://"test axEqbx,"
                        gravarPrograma(bw, "test axEqbx");
                        break;

                    case 46://"inc sp"
                        gravarPrograma(bw, "inc sp");
                        break;

                    case 47://"move ax,sp"
                        gravarPrograma(bw, "move ax,sp");
                        break;

                    case 48://"move sp,ax"
                        gravarPrograma(bw, "move sp,ax");
                        break;

                    case 49://"move ax,bp"
                        gravarPrograma(bw, "move ax,bp");
                        break;

                    case 50://"move bp,ax,{"
                        gravarPrograma(bw, "move bp,ax");
                        break;  

                    case 51://"iret"
                        gravarPrograma(bw, "iret");
                        break;

                    case 52://"int"
                        gravarPrograma(bw, "int "+mem[ip + 1]);
                        ip++;
                        break;

                    case 53://"sub bx,ax"
                        gravarPrograma(bw, "sub bx,ax");
                        break;

                    default: {
                        repetir = false;
                        System.out.println("Saiu");
                    }

                    if (ip >= mem.length) {
                        System.out.println("ERRO: a memoria nao pode ser lida");
                        repetir = false;
                    }
                }

                ip++;
            }
        }
        bw.close();
        fw.close();
        System.out.println("Salvo em: "+program.getAbsolutePath());
    }
}
