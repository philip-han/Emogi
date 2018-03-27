package code.challenge.emogi.viewModel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import code.challenge.emogi.model.EmojiLiveData;
import code.challenge.emogi.model.EmojiModel;
import code.challenge.emogi.model.entity.Contents;

/**
 * Created by Philip K. Han on 3/26/18.
 */

public class EmojiViewModel extends ViewModel {

  private static final String TAG = EmojiViewModel.class.getSimpleName();
  private EmojiModel model;
  private EmojiLiveData emojiLiveData;

  public void setModel(EmojiModel model) {
    this.model = model;
  }

  public EmojiLiveData getEmojiLiveData() {
    return emojiLiveData == null ? emojiLiveData = model.getEmojiLiveData() : emojiLiveData;
  }

  public List<String> getUrlsForMessage(String message) {
    Contents contents = emojiLiveData.getValue();
    String[] tokens = message.trim().split("\\s");
    List<String> result = contents.getUrlsForTokens(tokens);
    Log.i(TAG, "getUrlsForMessage: " + Arrays.toString(tokens));
    Log.i(TAG, "getUrlsForMessage: " + Arrays.toString(result.toArray()));
    return result;
  }

}
