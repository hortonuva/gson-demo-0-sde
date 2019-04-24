
import java.util.List;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import javax.swing.plaf.synth.SynthTextAreaUI;

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

        // Update to class demo: let's try to de-serialize what toJson() creates
        // You might think this would work:
        // List<String> slist2 = gson.fromJson(gson.toJson(slist), slist.getClass());
        // It will for primitive types.  But it won't for collections. Type info gets lost.
        // GSON has a way to do this, but it uses reflection and looks quite mysterious to us.

        // so instead the one line above that's commented out, that uses getClass(), do this:
        Type listType = new TypeToken<List<String>>(){}.getType(); // get object to "store" type info about List<String>
        List<String> slist2 = gson.fromJson(gson.toJson(slist), listType); // use that as 2nd parameter to fromJson()

        System.out.println("deserialized List<String>: " + slist2);
        System.out.println("Should be equals() to original list: " + slist2.equals(slist));


        HashMap<String,Integer> lengthMap = new HashMap<>();
        lengthMap.put("a", 1);
        lengthMap.put("hello", 5);
        System.out.println( "\nserialized Map: " + gson.toJson(lengthMap) );

        HashMap<Integer,List<String>> lengthToStrings = new HashMap<>();
        lengthToStrings.put(1, List.of("a", "i"));
        lengthToStrings.put(2, List.of("to", "by", "it"));
        lengthToStrings.put(4, List.of("four", "five", "game"));
        jsonString =  gson.toJson(lengthToStrings);
        System.out.println( "serialized Map<Integer,List<String>>: " + jsonString );


        // Update to what we demo'd in class!  Again, getClass() with fromJson() won't work.
        HashMap<Integer,List<String>> newMap = gson.fromJson(jsonString, lengthToStrings.getClass());
        System.out.println("\nIncorrect way:  deserialized Map<Integer,List<String>>:" + newMap);
        System.out.println( "are they equals()? " + newMap.equals(lengthToStrings)); // will return false!  ruh-roh, Shaggy!
        System.out.println( "But it looks like deserialized version: " + lengthToStrings ); // they kind of look the same, though

        System.out.println("type stored for newMap.get(\"1\"): " + newMap.get("1")); // wait, wasn't the key supposed to an Integer?
        System.out.println("type stored for lengthToStrings.get(1): " + lengthToStrings.get(1));

        // correct way to do this using TypeToken
        Type type = new TypeToken<HashMap<Integer,List<String>>>(){}.getType();
        newMap = gson.fromJson(jsonString, type);
        System.out.println("\nCorrect way: deserialized Map<Integer,List<String>>:" + newMap);
        System.out.println( "Now should be equals(): " + newMap.equals(lengthToStrings));

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
