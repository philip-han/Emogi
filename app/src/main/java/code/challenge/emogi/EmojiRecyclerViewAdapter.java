package code.challenge.emogi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Philip K. Han on 3/26/18.
 */

public class EmojiRecyclerViewAdapter extends RecyclerView.Adapter<EmojiRecyclerViewAdapter.ViewHolder> {

  private List<String> thumbList;
  private Context context;

  public EmojiRecyclerViewAdapter(List<String> thumbList, Context context) {
    this.thumbList = thumbList;
    this.context = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Glide.with(context)
        .load(thumbList.get(position))
        .thumbnail(0.1f)
        .into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return thumbList.size();
  }

  public void setData(List<String> urlsForMessage) {
    thumbList = urlsForMessage;
    notifyDataSetChanged();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public ViewHolder(View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.imageView);
    }

  }

}
