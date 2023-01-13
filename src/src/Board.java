import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board implements Ilayout, Cloneable {
    private static final int dim = 25;
    private char board[];
    private int zero;

    public Board() { board = new char[dim]; }

    public Board(String str) throws IllegalStateException {
        if (str.length() != 17)
            throw new IllegalStateException("Invalid arg in Board constructor");
        board = new char[dim];
         /* 
        for(int i = 0; i < dim; i++){
            if(i == 3 || i == 4 || i == 8 || i == 9 || i == 15 || i == 16 || i == 20 || i == 21){
                board[i] = ' ';
            }

        }
        */

        String filho1 = sc.nextLine();
        String filho2 = sc.nextLine();
        String[] filhos = pop.onePointCrossover(filho1,filho2);

        for(int i = 0; i < filhos.length; i++){
            System.out.println(filhos[i]);
        }
        board[3] = ' ';
        board[4] = ' ';
        board[8] = ' ';
        board[9] = ' ';
        board[15] = ' ';
        board[16] = ' ';
        board[20] = ' ';
        board[21] = ' '; 
        int si = 0;
        for (int i = 0; i < dim; i++){
            if (board[i] != ' '){
                if (str.charAt(si) == 'E')
                    this.zero = i;
                board[i]= str.charAt(si++);
            }
        }
    }

    public String toString(){
        String a = new String();
        for (int i = 0; i < dim; i++){
            if(i != 0 && i % 5 == 0){
                a += "\n" + String.valueOf(board[i]);
            } else a += String.valueOf(board[i]);
        }
        return a;
    }

    public boolean equals(Object o){
        for (int i = 0; i < dim; i++){
                if (this.board[i] != ((Board) o).board[i])
                    return false;
            }
        return true;
    }

    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(board);
        return result;
    }

    public Board clone(){
        Board clone = new Board();
        for (int i = 0; i < dim; i++) {
            clone.board[i] = this.board[i];
        }
        return clone;
    }

    public Board change(int oldI, int newI){
        Board clone = this.clone(); 
        char temp = clone.board[oldI];
        clone.board[oldI] = clone.board[newI];
        clone.board[newI] = temp;
        clone.zero = newI;
        return clone;
    }

    public boolean ableToPlay(Board goal){

        if(this.board[0] == 'R' && goal.board[0] == 'R'){
            if(this.board[1] == 'R' && goal.board[1] == 'R' && this.board[5] == 'R'&& goal.board[5] == 'R'){
                if(this.board[2] == 'R' && goal.board[2] == 'R' && this.board[10] == 'R'&& goal.board[10] == 'R' && this.board[6] == 'R'&& goal.board[6] == 'R' ){
                    if(this.board[11] == 'R' && goal.board[11] != 'R' && this.board[7] == 'R' && goal.board[7] != 'R')
                        return false;
                } else return false;
            } else return false;
        } else return false;

        if(this.board[24] == 'B' && goal.board[24] == 'B'){
            if(this.board[23] == 'B' && goal.board[23] == 'B' && this.board[19] == 'B'&& goal.board[19] == 'B'){
                if(this.board[22] == 'B' && goal.board[22] == 'B' && this.board[18] == 'B'&& goal.board[18] == 'B' && this.board[14] == 'B'&& goal.board[14] == 'B' ){
                    if(this.board[17] == 'B' && goal.board[17] != 'B' && this.board[13] == 'B' && goal.board[13] != 'B')
                        return false;
                } else return false;
            } else return false;
        } else return false;
        return true;
    }
    
    @Override
    public List<Ilayout> children(){
        List<Ilayout> children = new ArrayList<>();
        // condicoes para verficar se estao out of bounds
        if(this.zero - 5 >= 0){  // verifica se o E pode ir para cima uma unidade
            if(this.board[this.zero - 5] == 'B'){
                children.add(change(this.zero, this.zero - 5));
            } else if(this.zero - 10 >= 0 && this.board[this.zero - 10] == 'B') // verifica se o E pode ir para cima duas unidades
                    children.add(change(this.zero, this.zero - 10));
        }
        if(this.zero + 5 < dim){ // verifica se pode ir para baixo uma unidade
            if(this.board[this.zero + 5] == 'R'){
                children.add(change(this.zero, this.zero + 5));
            } else if(this.zero + 10 < dim && this.board[this.zero + 10] == 'R') // verifica se o E pode ir para baixo duas unidades
                        children.add(change(this.zero, this.zero + 10));
        }
        if(this.zero - 1 >= 0){ // verifica se pode ir para a esquerda uma unidade
            if(this.board[this.zero - 1] == 'B'){
                children.add(change(this.zero, this.zero - 1));
            } else if(this.zero - 2 >= 0 && this.board[this.zero - 2] == 'B') // verifica se o E pode ir para a esquerda duas unidades
                 children.add(change(this.zero, this.zero - 2));
        }
        if(this.zero + 1 < dim) { // verifica se pode ir para a direita uma unidade
            if(this.board[this.zero + 1] == 'R'){
                children.add(change(this.zero, this.zero + 1));
            } else if(this.zero + 2 < dim && this.board[this.zero + 2] == 'R') // verifica se o E pode ir para a direita duas unidades
                 children.add(change(this.zero, this.zero + 2));
        }
        return children;
    }

    @Override
    public double h(Ilayout l){
        double result = 0;
        if(!this.ableToPlay((Board )l)) return 100;
        char[] goal = ((Board) l).board;
        for(int i = 0; i < goal.length; i++){
            if(this.board[i] == ' '){
                i++;
                continue;
            } if(this.board[i] !=  goal[i])
                result++;
        }
        return result;
    }

    @Override
    public boolean isGoal(Ilayout l){ return this.equals(l); }

    @Override
    public double getG() { return 1; }
}