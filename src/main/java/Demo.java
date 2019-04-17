
import java.util.List;
import java.util.HashMap;

import com.google.gson.Gson;

public class Demo {

    public static void main(String[] args) {

        Gson gson = new Gson();
        String jsonString;  // we'll store results in this

        // serialize common things
        System.out.println( "serialize int: " + gson.toJson(1) );
        String[] sArray = {"wa", "hoo", "wa"};
        System.out.println( "serialize sArray: " + gson.toJson(sArray) );

        // serialize Collections objects
        List<String> slist = List.of("hi", "there", "tom");
        System.out.println( "\nserialized List<String>: " + gson.toJson(slist) );

        HashMap<String,Integer> lengthMap = new HashMap<>();
        lengthMap.put("a", 1);
        lengthMap.put("hello", 5);
        System.out.println( "serialized Map: " + gson.toJson(lengthMap) );

        HashMap<Integer,List<String>> lengthToStrings = new HashMap<>();
        lengthToStrings.put(1, List.of("a", "i"));
        lengthToStrings.put(2, List.of("to", "by", "it"));
        lengthToStrings.put(4, List.of("four", "five", "game"));
        jsonString =  gson.toJson(lengthToStrings);
        System.out.println( "serialized Map<Integer,List<String>>: " + jsonString );

        HashMap<Integer,List<String>> newMap = gson.fromJson(jsonString, lengthToStrings.getClass());
        System.out.println("deserialized Map<Integer,List<String>>:" + newMap);

        // serialize and deserialize our own class Course
        Course c1 = new Course("cs3240", "Adv SW Dev", List.of("sherriff", "horton"), 120);
        c1.setSecretValue(5);  // note what happens to this transient field
        System.out.println( "\nOriginal Course object: " + c1 );


        jsonString =  gson.toJson(c1);
        System.out.println( "serialized Course object: " + jsonString );

        Course c2 = gson.fromJson(jsonString, Course.class);
        System.out.println( "deserialized Course object: " + c2 );

    }
}
