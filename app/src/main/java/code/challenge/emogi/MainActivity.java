package code.challenge.emogi;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import code.challenge.emogi.model.EmojiLiveData;
import code.challenge.emogi.model.EmojiModel;
import code.challenge.emogi.viewModel.EmojiViewModel;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  private EmojiViewModel viewModel;
  private EditText message;
  private RecyclerView emojis;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    viewModel = ViewModelProviders.of(this).get(EmojiViewModel.class);
    viewModel.setModel(new EmojiModel(getApplication()));
    EmojiLiveData emojiLiveData = viewModel.getEmojiLiveData();
    emojiLiveData.observe(this, contents -> {
      findWidgets();
      emojis.setLayoutManager(new GridLayoutManager(MainActivity.this, 7));
      emojis.setAdapter(new EmojiRecyclerViewAdapter(viewModel.getUrlsForMessage(message.getText().toString()), MainActivity.this));
    });
  }

  private void findWidgets() {
    emojis = findViewById(R.id.emojis);
    message = findViewById(R.id.message);
    message.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        EmojiRecyclerViewAdapter adapter = ((EmojiRecyclerViewAdapter) emojis.getAdapter());
        adapter.setData(viewModel.getUrlsForMessage(charSequence.toString()));
      }

      @Override
      public void afterTextChanged(Editable editable) {
      }
    });
  }
}
