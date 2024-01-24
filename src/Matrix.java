import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private final int height;
    private final int width;
    private final Number[][] matrix;

    public Matrix(Matrix matrix) {
        this.matrix = matrix.matrix;
        this.height = matrix.height;
        this.width = matrix.width;
    }

    public Matrix(Number[][] matrix) {
        this.matrix = matrix;
        this.height = matrix.length;
        this.width = matrix[0].length;
        for (int i = 1; i < matrix.length; ++i) {
            if (matrix[i].length != width) {
                throw new UnsupportedOperationException("Matrix's rows must have the same size");
            }
        }
    }

    public Matrix(int width, int height) {
        this.height = height;
        this.width = width;
        matrix = new Number[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                set(i, j, new RealNumber(0));
            }
        }
    }

    public Number[][] getMatrix() {
        return matrix;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Matrix plus(Matrix that) {
        if (this.width != that.width || this.height != that.height) {
            throw new UnsupportedOperationException("Cannot add matrix with different size");
        }
        Matrix newMatrix = new Matrix(width, height);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                newMatrix.set(i, j, matrix[i][j].plus(that.getValue(i, j)));
            }
        }
        return newMatrix;
    }

    public Matrix minus(Matrix that) {
        if (this.width != that.width || this.height != that.height) {
            throw new UnsupportedOperationException("Cannot add matrix with different size");
        }
        Matrix newMatrix = new Matrix(width, height);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                newMatrix.set(i, j, matrix[i][j].minus(that.getValue(i, j)));
            }
        }
        return newMatrix;
    }

    public Matrix multiply(Matrix that) {
        if (this.width != that.height) {
            throw new UnsupportedOperationException("Cannot add matrix with different size");
        }
        Matrix newMatrix = new Matrix(width, that.getHeight());
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < that.width; ++j) {
                Number result = new RealNumber(0);
                for (int r = 0; r < width; ++r) {
                    result = result.plus(this.matrix[i][r].multiply(that.matrix[r][j]));
                }
                newMatrix.set(i, j, result);
            }
        }
        return newMatrix;
    }

    public Number determinant() {
        if (height != width) {
            throw new UnsupportedOperationException("Determinant is not defined for non quadratic matrix");
        }
        return determinant(matrix);
    }

    private Number determinant(Number[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        Number result = new RealNumber(0);
        for (int k = 0; k < matrix.length; ++k) {
            Number[][] minor = new Number[matrix.length - 1][matrix.length - 1];
            int minorI = 0, minorJ = 0;
            for (int i = 1; i < matrix.length; ++i) {
                for (int j = 0; j < matrix.length; ++j) {
                    if (j != k) {
                        minor[minorI][minorJ++] = matrix[i][j];
                    }
                }
                ++minorI;
                minorJ = 0;
            }
            if (k % 2 == 0) {
                result = result.plus(matrix[0][k].multiply(determinant(minor)));
            } else {
                result = result.minus(matrix[0][k].multiply(determinant(minor)));
            }
        }
        return result;
    }

    public void set(int row, int column, Number value) {
        matrix[row][column] = value;
    }

    public Number getValue(int row, int column) {
        return matrix[row][column];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return height == matrix1.height && width == matrix1.width && Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(height, width);
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Number[] row : matrix) {
            for (Number number : row) {
                stringBuilder.append(number).append(" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
