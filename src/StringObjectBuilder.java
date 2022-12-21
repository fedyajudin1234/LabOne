
import java.util.Random;

public class StringObjectBuilder implements ObjectBuilder{
    StringObjectBuilder(){
        create();
    }

    @Override
    public String typeName() {
        return "String";
    }

    @Override
    public String create() {
        int leftBorder = 97;
        int rightBorder = 122;
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < 1; i++){
            int randomBorder = leftBorder + (int)(random.nextFloat() * (rightBorder - leftBorder + 1));
            stringBuffer.append((char)randomBorder);
        }
        return stringBuffer.toString();
    }

    @Override
    public String toString(Object value) {
        return value.toString();
    }
}
