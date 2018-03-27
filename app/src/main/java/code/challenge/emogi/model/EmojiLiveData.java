package code.challenge.emogi.model;

import android.arch.lifecycle.LiveData;

import code.challenge.emogi.model.entity.Contents;

/**
 * Created by Philip K. Han on 3/26/18.
 */

public class EmojiLiveData extends LiveData<Contents> {

  private EmojiModel model;

  public EmojiLiveData(EmojiModel model) {
    this.model = model;
    load();
  }

  private void load() {
    new Thread(() -> {
      postValue(new Contents(model.loadContents()));
    }).run();
  }

}
