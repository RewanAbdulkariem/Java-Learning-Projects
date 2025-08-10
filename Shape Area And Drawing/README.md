# Geometric Shapes (Java OOP)
This Java project demonstrates fundamental Object-Oriented Programming concepts by modeling geometric shapes that can calculate their area and draw themselves using ASCII art.

## 📌 What This Project Does
Defines an `abstract class Shape` with an `abstract method getArea()` to calculate the shape's `area`.

Defines an `interface Drawable` with a method `draw()` that prints the shape using stars (*).

### Implements two shapes:

**Rectangle**: Has `width` and `height`, calculates `area` as width × height, and `draws itself` as a rectangle of stars.

**Circle**: Has a `radius`, calculates `area` using π × radius², and `draws an approximate circle` in ASCII.