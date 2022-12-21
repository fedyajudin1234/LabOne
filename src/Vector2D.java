import java.io.*;
import java.util.Random;

public class Vector2D implements ObjectBuilder{
    public double valueX, valueY;
    public Vector2D(){
        valueX = 0;
        valueY = 0;
    }
    public Vector2D(double valueX, double valueY){
        this.valueX = valueX;
        this.valueY = valueY;
    }
    public Vector2D(Vector2D v){
        valueX = v.valueX;
        valueY = v.valueY;
    }
    public void print(){
        System.out.println("X is: " + valueX + " Y is: " + valueY);
    }
    public String toString(){
        String s = "X: " + valueX + " " + "Y: " + valueY;
        return s;
    }

    @Override
    public String typeName() {
        return "Vector2D";
    }

    @Override
    public Object create() {
        int min = 0;
        int max = 100;
        int diff = max - min;
        Random random = new Random();
        int i1 = random.nextInt(diff + 1);
        int i2 = random.nextInt(diff + 1);
        return new Vector2D(i1,i2);
    }

    @Override
    public String toString(Object value) {
        return value.toString().toString();
    }
}
