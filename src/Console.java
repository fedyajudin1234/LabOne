import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Console{
    Frame jFrame = getFrame();
    JPanel jPanel = new JPanel();
    int size = 0;
    /*String key;
    String value;
    Double valueX;
    Double valueY;*/
    Hash hash = null;
    Hash hash1 = null;
    ObjectFactory factory = null;
    Vector2D vector2D = new Vector2D();
    HashSerialization hashSerialization = new HashSerialization();
    public Console(){
        jFrame.add(jPanel);
        JLabel jlable = new JLabel("Insert HashMap size: ");
        jlable.setBounds(200,50,30,40);
        jPanel.add(jlable);
        JTextField jText = new JTextField(10);
        jPanel.add(jText);
        jText.setBounds(200,50,100,100);
        jPanel.revalidate();
        Border border = BorderFactory.createEtchedBorder();
        jPanel.setBorder(border);
        JButton jSetButton = new JButton("Add");
        jPanel.add(jSetButton);
        jSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String getValue = jText.getText();
                    size = Integer.parseInt(getValue);
                    if(size > 0 ){
                        MyDialog myDialog = new MyDialog();
                        myDialog.setVisible(true);
                        myDialog.setBounds(500,500,500,450);
                    }else{
                        System.out.println("Incorrect value");
                    }
                }catch(Exception exception){
                    System.out.println("Text field should contain integer value");
                }
            }
        });
        jPanel.revalidate();
    }
    class MyDialog extends JDialog{
        public MyDialog(){
            super(jFrame,"Type choosing",true);
            JButton jStringButton = new JButton(new MyActionString());
            jStringButton.setText("String");
            add(jStringButton, BorderLayout.NORTH);
            JButton jVectorButton = new JButton(new MyActionVector());
            jVectorButton.setText("Vector");
            add(jVectorButton, BorderLayout.SOUTH);
            setBounds(550,400,250,100);
        }
    }
    class MyStringDialog extends JDialog{
        public MyStringDialog(){
            super(jFrame,"String Console",true);
            hash = new Hash(size);
            JLabel jNumberLable = new JLabel("Number: ");
            add(jNumberLable,BorderLayout.BEFORE_FIRST_LINE);
            JTextField jNumberTextField = new JTextField(20);
            jNumberTextField.setBounds(50,3,250,20);
            add(jNumberTextField,BorderLayout.NORTH);
            JButton jGenerate = new JButton();
            jGenerate.setText("Generate");
            jGenerate.setBounds(210,30,100,25);
            add(jGenerate,BorderLayout.NORTH);
            jGenerate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long start = System.currentTimeMillis();
                    String numberValue = jNumberTextField.getText();
                    int number = Integer.parseInt(numberValue);
                    StringObjectBuilder stringObjectBuilder = new StringObjectBuilder();
                    for(int i = 0; i < number; i++){
                        hash.put(new IntObjectBuilder().create(),stringObjectBuilder.create());
                        factory = new ObjectFactory(stringObjectBuilder);
                    }
                    factory.getBuilderByName("String");
                    // увеличение хэш-таблицы
                    hash = hash.resizeHash(hash,hash1,number);
                    long finish = System.currentTimeMillis() - start;
                    System.out.println("Время выполнения: " + finish);
                }
            });
            JLabel jIDGetLable = new JLabel("ID: ");
            jIDGetLable.setBounds(1,75,20,15);
            add(jIDGetLable,BorderLayout.NORTH);
            JTextField jIDGetTextField = new JTextField(20);
            jIDGetTextField.setBounds(18,75,300,20);
            add(jIDGetTextField,BorderLayout.NORTH);
            JButton jGetHash = new JButton();
            jGetHash.setText("Hash");
            jGetHash.setBounds(73,100,70,25);
            add(jGetHash,BorderLayout.NORTH);
            jGetHash.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(hash.toString());
                }
            });
            JButton jGetID = new JButton();
            jGetID.setText("GetID");
            jGetID.setBounds(150,100,70,25);
            add(jGetID,BorderLayout.NORTH);
            jGetID.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String keyGetter = jIDGetTextField.getText();
                    int number = Integer.parseInt(keyGetter);
                    System.out.println(hash.get(number));
                }
            });
            JButton jRemove = new JButton();
            jRemove.setText("Remove");
            jRemove.setBounds(227,100,90,25);
            add(jRemove,BorderLayout.NORTH);
            jRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String keyRemover = jIDGetTextField.getText();
                    int number = Integer.parseInt(keyRemover);
                    System.out.println(hash.remove(number));
                }
            });
            JButton jSerialize = new JButton();
            jSerialize.setText("Serialize");
            jSerialize.setBounds(1,130,317,25);
            add(jSerialize,BorderLayout.NORTH);
            jSerialize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        hashSerialization.saveToFile("hashMap.xml",hash,new StringObjectBuilder());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            JButton jDeserialize = new JButton();
            jDeserialize.setText("Deserialize");
            jDeserialize.setBounds(1,160,317,25);
            add(jDeserialize,BorderLayout.NORTH);
            jDeserialize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    HashSerialization hashSerialization = new HashSerialization();
                    try {
                        hashSerialization.loadFromFile("hashMap.xml",hash);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            setBounds(400,400,335,270);
        }

    }
    class MyVectorDialog extends JDialog{
        public MyVectorDialog(){
            super(jFrame,"Vector Console",true);
            hash = new Hash(size);
            JLabel jNumberLable = new JLabel("Number: ");
            add(jNumberLable,BorderLayout.BEFORE_FIRST_LINE);
            JTextField jNumberTextField = new JTextField(20);
            jNumberTextField.setBounds(50,3,250,20);
            add(jNumberTextField,BorderLayout.NORTH);
            JButton jGenerate = new JButton();
            jGenerate.setText("Generate");
            jGenerate.setBounds(210,30,100,25);
            add(jGenerate,BorderLayout.NORTH);
            jGenerate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long start = System.currentTimeMillis();
                    String numberValue = jNumberTextField.getText();
                    int number = Integer.parseInt(numberValue);
                    for(int i = 0; i < number; i++){
                        hash.put(new IntObjectBuilder().create(),vector2D.create());
                        factory = new ObjectFactory(vector2D);
                    }
                    factory.getBuilderByName("Vector2D");
                    hash = hash.resizeHash(hash,hash1,number);
                    long finish = System.currentTimeMillis() - start;
                    System.out.println("Время выполнения: " + finish);
                }
            });
            JLabel jIDGetLable = new JLabel("ID: ");
            jIDGetLable.setBounds(1,75,20,15);
            add(jIDGetLable,BorderLayout.NORTH);
            JTextField jIDGetTextField = new JTextField(20);
            jIDGetTextField.setBounds(18,75,300,20);
            add(jIDGetTextField,BorderLayout.NORTH);
            JButton jGetHash = new JButton();
            jGetHash.setText("Hash");
            jGetHash.setBounds(73,100,70,25);
            add(jGetHash,BorderLayout.NORTH);
            jGetHash.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(hash.toString());
                }
            });
            JButton jGetID = new JButton();
            jGetID.setText("GetID");
            jGetID.setBounds(150,100,70,25);
            add(jGetID,BorderLayout.NORTH);
            jGetID.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String keyGetter = jIDGetTextField.getText();
                    int number = Integer.parseInt(keyGetter);
                    System.out.println(hash.get(number));
                }
            });
            JButton jRemove = new JButton();
            jRemove.setText("Remove");
            jRemove.setBounds(227,100,90,25);
            add(jRemove,BorderLayout.NORTH);
            jRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String keyRemover = jIDGetTextField.getText();
                    int number = Integer.parseInt(keyRemover);
                    System.out.println(hash.remove(number));
                }
            });
            JButton jSerialize = new JButton();
            jSerialize.setText("Serialize");
            jSerialize.setBounds(1,130,317,25);
            add(jSerialize,BorderLayout.NORTH);
            jSerialize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        hashSerialization.saveToFile("hashMap.xml",hash,vector2D);
                        System.out.println(hash.stringLengthCounter);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            JButton jDeserialize = new JButton();
            jDeserialize.setText("Deserialize");
            jDeserialize.setBounds(1,160,317,25);
            add(jDeserialize,BorderLayout.NORTH);
            jDeserialize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    HashSerialization hashSerialization = new HashSerialization();
                    try {
                        hashSerialization.loadFromFile("hashMap.xml",hash);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            setBounds(400,400,335,270);
        }
    }
    class MyActionString extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            MyStringDialog myStringDialog = new MyStringDialog();
            myStringDialog.setVisible(true);
        }
    }
    class MyActionVector extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            MyVectorDialog myVectorDialog = new MyVectorDialog();
            myVectorDialog.setVisible(true);
        }
    }
    JFrame getFrame(){
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        jFrame.setBounds(d.width/3,d.height/3,300,150);
        jFrame.setTitle("L1 Console");
        return jFrame;
    }
}
