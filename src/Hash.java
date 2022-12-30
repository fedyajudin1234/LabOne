
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Hash implements IHash,Cloneable {
    public int size;
    int stringLengthCounter;
    int currValue;
    int totalValue;
    int maxValue;
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
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Entry e = array[i];
            currValue = 0;
            while(e != null){
                currValue++;
                e = e.next;
            }
            arrayList.add(currValue);
            middleValue += currValue;
        }
        int min = Collections.min(arrayList);
        int max = Collections.max(arrayList);
        middleValue = middleValue / size;
        totalValue = (max - min) - middleValue;
        maxValue = max;
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Размер таблицы: " + size);
        System.out.println("Минимальное значение: " + min);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Среднее значение: " + middleValue);
        System.out.println("Значение, которое мы берём для увеличения хэш-таблицы((макс - мин) - ср.знач): " + totalValue);
        System.out.println("Для просмотра, по окончании итераций хэш-таблицы, нажмите Hash");
        System.out.println("----------------------------------------------------------------------------------");
    }
    public Hash clone() throws CloneNotSupportedException {
        return (Hash)super.clone();
    }
    Hash insert(Hash hash){
        for (int i = 0; i < size; i++) {
            Entry e = array[i];
            while(e != null){
                hash.put(e.key,e.value);
                e = e.next;

            }
        }
        return hash;
    }
    Hash resizeHash(Hash hash, Hash hash1, int number){
        hash.sizeRecorder();
        if(hash.maxValue > hash.totalValue){
            try {
                hash1 = hash.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            while(hash.maxValue > hash.totalValue){
                size = size * 2;
                hash = new Hash(size);
                hash = hash1.insert(hash);
                hash.sizeRecorder();
            }
        }
        return hash;
    }
}
