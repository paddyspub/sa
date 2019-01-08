package shape;

public class Shape {
    private static final int MIN_SHAPE_DIMENSIONS = 5;
    private static final int MAX_SHAPE_DIMENSIONS = 80;
    private static final int DEFAULT_SHAPE_DIMENSIONS = 20;

    public void createShape() throws Exception {
        createShape(DEFAULT_SHAPE_DIMENSIONS);
    }

    public void createShape(int size) throws Exception {
        if (size < MIN_SHAPE_DIMENSIONS) {
            throw new Exception ("Cannot create a shape smaller than 5x5");
        }
        if (size > MAX_SHAPE_DIMENSIONS) {
            throw new Exception ("Cannot create a shape larger than 80x80");
        }
        StringBuilder stringBuilder = new StringBuilder();
        int rows = size;
        while (rows-- > 0) {
            int cols = size;
            while (cols-- > 0) {
                String piece = (rows + cols == size - 1 || rows == cols ? " " : "#") + " ";
                stringBuilder.append(piece);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
