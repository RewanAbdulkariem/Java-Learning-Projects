public class Circle extends Shape implements Drawable{
    private double radius;

    public Circle(double radius) {
        setRadius(radius);
    }

    public void setRadius(double radius){
        if (radius > 0){
            this.radius = radius;
        }
    }
    public double getRadius(){
        return radius;
    }

    @Override
    public void draw() {
        System.out.println("--- Circle ---");
        for (double y = -radius; y <= radius; y++) {
            for (double x = -radius; x <= radius; x+=0.5) {
                double distanceSquared = x * x + y * y;
                double radiusSquared = radius * radius;

                if (Math.abs(distanceSquared - radiusSquared) < 0.5){
                    System.out.print("*");
                } else
                    System.out.print("  ");
                }
            System.out.println();
        }
        System.out.println("--- ------ ---");
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}
