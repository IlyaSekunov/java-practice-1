public class Main {
    public static void main(String[] args) {
        Number[][] numbers = {
                { new RealNumber(1), new ComplexNumber(1, 1), new ComplexNumber(2, 1) },
                { new ComplexNumber(-1, -1), new RealNumber(0), new ComplexNumber(4, -2) },
                { new RealNumber(0), new ComplexNumber(0, 1), new ComplexNumber(2, -1) }
        };
        Matrix matrix = new Matrix(numbers);
        System.out.println("matrix + matrix:\n" + matrix.plus(matrix));
        System.out.println("\n\nmatrix - matrix:\n" + matrix.minus(matrix));
        System.out.println("\n\nmatrix * matrix:\n" + matrix.multiply(matrix));
        System.out.println("\n\ntransposed matrix:\n" + matrix.transpose());
        System.out.println("\n\nmatrix's determinant:\n" + matrix.determinant());
    }
}