public class Main {
    public static void main(String[] args){
        Rectangle r = new Rectangle(10, 5);
        Circle c = new Circle(5);
        Shape[] shapes = {r, c};
        for (Shape s : shapes) {
            if (s instanceof Drawable) {
                ((Drawable) s).draw();
            }
            System.out.println(s.getArea());
        }
    }
}
