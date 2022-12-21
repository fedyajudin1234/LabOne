import java.util.Random;

public class IntObjectBuilder implements ObjectBuilder{
    @Override
    public String typeName() {
        return "IntObjectBuilder";
    }

    @Override
    public Object create() {
        int min = 0;
        int max = 32000;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        return i;
    }

    @Override
    public String toString(Object value) {
        return value.toString();
    }
}
