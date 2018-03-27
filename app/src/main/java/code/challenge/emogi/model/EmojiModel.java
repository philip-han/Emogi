package code.challenge.emogi.model;

import android.app.Application;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Philip K. Han on 3/26/18.
 */

public class EmojiModel {

  private Application application;

  public EmojiModel(Application application) {
    this.application = application;
  }

  public EmojiLiveData getEmojiLiveData() {
    return new EmojiLiveData(this);
  }

  JSONObject loadContents() {
    try (InputStream inputStream = application.getResources().getAssets().open("contents.json")) {
      return processInput(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
      return new JSONObject();
    }
  }

  private JSONObject processInput(InputStream inputStream) {
    Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
    try {
      return new JSONObject(scanner.hasNext() ? scanner.next() : "");
    } catch (JSONException e) {
      e.printStackTrace();
      return new JSONObject();
    }
  }

}
