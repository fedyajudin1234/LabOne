
import java.util.ArrayList;

public class Hash implements IHash,Cloneable {
    public int size;
    int stringLengthCounter;
    int currValue;
    int totalValue;
    private class Entry{
        private Object key;
        private Object value;
        private Entry next;
        private Entry(Object key, Object value){
            this.key = key;
            this.value = value;
        }
        public Object getKey(){
            return this.key;
        }
        public Object getValue(){
            return this.value;
        }
        public void setValue(Object value){
            this.value = value;
        }
        public String toString(){
            Entry tmp = this;
            StringBuilder sb = new StringBuilder();
            while(tmp != null){
                sb.append(tmp.key + " -> " + tmp.value + "; ");
                tmp = tmp.next;
            }
            return sb.toString();
        }
    }
    private Entry array[];
    public Hash(int size){
        this.size = size;
        array = new Entry[this.size];
    }
    public void put(Object key, Object value){
        int hash = key.hashCode() % size;
        Entry e = array[hash];
        if(e == null){
            array[hash] = new Entry(key, value);
        }else{
            while(e.next != null){
                if(e.getKey() == key){
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }
            if(e.getKey() == key){
                e.setValue(value);
                return;
            }
            e.next = new Entry(key, value);
        }
    }
    public Object get(Object key){
        int hash = key.hashCode() % size;
        Entry e = array[hash];
        if(e == null){
            return null;
        }
        while(e != null){
            if(e.getKey().equals(key)){
                return e.getValue();
            }
            e = e.next;
        }
        return null;
    }
    public Entry remove(Object key){
        int hash = key.hashCode() % size;
        Entry e = array[hash];
        if(e == null){
            return null;
        }
        if(e.getKey().equals(key)){
            array[hash] = e.next;
            e.next = null;
            return e;
        }
        Entry prev = e;
        e = e.next;
        while(e != null){
            if(e.getKey().equals(key)){
                prev = e;
                e = e.next;
                prev.next = e.next;
                e.next = null;
                return e;
            }
        }
        return null;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            if(array[i] != null){
                sb.append("\t" + i + " " + array[i] + "\n");
            }else{
                sb.append(i + " " + "null" + "\n");
            }
        }
        return sb.toString();
    }
    public void forEach(ActionStarter as){
        Entry e = array[0];
        for(int i = 0; i < size;i++){
            e = array[i];
            as.toDo(e);
        }
    }
    void sizeRecorder() {
        int middleValue = 0;
        int generalValue = 0;
        int deviation = 0;
        int D = 0;
        for (int i = 0; i < size; i++) {
            Entry e = array[i];
            Entry entry = e;
            currValue = 0;
            while(entry != null){
                while(entry != null){
                    stringLengthCounter++;
                    break;
                }
                //System.out.println(stringLengthCounter);
                //System.out.println(entry);
                entry = entry.next;
                currValue = stringLengthCounter;
            }
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(currValue);
            generalValue = currValue / size;
            middleValue = currValue - generalValue;
            deviation = middleValue * middleValue;
            D += deviation;
            //System.out.println(D);
            //System.out.println(arrayList);
            stringLengthCounter = 0;
        }
        totalValue = D / size;
        System.out.println("Дисперсия: " + totalValue);
    }
    public Hash clone() throws CloneNotSupportedException {
        return (Hash)super.clone();
    }
    Hash insert(Hash hash){
        for (int i = 0; i < size; i++) {
            Entry e = array[i];
            Entry entry = e;
            while(entry != null){
                while(entry != null){
                    hash.put(entry.key,entry.value);
                    break;
                }
                //hash.put(entry.key,entry.value);
                entry = entry.next;

            }
        }
        return hash;
    }
}
