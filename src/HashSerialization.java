import java.io.*;

public class HashSerialization {
    public void saveToFile(String filename, IHash hash, ObjectBuilder objectBuilder) throws IOException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.write("<hashMap>\n");
            hash.forEach(value -> writer.println(objectBuilder.toString(value)));
            writer.write("</hashMap>");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public IHash loadFromFile(String filename, IHash hash) throws IOException, ClassNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            return hash;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
