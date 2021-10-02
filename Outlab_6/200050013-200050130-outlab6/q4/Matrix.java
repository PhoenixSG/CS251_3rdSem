public class Matrix {
    protected float arr[][];
    protected int rows;
    protected int columns;



    
    public Matrix(int n, float v) {
        arr = new float[n][n];
        rows = n;
        columns = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = v;
            }
        }
    }

    public Matrix(int n, int m, float v) {
        arr = new float[n][m];
        rows = n;
        columns = m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = v;
            }
        }
    }

    public Matrix(int n, int m) {
        arr = new float[n][m];
        rows = n;
        columns = m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = 0;
            }
        }
    }

    public Matrix(int n) {
        arr = new float[n][n];
        rows = n;
        columns = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = 0;
            }
        }
    }

    public int getrows() {
        return rows;
    }

    public int getcols() {
        return columns;
    }

    public float getelem(int r, int c) {
        if (r < rows && c < columns) {
            return arr[r][c];
        } else {
            System.out.println("Index out of bound");
            return -100;
        }
    }

    public void setelem(int r, int c, float v) {
        if (r < rows && c < columns) {
            arr[r][c] = v;
        } else {
            System.out.println("Index out of bound");
        }
    }

    public void printmatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j]+ " ");
            }
            System.out.print("\n");
        }
    }

    public Matrix add(Matrix x) {
        if(rows == x.getrows() && columns == x.getcols()){
            Matrix sum = new Matrix(rows, columns);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    sum.setelem(i, j, getelem(i, j)+x.getelem(i, j));
                }
            }
            return sum;
        }
        else{
            System.out.println("Matrices cannot be added");
            Matrix sum = new Matrix(1);
            return sum;
        }
    }

    public Matrix matmul(Matrix x) {
        if(columns == x.getrows()){
            Matrix mult = new Matrix(rows, x.getcols());
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < x.getcols(); j++) {
                    for (int j2 = 0; j2 < columns; j2++) {
                        mult.setelem(i, j, mult.getelem(i, j)+getelem(i, j2)*x.getelem(j2, j));
                    }
                }
            }
            return mult;
        }
        else{
            System.out.println("Matrices cannot be multiplied");
            Matrix sum = new Matrix(1);
            return sum;
        }
    }
    public void scalarmul(int k){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                setelem(i, j, getelem(i, j)*k);
            }
        }
    }
}
