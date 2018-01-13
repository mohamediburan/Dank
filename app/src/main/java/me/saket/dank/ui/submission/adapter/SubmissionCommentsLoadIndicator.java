package me.saket.dank.ui.submission.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.auto.value.AutoValue;

import me.saket.dank.R;

public interface SubmissionCommentsLoadIndicator {

  @AutoValue
  abstract class UiModel implements SubmissionScreenUiModel {
    @Override
    public long adapterId() {
      return SubmissionCommentsAdapter.ID_COMMENTS_LOAD_INDIDACTOR;
    }

    @Override
    public SubmissionCommentRowType type() {
      return SubmissionCommentRowType.COMMENTS_LOAD_INDICATOR;
    }

    public static UiModel create() {
      return new AutoValue_SubmissionCommentsLoadIndicator_UiModel();
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    public static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new ViewHolder(inflater.inflate(R.layout.list_item_submission_comments_load_progress, parent, false));
    }

    public ViewHolder(View itemView) {
      super(itemView);
    }

    public void bind(UiModel model) {
      // Nothing to do here.
    }
  }
}