import java.io.IOException;

public interface IHash{
    void put(Object key, Object value);
    Object get(Object key);
    String toString();
    void forEach(ActionStarter a) throws IOException;
}
