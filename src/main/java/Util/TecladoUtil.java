package Util;

import java.util.Scanner;

public class TecladoUtil {

    private void printfMenu(String mensagem){
        int larguraTotal = 40;
        int espacosAntes = (larguraTotal - mensagem.length()) / 2;
        int espacosDepois = larguraTotal - espacosAntes - mensagem.length();
        String espacosAntesStr = String.format("%" + espacosAntes + "s", "");
        String espacosDepoisStr = String.format("%" + espacosDepois + "s", "");

        System.out.printf("%s%s%s%n", espacosAntesStr, mensagem, espacosDepoisStr);
    }
    public static String lerString(String mensagem) {
        return  inicializaTeclado(mensagem).next();
    }


    public static  Integer lerInteiro(String mensagem) {
        return  inicializaTeclado(mensagem).nextInt();
    }

    private static Scanner inicializaTeclado(String mensagem) {
        TecladoUtil util = new TecladoUtil();
        util.printfMenu(mensagem);
        return new Scanner(System.in);
    }
}
