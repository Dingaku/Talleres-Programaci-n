package homomorfismos;

public class Matriz {
    int m,n;
    int[][] matriz;
    public Matriz(int a, int b){
        setdim(a, b);
    }
    public Matriz(int[][] mat){
        this(mat.length, mat[0].length);
        load(mat);
    }
    private void setdim(int m, int n){
        this.m = m;
        this.n = n;
        this.matriz = new int[this.m][this.n];
    }
    private void load(int[][] mat){
        this.matriz = mat;
    }
    private int fact(int n){
        if (n == 0){
        return 1;
        } else {
            return fact(n-1) * n;
        }
    }
    public int get(int i, int j){
        return matriz[i][j];
    }
    public void set(int i, int j, int x){
        matriz[i][j] = x; 
    }
    public int[][] getM(){
        return this.matriz;
    }
    public void ver(){
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                System.out.print(matriz[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public Matriz transpuesta(){
        Matriz trans = new Matriz(n,m);
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                trans.set(j, i, matriz[i][j]);
            }
        }
        
        return trans;
    }
    public int[] getRow(int i){
        return matriz[i];
    }
    public Matriz setRow(int i, int[] r){
        int[][] respaldo = matriz;
        matriz[i] = r;
        return new Matriz(respaldo);
    }
    public int[] getCol(int i){
        Matriz uwu = new Matriz(matriz);
        Matriz nwn = uwu.transpuesta();
        return nwn.getRow(i);
    }
    public Matriz setCol(int i, int[] c){
        Matriz uwu = new Matriz(matriz);
        Matriz nwn = uwu.transpuesta();
        nwn = nwn.setRow(i, c);
        return nwn.transpuesta();
    }
    // elimina la última fila de una matriz
    public Matriz popR(){
        int[][] b = new int[matriz.length - 1][matriz[0].length];
        for (int i = 0; i < b.length; i++){
            b[i] = matriz[i];
        }
        return new Matriz(b);
    }
    //Elimina la Última columna de una matriz
    public Matriz popC(){
        Matriz uwu = new Matriz(matriz);
        Matriz nwn = uwu.transpuesta();
        return nwn.popR().transpuesta();
    }
    //Añade una fila a una matriz
    public Matriz addR(int[] a){
        int[][] uwu = new int[matriz.length + 1][matriz[0].length];
        for (int i = 0; i < matriz.length; i++){
            uwu[i] = matriz[i];
        }
        uwu[uwu.length - 1] = a;
        return new Matriz(uwu);
    }
    //Añade una columna a una matriz
    public Matriz addC(int[] o_o){
        Matriz uwu = new Matriz(matriz);
        Matriz nwn = uwu.transpuesta();
        return nwn.addR(o_o).transpuesta();
    }
    public Matriz addR(int[] uwu, int a){
        int[][] nwn = new int[matriz.length + 1][matriz[0].length];
        nwn[a]= uwu;
        for(int i = 0; i < a; i++){
            nwn[i]=matriz[i];
        }
        for(int i = a; i < matriz.length; i++){
            nwn[i+1]=matriz[i];
        }
        return new Matriz(nwn);
    }
    public Matriz addC(int[] uwu, int a){
        int[][] nwn = new int[matriz.length + 1][matriz[0].length];
        Matriz mat = new Matriz(nwn).transpuesta();
        return mat.addR(uwu, a).transpuesta();
    }
    /*Añade una columna al final de una matriz y hace que retroceda
    hasta llegar a la posición 0*/
    public Matriz[] recoR(int[] o_o){
        Matriz[] nwn = new Matriz[matriz.length + 1];
        for (int i = 0; i < nwn.length; i++){
            nwn[i] = new Matriz(matriz).addR(o_o, i);
        }
        return nwn;
    }
    public Matriz[] recoC(int[] o_o){
        Matriz[] nwn = new Matriz[matriz[0].length + 1];
        for (int i = 0; i < nwn.length; i++){
            nwn[i] = new Matriz(matriz).transpuesta().addR(o_o, i).transpuesta();
        }
        return nwn;
    }
    public Matriz[] perR(){
        Matriz nwn = new Matriz(matriz);
        Matriz[] p = new Matriz[fact(nwn.getM().length)];
        if(nwn.getM().length == 1){
            p[0] = new Matriz(nwn.getM());
        } else {
            Matriz[] mat = new Matriz[fact(nwn.getM().length)]; 
            Matriz prev = nwn.popR();
            Matriz[] ant = prev.perR();
            int pos = 0;
            for (int i = 0; i < ant.length; i++){
                for (int j = 0; j < ant[i].recoR(nwn.getRow(nwn.getM().length-1)).length; j++){
                    mat[pos] = ant[i].recoR(nwn.getRow(nwn.getM().length-1))[j];
                    pos++;
                }
            }
            p = mat;
        }
        return p;
    }
    public Matriz[] perC(){
        Matriz nwn = new Matriz(matriz);
        Matriz uwu = nwn.transpuesta();
        Matriz[] ret = uwu.perR();
        for (int i = 0; i < ret.length; i++){
            ret[i] = ret[i].transpuesta();
        }
        return ret;
    }
    public Matriz[][] perTotal(){
        Matriz uwu = new Matriz(matriz);
        int sizeC = uwu.perC().length;
        int sizeR = uwu.perC().length;
        Matriz[][] total = new Matriz[sizeR][sizeC];
        Matriz[] nwn = uwu.perC();
        for (int i = 0; i < total.length; i++){
            total[i] = nwn[i].perR();
        }
        return total;
    }
    // Para grafos no dirigidos:
    public int nLoop(){
        int n = 0;
        for (int i = 0; i < matriz.length; i++){
            n = matriz[i][i] + n;
        }
        return n;
    }
    public boolean sim(){
        boolean t = true;
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz.length; j++){
                if (matriz[i][j] != matriz[j][i]){
                    t = false;
                    break;
                }
            }
        }
        return t;
    }
    public Matriz[] autom(){
        Matriz[] arr;
        Matriz base = new Matriz(matriz);
        int tam = 0;
        Matriz[][] p = base.perTotal();
        for (int i = 0; i < p.length; i++){
            for(int j = 0; j < p.length; j++){
                if (base.sim()){
                    if (base.nLoop() == p[i][j].nLoop() && p[i][j].sim()){
                        tam++;
                    }
                }       
            }
        }
        arr = new Matriz[tam];
        int uwu = 0; 
        for (int i = 0; i < p.length; i++){
            for(int j = 0; j < p.length; j++){
                if (base.sim()){
                    if (base.nLoop() == p[i][j].nLoop() && p[i][j].sim()){
                        arr[uwu] = p[i][j];
                        uwu++;
                    }
                }       
            }
        }
        return arr;
    }
    public void seeAut(){
        Matriz m = new Matriz(matriz);
        for (int i =0; i < m.autom().length; i++){
            m.autom()[i].ver();
            System.out.println();
        }
    }
}
