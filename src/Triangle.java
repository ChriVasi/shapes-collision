public class Triangle {
    public Triangle() {
    }

    public static void main(String[] args) {
        int row = 6;

        for(int i = 0; i < row; ++i) {
            int j;
            for(j = row - i; j > 1; --j) {
                System.out.print(" ");
            }

            for(j = 0; j <= i; ++j) {
                System.out.print("* ");
            }

            System.out.println();
        }

    }
}
