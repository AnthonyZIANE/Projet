package iut.cours1.projet.dummy;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.nfc.Tag;

import iut.cours1.projet.MainActivity;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

//    private static final int COUNT = 25;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

//    private static DummyItem createDummyItem(int position) {
//        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String region;
        public final String annee;
        public final String filename;

        public DummyItem(String id, String content, String filename, String region, String annee) {
            this.id = id;
            this.content = content;
            this.filename = filename;
            this.region = region;
            this.annee = annee;
        }

        @Override
        public String toString() {
            return content;
        }
    }


    public static String jtoString() throws JSONException, IOException {

        String str = new String();
        try {

            BufferedReader br = new BufferedReader(new
                    InputStreamReader(MainActivity.getContext().getAssets().open("fp")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
             str = new String(sb.toString());




        }catch(IOException e){
            e.printStackTrace();
        }
        return str;

    }


    public static void loadPhareJson(){
        try {
            String str = jtoString();
            JSONObject jObjConnection = null;
            jObjConnection = new JSONObject(str);

            JSONObject jsonBix = jObjConnection.getJSONObject("phares");
            JSONArray jsonA = jsonBix.getJSONArray("liste");

            for (int i = 0; i< jsonA.length(); i++){

                JSONObject msg = (JSONObject) jsonA.get(i);
                Log.d(TAG,"coucou"+ msg.getString("construction"));
                addItem(new DummyItem(
                        msg.getString("id"),
                        msg.getString("name"),
                        msg.getString("filename"),
                        msg.getString("region"),
                        msg.getString("construction")

                ));

            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

}
