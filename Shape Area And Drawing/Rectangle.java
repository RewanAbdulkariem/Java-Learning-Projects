public class Rectangle extends Shape implements Drawable{
    private double width;
    private double height;

    public Rectangle(double width, double height){
        setHeight(height);
        setWidth(width);
    }

    void setWidth(double width){
        if (width > 0)
            this.width = width;
    }
    double getWidth(){
        return width;
    }

    void setHeight(double height){
        if (height > 0)
            this.height = height;
    }
    double getHeight(){
        return height;
    }

    @Override
    double getArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("--- Rectangle ---");
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if (i == 1 || i == height || j == 1 || j == width){
                    System.out.print("# ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println("--- --------- ---");

    }
}
