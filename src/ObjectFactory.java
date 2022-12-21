import java.util.*;

public class ObjectFactory{
    private ArrayList<ObjectBuilder> userTypeList = new ArrayList<>();

    public ObjectFactory(ObjectBuilder objectBuilder) {
        objectRecorder(objectBuilder);
    }
    List objectRecorder(ObjectBuilder objectBuilder){
        List<ObjectBuilder> list = new ArrayList<>();
        userTypeList.add(objectBuilder);
        return list;
    }

    public ObjectBuilder getBuilderByName(String name){
        Iterator<ObjectBuilder> userTypeIterator = userTypeList.iterator();
        while (userTypeIterator.hasNext()) {
            ObjectBuilder objectBuilder = userTypeIterator.next();
            if(objectBuilder.typeName().equals(name)) {
                return objectBuilder;
            }
        }
        return null;
    }
}
