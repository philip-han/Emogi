package code.challenge.emogi.model.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Philip K. Han on 3/26/18.
 */

public class Contents {

  private static final String TAG = Contents.class.getSimpleName();
  private JSONObject jsonObject;
  private Map<String, List<String>> tagContentMap;
  private Map<String, String> contentIdUrlMap;

  public Contents(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
    tagContentMap = new HashMap<>();
    contentIdUrlMap = new HashMap<>();
    process();
  }

  public List<String> getUrlsForTokens(String[] tokens) {
    LinkedHashSet<String> result = new LinkedHashSet<>();
    List<String> contentIdList;
    for (String token : tokens) {
      if (tagContentMap.containsKey(token)) {
        contentIdList = tagContentMap.get(token);
        for (String contentId : contentIdList) {
          result.add(contentIdUrlMap.get(contentId));
        }
      }
    }
    return new ArrayList<>(result);
  }

  private void process() {
    try {
      JSONObject contents = jsonObject.getJSONObject("contents");
      Iterator<String> keys = contents.keys();
      String key;
      JSONObject content;
      while (keys.hasNext()) {
        key = keys.next();
        content = contents.getJSONObject(key);
        addTags(content);
        addAssets(content);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    /*for (String tag : tagContentMap.keySet()) {
      List<String> list = tagContentMap.get(tag);
      Log.i(TAG, String.format("%s %s", tag, Arrays.toString(list.toArray())));
    }

    for (String contentId : contentIdUrlMap.keySet()) {
      Log.i(TAG, "process: " + contentId + " : " + contentIdUrlMap.get(contentId));
    }*/

  }

  private void addTags(JSONObject content) throws JSONException {
    JSONArray tagArray = content.getJSONArray("tags");
    String key;
    for (int i = 0; i < tagArray.length(); i++) {
      key = tagArray.getString(i);
      List<String> list = tagContentMap.containsKey(key) ? tagContentMap.get(key) : new ArrayList<String>();
      list.add(content.getString("content_id"));
      tagContentMap.put(key, list);
    }
  }

  private void addAssets(JSONObject content) throws JSONException {
    JSONArray assetArray = content.getJSONArray("assets");
    String thumb = getThumbUrl(assetArray);
    if (thumb.length() != 0) contentIdUrlMap.put(content.getString("content_id"), thumb);
  }

  private String getThumbUrl(JSONArray assetsArray) throws JSONException {
    for (int i = 0; i < assetsArray.length(); i++) {
      JSONObject jsonObject = assetsArray.getJSONObject(i);
      if (jsonObject.getString("size").toLowerCase().equals("thumb")) { // TODO: 3/26/18 handle NPE
        return jsonObject.getString("url");
      }
    }
    return "";
  }

}
