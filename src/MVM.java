
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 *
 * @author Guilherme Bacca, Peterson Boni
 */
public class MVM {

	private static TelaExecucao tela;
	public static int botao = 0;
	static int ax = 0, bx = 0, cx = 0, bp = 0, sp = 0, al = 0, ip, ri;
	static int iPosicaoInstrucoes = 0; // para pegar do arraylist a instruÃ§ao
										// que esta sendo executada
	static int nrAcessosMemoria = 0; // acessos em memória

	public static Graphics graphics;
	static int iValorInicialPilha = -1;

	public MVM(TelaExecucao telaExecucao) {
		tela = telaExecucao;
		graphics = new Graphics();
	}

	public static void zeraRegs() {
		ax = 0;
		al = 0;
		bx = 0;
		cx = 0;
		bp = 0;
		sp = 0;
		ip = 0;
		ri = 0;
		iPosicaoInstrucoes = 0;
		nrAcessosMemoria = 0;
	}

	public static void decodificador(short mem[], int programa, int aux, ArrayList<String> arrayInstrucoes) {
		boolean repetir = true;
		ip = ip + aux; // ganha ele mesmo +1 para salvar quando dar step
		aux = 0;

		while (repetir) {

			System.out.println("Valor de IP: " + ip);
			if (iPosicaoInstrucoes >= arrayInstrucoes.size()) { // termina o
																// programa se
																// chegou na
																// ultima
																// instrucao e
																// nao encontrou
																// halt, nao
																// ocorre no
																// Step
				JOptionPane.showMessageDialog(null,
						"O programa nÃ£o possui 'halt' para terminar.\nExecuÃ§Ã£o finalizada");
				repetir = false;
			} else {
				tela.setEdtLinhaExecucao(arrayInstrucoes.get(iPosicaoInstrucoes++));
			}

			if (botao == 1) {
				// "push ip"
				mem[sp] = (short) ip;
				sp--;

				// "push bp"
				mem[sp] = (short) bp;
				sp--;

				// "push ax"
				mem[sp] = (short) ax;
				sp--;

				// "push bx"
				mem[sp] = (short) bx;
				sp--;

				// "push cx"
				mem[sp] = (short) cx;
				sp--;

				ip = mem[0];
				botao = 0;
				tela.appendLog("EXECUTOU INTERRUPÃ‡AO: INT3");
				System.out.println("EXECUTOU INTERRUPCAO: INT3");
			}

			ri = mem[ip];
			int auxIp = 0;
			switch (ri) {
			case 0:// "init ax"
				tela.appendLog(ip + " - Executou init ax");
				ax = 0;
				nrAcessosMemoria++;
				break;

			case 1:// "move ax,bx"
				tela.appendLog(ip + " - Executou move ax,bx");
				ax = bx;
				nrAcessosMemoria++;
				break;
			case 2:// "move ax,cx",
				tela.appendLog(ip + " - Executou move ax,cx");
				ax = cx;
				nrAcessosMemoria++;
				break;

			case 3:// "move bx,ax"
				tela.appendLog(ip + " - Executou move bx,ax");
				bx = ax;
				nrAcessosMemoria++;
				break;

			case 4:// "move cx,ax"
				tela.appendLog(ip + " - Executou move cx,ax");
				cx = ax;
				nrAcessosMemoria++;
				break;

			case 5:// "move ax,[",
				ax = mem[mem[ip + 1]];
				tela.appendLog("Executou move ax,[" + mem[ip + 1] + "]");
				System.out.println("Executou move ax,[" + mem[ip + 1] + "]");
				ip++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // colchetes gera +1 acesso
				break;

			case 6:// "move ax,[bx+"
				ax = mem[bx + mem[ip + 1]];
				tela.appendLog("Executou move ax, [bx+" + mem[ip + 1] + "]");
				System.out.println("Executou move ax, [bx+" + mem[ip + 1] + "]");
				ip++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 7:// "move ax,[bp-"
				ax = mem[bp - mem[ip + 1]];
				tela.appendLog("Executou move ax, [bx-" + mem[ip + 1] + "]");
				System.out.println("Executou move ax, [bx-" + mem[ip + 1] + "]");
				ip++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 8:// "move ax,[bp+"
				if (mem[ip + 1] > 255) {

				}
				ax = mem[bp + mem[ip + 1]];
				tela.appendLog("Executou move ax, [bp+" + mem[ip + 1] + "].");
				System.out.println("Executou move ax, [bp+" + mem[ip + 1] + "].");
				ip++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 9:// "move ["
				mem[mem[ip + 1]] = (short) ax;
				tela.appendLog("Executou move [" + mem[ip + 1] + "],ax.");
				System.out.println("Executou move [" + mem[ip + 1] + "],ax.");
				ip++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 10:// "move [bx+"
				mem[bx + mem[ip + 1]] = (short) ax;
				tela.appendLog("Executou move [bx+" + mem[ip + 1] + "],ax.");
				System.out.println("Executou move [bx+" + mem[ip + 1] + "],ax.");
				ip++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 11:// "move bp,sp"
				tela.appendLog(ip + " - Executou move bp,sp");
				bp = sp;
				nrAcessosMemoria++;
				break;

			case 12:// "move sp,bp"
				tela.appendLog(ip + " - Executou sp,bp");
				sp = bp;
				nrAcessosMemoria++;
				break;

			case 13:// "add ax,bx"
				tela.appendLog(ip + " - Executou add ax,bx");
				ax = ax + bx;
				nrAcessosMemoria++;
				break;

			case 14:// "add ax,cx"
				tela.appendLog(ip + " - Executou add ax,cx");
				ax = ax + cx;
				nrAcessosMemoria++;
				break;

			case 15:// "add bx,cx"
				tela.appendLog(ip + " - Executou add bx,cx");
				bx = bx + cx;
				nrAcessosMemoria++;
				break;

			case 16:// "sub ax,bx"
				tela.appendLog(ip + " - Executou sub ax,bx");
				ax = ax - bx;
				nrAcessosMemoria++;
				break;

			case 17:// "sub ax,cx"
				tela.appendLog(ip + " - Executou sub ax,cx");
				ax = ax - cx;
				nrAcessosMemoria++;
				break;

			case 18:// "sub bx,cx"
				tela.appendLog(ip + " - Executou sub bx,cx");
				bx = bx - cx;
				nrAcessosMemoria++;
				break;

			case 19:// "inc ax"
				tela.appendLog(ip + " - Executou inc ax");
				ax++;
				nrAcessosMemoria++;
				break;

			case 20:// "inc bx"
				tela.appendLog(ip + " - Executou inc bx");
				bx++;
				nrAcessosMemoria++;
				break;

			case 21:// "inc cx"
				tela.appendLog(ip + " - Executou inc cx");
				cx++;
				nrAcessosMemoria++;
				break;

			case 22:// "dec ax"
				tela.appendLog(ip + " - Executou dec ax");
				ax--;
				nrAcessosMemoria++;
				break;

			case 23:// "dec bx"
				tela.appendLog(ip + " - Executou dec bx");
				bx--;
				nrAcessosMemoria++;
				break;

			case 24:// "dec cx"
				tela.appendLog(ip + " - Executou dec cx");
				cx--;
				nrAcessosMemoria++;
				break;

			case 25:// "test ax0,"
				tela.appendLog(ip + " - Executou test ax0," + mem[ip + 1]);
				if (ax == 0) {
					ip = aux + mem[ip + 1] - 1; // -1 para compensar o ip++ no
												// laco
				} else {
					ip++;
				}
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 26:// "jmp "
				ip = aux + mem[ip + 1];
				tela.appendLog("Jmp " + ip + ".");
				System.out.println("jmp " + ip);
				ip--;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 27:// "call"
				tela.appendLog(ip + " - Executou call " + (ip + 2));
				mem[sp] = (short) (ip + 2);
				sp--;
				ip = aux + mem[ip + 1];
				ip--; // para compensar a alteracao de ip
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro call
				break;

			case 28:// "ret"
				tela.appendLog(ip + " - Executou ret");
				sp++;
				ip = mem[sp];
				ip--;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro ret
				break;

			case 29:// "in ax"
				ax = Integer.parseInt(JOptionPane.showInputDialog("ax:"));
				tela.appendLog(ip + " - Executou in ax");
				nrAcessosMemoria++;
				break;

			case 30:// "out ax"
				System.out.println("Saida: AX=" + ax);
				nrAcessosMemoria++;
				break;

			case 31:// "push ax"
				tela.appendLog(ip + " - Executou push ax");
				mem[sp] = (short) ax;
				sp--;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro push
				break;

			case 32:// "push bx"
				tela.appendLog(ip + " - Executou push bx");
				mem[sp] = (short) bx;
				sp--;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro push
				break;

			case 33:// "push cx"
				tela.appendLog(ip + " - Executou push cx");
				mem[sp] = (short) cx;
				sp--;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro push
				break;

			case 34:// "push bp"
				tela.appendLog(ip + " - Executou push bp");
				mem[sp] = (short) bp;
				sp--;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro push
				break;

			case 35:// "pop bp"
				tela.appendLog(ip + " - Executou pop bp");
				sp++;
				bp = mem[sp];
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro pop
				break;

			case 36:// "pop cx"
				tela.appendLog(ip + " - Executou pop cx");
				sp++;
				cx = mem[sp];
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro pop
				break;

			case 37:// "pop bx"
				tela.appendLog(ip + " - Executou pop bx");
				sp++;
				bx = mem[sp];
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro pop
				break;

			case 38:// "pop ax"
				tela.appendLog(ip + " - Executou pop ax");
				sp++;
				if (!(sp <= 0)) {
					ax = mem[sp];
				}
				mem[sp] = 0;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 pro pop
				break;

			case 39:// "nop"
				tela.appendLog(ip + " - Executou nop");
				break;

			case 40: // "halt"
				tela.appendLog(ip + " - Executou halt");
				repetir = false;
				break;

			case 41:// "dec sp"
				tela.appendLog(ip + " - Executou dec sp");
				sp--;
				nrAcessosMemoria++;
				break;

			case 42:// "move [bp-"
				auxIp = ip;
				mem[aux + bp - mem[ip + 1]] = (short) ax;
				ip++;
				tela.appendLog(auxIp + " - Executou [bp-" + (auxIp + 1) + "], ax");
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 43:// "move [bp+"
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 44:// "move ax,{"
				auxIp = ip;
				ax = mem[ip + 1];
				ip++;
				tela.appendLog(auxIp + " - Executou move ax,{" + ax + "}");
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 45:// "test axEqbx,"
				auxIp = ip;
				if (ax == bx) {
					ip = mem[ip + 1] - 1;
					tela.appendLog(auxIp + " - Executou THEN test axEqbx -> ip" + mem[ip + 1] + ".");
					System.out.println(auxIp + " - Executou THEN test axEqbx -> ip" + mem[ip + 1] + ".");
				} else {
					ip++;
					tela.appendLog(auxIp + " - Executou ELSE test axEqbx -> ip" + ip + ".");
					System.out.println(auxIp + " - Executou ELSE test axEqbx -> ip" + ip + ".");
				}
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 46:// "inc sp"
				tela.appendLog(ip + " - Executou inc sp");
				sp++;
				nrAcessosMemoria++;

				break;

			case 47:// "move ax,sp"
				tela.appendLog(ip + " - Executou move ax,sp");
				ax = sp;
				nrAcessosMemoria++;
				break;

			case 48:// "move sp,ax"
				tela.appendLog(ip + " - move sp,ax");
				sp = ax;
				iValorInicialPilha = sp; // salva esse valor para mostrar o
											// pilha a partir dele
				nrAcessosMemoria++;
				break;

			case 49:// "move ax,bp"
				tela.appendLog(ip + " - Executou move ax,bp");
				ax = bp;
				nrAcessosMemoria++;
				break;

			case 50:// "move bp,ax,{"
				tela.appendLog(ip + " - Executou move bp,ax");
				bp = ax;
				nrAcessosMemoria++;
				break;

			case 51:// "iret"
				tela.appendLog(ip + " - Executou iret");
				// "pop cx"

				sp++;

				cx = mem[sp];

				// "pop bx"

				sp++;

				bx = mem[sp];

				// "pop ax"

				sp++;

				ax = mem[sp];

				// "pop bp"

				sp++;

				bp = mem[sp];

				// "ret"

				sp++;

				ip = mem[sp];

				ip--;

				nrAcessosMemoria++;
				nrAcessosMemoria++; // +5 pro iret
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break;

			case 52:// "int"
				tela.appendLog(ip + " - Executou int");
				// "push ip"
				mem[sp] = (short) (ip + 2);
				sp--;

				// "push bp"
				mem[sp] = (short) bp;
				sp--;

				// "push ax"
				mem[sp] = (short) ax;
				sp--;

				// "push bx"
				mem[sp] = (short) bx;
				sp--;

				// "push cx"
				mem[sp] = (short) cx;
				sp--;

				ip = mem[aux + mem[ip + 1]];
				ip--;

				nrAcessosMemoria++;
				nrAcessosMemoria++; // +5 pro int
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++; // +1 do end do device driver
				break;

			case 53:// "sub bx,ax"
				tela.appendLog(ip + " - Executou sub bx,ax");
				bx = bx - ax;
				nrAcessosMemoria++;
				break;
			case 54:
				mem[mem[ip + 1]] = (short) bx;
				tela.appendLog("Executou move [" + mem[ip + 1] + "],bx.");
				System.out.println("Executou move [" + mem[ip + 1] + "],bx.");
				ip++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				nrAcessosMemoria++;
				break; 
			case Short.MAX_VALUE:
				ip++;
				break;
			default: {
				repetir = false;
				tela.appendLog("Saiu.");
				System.out.println("Saiu.");
			}

				if (ip >= mem.length) {
					tela.appendLog("ERRO: a memoria nao pode ser lida.");
					System.out.println("ERRO: a memoria nao pode ser lida.");
					repetir = false;
				}
			}
			int spAux = iValorInicialPilha;
			tela.LimpaPilha();
			if (spAux <= 0) {
				final short shAux = 0;
				tela.PreenchePilha(shAux, shAux);
			} else {
				for (int i = 0; i < 15; i++) {
					if (spAux < 0) {
						break;
					}
					tela.PreenchePilha(spAux, mem[spAux--]);
				}
			}

			tela.setRegistradores(ax, bx, cx, sp, bp, ip, nrAcessosMemoria);

			ip++;
			if (tela.bStop) {
				repetir = false;
				break;
			}
		}

		tela.setTextSaida("AX = " + ax);
		for (int i = 0; i < mem.length; i++) {
			System.out.println("ENDERECO: " + i + " VALOR: " + mem[i]);
		}
	}

	// Path pathArquivo = Paths.get(sArquivo);
	// BufferedReader ler = new BufferedReader(new
	// FileReader(pathArquivo.toFile()));
	// LineNumberReader lnr = new LineNumberReader(new
	// FileReader(pathArquivo.toFile()));
	// lnr.skip(Long.MAX_VALUE);
	// int f = lnr.getLineNumber()+1;
	// if (Files.exists(pathArquivo)){

	public static void codificador(short mem[], /* String sArquivo, */ short shPosicao,
			ArrayList<String> arrayInstrucoes) throws FileNotFoundException, IOException {
		String sConteudo = "";
		short iMem = shPosicao;
		int iPosConteudo = 0;
		boolean twoByte = false;
		for (final String sInstrucao : arrayInstrucoes) {
			// System.out.println(iMem);
			if (iMem + 2 >= mem.length) {
				AvisoLimiteArray();
				break;
			}

			// String sLinha = ler.readLine();
			if (sInstrucao == null) {
				continue;
			}
			sConteudo = sInstrucao.replaceAll(" ", "");

			final int ri = 0;
			if (sConteudo.equals("initax")) {// "init ax"
				mem[iMem++] = 0;
			} else if (sConteudo.equals("moveax,bx")) {// "move ax,bx"
				mem[iMem++] = 1;
			} else if (sConteudo.equals("moveax,cx")) {// "move ax,cx",
				mem[iMem++] = 2;
			} else if (sConteudo.equals("movebx,ax")) {// "move bx,ax"
				mem[iMem++] = 3;
			} else if (sConteudo.equals("movecx,ax")) {// "move cx,ax"
				mem[iMem++] = 4;
			} else if (sConteudo.contains("moveax,[") && !sConteudo.contains("moveax,[b")) {// "move
				twoByte = true; // ax,[",
				mem[iMem++] = 5;
				iPosConteudo = 8;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("moveax,[bx+")) {
				twoByte = true;
				mem[iMem++] = 6;
				iPosConteudo = 10;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("moveax,[bp-")) {
				twoByte = true;
				mem[iMem++] = 7;
				iPosConteudo = 11;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("moveax,[bp+")) {
				mem[iMem++] = 8;
				iPosConteudo = 10;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("move[") && sConteudo.contains("],bx") && !sConteudo.contains("ax")
					&& !sConteudo.contains("bp")) {
				twoByte = true;
				mem[iMem++] = 54;
				iPosConteudo = 5;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("move[") && sConteudo.contains("],ax") && !sConteudo.contains("bx")
				&& !sConteudo.contains("bp")) {
			twoByte = true;
			mem[iMem++] = 9;
			iPosConteudo = 5;
			String sAux = "";
			while (sConteudo.charAt(iPosConteudo) != ']') {
				sAux += sConteudo.charAt(iPosConteudo++);
			}
			mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
		}else if (sConteudo.contains("move[bx+") && sConteudo.contains(",ax")) {
				mem[iMem++] = 10;
				iPosConteudo = 8;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.equals("movebp,sp")) { // "move bp,sp"
				mem[iMem++] = 11;
			} else if (sConteudo.equals("movesp,bp")) { // "move sp,bp"
				mem[iMem++] = 12;
			} else if (sConteudo.equals("addax,bx")) { // "add ax,bx"
				mem[iMem++] = 13;
			} else if (sConteudo.equals("addax,cx")) { // "add ax,cx"
				mem[iMem++] = 14;
			} else if (sConteudo.equals("addbx,cx")) { // "add bx,cx"
				mem[iMem++] = 15;
			} else if (sConteudo.equals("subax,bx")) { // "sub ax,bx"
				mem[iMem++] = 16;
			} else if (sConteudo.equals("subax,cx")) { // "sub ax,cx"
				mem[iMem++] = 17;
			} else if (sConteudo.equals("subbx,cx")) { // "sub bx,cx"
				mem[iMem++] = 18;
			} else if (sConteudo.equals("incax")) { // "inc ax"
				mem[iMem++] = 19;
			} else if (sConteudo.equals("incbx")) { // "inc bx"
				mem[iMem++] = 20;
			} else if (sConteudo.equals("inccx")) { // "inc cx"
				mem[iMem++] = 21;
			} else if (sConteudo.equals("decax")) { // "dec ax"
				mem[iMem++] = 22;
			} else if (sConteudo.equals("decbx")) { // "dec bx"
				mem[iMem++] = 23;
			} else if (sConteudo.equals("deccx")) { // "dec cx"
				mem[iMem++] = 24;
			} else if (sConteudo.contains("testax0,")) {
				twoByte = true;
				mem[iMem++] = 25;
				iPosConteudo = 8;
				final int iPosFinal = sConteudo.length();
				String sAux = "";
				while (iPosConteudo < iPosFinal) {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				System.out.println("AUX " + Short.parseShort(sAux));
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("jmp")) {
				twoByte = true;
				mem[iMem++] = 26;
				iPosConteudo = 3;
				final int iPosFinal = sConteudo.length();
				String sAux = "";
				while (iPosConteudo < iPosFinal) {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("call")) {
				twoByte = true;
				mem[iMem++] = 27;
				iPosConteudo = 4;
				final int iPosFinal = sConteudo.length();
				String sAux = "";
				while (iPosConteudo < iPosFinal) {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.equals("ret")) { // "ret"
				mem[iMem++] = 28;
			} else if (sConteudo.equals("inax")) { // "in ax"
				mem[iMem++] = 29;
			} else if (sConteudo.equals("outax")) { // "out ax"
				mem[iMem++] = 30;
			} else if (sConteudo.equals("pushax")) { // "push ax"
				mem[iMem++] = 31;
			} else if (sConteudo.equals("pushbx")) { // "push bx"
				mem[iMem++] = 32;
			} else if (sConteudo.equals("pushcx")) { // "push cx"
				mem[iMem++] = 33;
			} else if (sConteudo.equals("pushbp")) { // "push bp"
				mem[iMem++] = 34;
			} else if (sConteudo.equals("popbp")) { // "pop bp"
				mem[iMem++] = 35;
			} else if (sConteudo.equals("popcx")) { // "pop cx"
				mem[iMem++] = 36;
			} else if (sConteudo.equals("popbx")) { // "pop bx"
				mem[iMem++] = 37;
			} else if (sConteudo.equals("popax")) { // "pop ax"
				mem[iMem++] = 38;
			} else if (sConteudo.equals("nop")) { // "nop"
				mem[iMem++] = 39;
			} else if (sConteudo.equals("halt")) { // "halt"
				mem[iMem++] = 40;
			} else if (sConteudo.equals("decsp")) { // "dec sp"
				mem[iMem++] = 41;
			} else if (sConteudo.contains("move[bp-") && sConteudo.contains(",ax")) { // "move
				twoByte = true;// [bp-"
				mem[iMem++] = 42;
				iPosConteudo = 8;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("move[bp+") && sConteudo.contains(",ax")) {
				twoByte = true;
				mem[iMem++] = 43;
				iPosConteudo = 8;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != ']') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.contains("moveax,{")) {
				twoByte = true;
				mem[iMem++] = 44;
				iPosConteudo = 8;
				String sAux = "";
				while (sConteudo.charAt(iPosConteudo) != '}') {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = Short.parseShort(sAux);
			} else if (sConteudo.contains("testaxEqbx,")) {
				twoByte = true;
				mem[iMem++] = 45;
				iPosConteudo = 11;
				final int iPosFinal = sConteudo.length();
				String sAux = "";
				while (iPosConteudo < iPosFinal) {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = (short) (Short.parseShort(sAux) + shPosicao);
			} else if (sConteudo.equals("incsp")) { // "inc sp"
				mem[iMem++] = 46;
			} else if (sConteudo.equals("moveax,sp")) { // "move ax,sp"
				mem[iMem++] = 47;
			} else if (sConteudo.equals("movesp,ax")) { // "move sp,ax"
				mem[iMem++] = 48;
			} else if (sConteudo.equals("moveax,bp")) { // "move ax,bp"
				mem[iMem++] = 49;
			} else if (sConteudo.equals("movebp,ax")) { // "move bp,ax"
				mem[iMem++] = 50;
			} else if (sConteudo.equals("iret")) { // "iret"
				mem[iMem++] = 51;
			} else if (sConteudo.contains("int")) {
				twoByte = true;
				mem[iMem++] = 52;
				iPosConteudo = 3;
				final int iPosfinal = sConteudo.length();
				String sAux = "";
				while (iPosConteudo < iPosfinal) {
					sAux += sConteudo.charAt(iPosConteudo++);
				}
				mem[iMem++] = Short.parseShort(sAux);
			} else if (sConteudo.equals("subbx,ax")) {
				mem[iMem++] = 53;
			} else if (sConteudo.equals("")) {
				if (!twoByte) {
					mem[iMem++] = Short.MAX_VALUE;
				} else {
					twoByte = false;
				}

				System.out.println("do nothing");
			} else {
				JOptionPane.showMessageDialog(null, "InstruÃ§Ã£o invÃ¡lida, serÃ¡ finalizada a aÃ§Ã£o.");
				tela.setEdtLinhaExecucao("InstruÃ§Ã£o invÃ¡lida.");
				tela.setTextLog("Encontrou uma instruÃ§Ã£o invÃ¡lida e parou.\n  ---> " + sConteudo);
				tela.setTextSaida("Ax = " + ax);
				tela.bProgramaInvalido = true;
				break;
			}
		}
		for (int i = 0; i < mem.length; i++) {
			System.out.println("ENDERECO: " + i + " VALOR: " + mem[i]);
		}
	}

	private static void AvisoLimiteArray() {
		final MainMVM main = new MainMVM();
		MainMVM.bLimiteArray = true;
		JOptionPane.showMessageDialog(null, "Limite do array atingido, serÃ¡ finalizada a aÃ§Ã£o.");
	}
}
