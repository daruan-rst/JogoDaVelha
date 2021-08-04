import java.time.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class JogoDaVelha {

    public static void main(String[] args) {
        greeting();
        int move;
        char positionValue;
        String[] player = new String[2];
        player[0] = user(1);
        player[1] = user(2);
        boolean itIsNotTied = true;
        boolean nobodyWonYet = true;
        char[][] places = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };
        int turn = 1;
        table(places[0][0], places[0][1], places[0][2], places[1][0], places[1][1], places[1][2], places[2][0], places[2][1], places[2][2]);
        while (nobodyWonYet && itIsNotTied) {
            if (turn == 1){
                turn =0;
            }
            else{
                turn=1;
            }
            move = itIsYourTurn(player[turn]);
            positionValue = position(move, places);
            decision(turn, move, places, positionValue);
            table(places[0][0], places[0][1], places[0][2], places[1][0], places[1][1], places[1][2], places[2][0], places[2][1], places[2][2]);
            itIsNotTied = isItTied(places);
            nobodyWonYet = didSomebodyWin(places);

        }
        theEnd(player[turn],nobodyWonYet, itIsNotTied );
        

    }


    public static String user(int playerNumber) {
        Scanner input = new Scanner(System.in);
        System.out.printf("%dº Jogador: ", playerNumber);
        String player = input.next();
        return player;
    }

    public static char[][] table(char a11, char a12, char a13, char a21, char a22, char a23, char a31, char a32, char a33) {
        char[][] table = {
                {a11, a12, a13},
                {a21, a22, a23},
                {a31, a32, a33}
        };
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(Character.toString(table[i][j]));
                if (j != 2) {
                    System.out.print("|");
                }
            }
            if (i < 2) {
                System.out.println("\n_|_|_");
            }

        }
        return table;
    }

    public static int itIsYourTurn(String player) {
        Scanner input = new Scanner(System.in);
        int iHearAVoice = ThreadLocalRandom.current().nextInt(1, 5);
        switch (iHearAVoice) {
            case 1:
                System.out.printf("\nAgora é o turno do jogador %s jogar ", player);
                break;
            case 2:
                System.out.printf("\n%s, agora é a sua vez! ", player);
                break;
            case 3:
                System.out.printf("\n%s, sua vez de jogar ", player);
                break;
            case 4:
                System.out.printf("\nO jogador %s deve fazer o seu movimento ", player);
                break;
            default:
                System.out.printf("\nEscolha uma posição, %s ", player);
        }
        int move = input.nextInt();
        while (move > 9 || move < 1) {
            System.out.println("Escolha inválida!\nTente novamente ");
            move = input.nextInt();
        }
        return move;
    }
    public static char position(int move, char[][] table){
        int k;
        int j;
        switch (move) {
            case 1:
                k = 0;
                j = 0;
                break;
            case 2:
                k = 0;
                j = 1;

                break;
            case 3:
                k = 0;
                j = 2;

                break;
            case 4:
                k = 1;
                j = 0;

                break;
            case 5:
                k = 1;
                j = 1;

                break;
            case 6:
                k = 1;
                j = 2;

                break;
            case 7:
                k = 2;
                j = 0;
                break;
            case 8:
                k = 2;
                j = 1;
                break;
            default:
                k = 2;
                j = 2;
                break;
        }
        return table[k][j];
    }

    public static char[][] decision(int i, int move, char[][] table, char positionValue) {
        Scanner input = new Scanner(System.in);
        int j = -1;
        int k = -1;
        char mark;
        if (i==0){
            mark = 'X';
        }else {
            mark = '0';
        }

        while (positionValue == 'X' || positionValue == '0'){
            System.out.println("Essa posição já foi escolhida!\nEscolha outra posição:");
            move = input.nextInt();
            positionValue = position(move,table);
        }
        for (int k2 = 0 ; k2 < 3; k2++){
            for(int j2 = 0 ; j2 < 3 ; j2++) {
                if ( table[k2][j2] == positionValue)
                {
                   k = k2;
                   j = j2;
                    break;
                }
            }
        }
        table[k][j] = mark;
        return table;
    }



    public static boolean didSomebodyWin (char[][] table){
        //returns false if somebody won
        boolean nobodyWonYet = true;
        char[]mark = {'0' , 'X'};
        for (int i= 0 ; i<2 ; i++){
            for (int j = 0 ; j<3 ; j++ ){
                if (table[j][0] == mark[i] && table[j][1] == mark[i] && table[j][2] == mark[i] ){
                    nobodyWonYet = false;
                }else if(table[0][j] == mark[i] && table[1][j] == mark[i] && table[2][j] == mark[i]){
                    nobodyWonYet = false;
                }
                else if ( table[0][0] == mark[i] && table[1][1] == mark[i] && table[2][2] == mark[i]){
                nobodyWonYet = false;
                }
                else if( table[0][2] == mark[i] && table[1][1] == mark[i] && table[2][0] == mark[i]){
                    nobodyWonYet = false;
                }
            }

        }return nobodyWonYet;
    }




    public static boolean isItTied (char[][]table){
        int count = 0;
        for (int k = 0 ; k<3 ; k++ ){
            for (int j = 0 ; j<3 ; j++){
                if (table[k][j] !='X' && table[k][j] !='0' ){
                    count++;
                }

            }

    }
        boolean itIsNotTied = (count > 0);
        return itIsNotTied;
    }
    public static void theEnd(String player, boolean nobodyWonYet, boolean itIsNotTied){
        if (!nobodyWonYet){
            System.out.printf("\nParabéns ao jogador %s por ter vencido!", player);
        }
        else if(!itIsNotTied){
            System.out.println("\nO jogo terminou empatado\nDEU VELHA");
        }
    }


    public static void greeting(){
        System.out.println(" ┬┌─┐┌─┐┌─┐     \n" +" ││ ││ ┬│ │     \n" + "└┘└─┘└─┘└─┘     \n" +"┌┬┐┌─┐          \n" + " ││├─┤          \n" + "─┴┘┴ ┴          \n" + "┬  ┬┌─┐┬  ┬ ┬┌─┐\n" + "└┐┌┘├┤ │  ├─┤├─┤\n" + " └┘ └─┘┴─┘┴ ┴┴ ┴");

        LocalDateTime local = LocalDateTime.now();
        int hourOfDay = local.getHour();
        if (hourOfDay>=12 && hourOfDay<18){
            System.out.println("Boa tarde!");

        }
        else if (hourOfDay>=5 && hourOfDay<12){
            System.out.println("Bom dia!");

        }
        else{
            System.out.println("Boa noite!");

        }
        System.out.println("Este é um jogo para duas pessoas\n O primeiro jogador usará X, o segundo 0\n");

    }
}
