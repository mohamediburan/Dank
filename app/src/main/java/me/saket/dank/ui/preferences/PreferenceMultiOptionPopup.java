package me.saket.dank.ui.preferences;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;

import com.f2prateek.rx.preferences2.Preference;
import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;

import me.saket.dank.utils.NestedOptionsPopupMenu;
import me.saket.dank.utils.Optional;

public class PreferenceMultiOptionPopup<T> extends NestedOptionsPopupMenu {

  private Preference<T> preference;
  private List<Option<T>> options;

  public static <T> Builder<T> builder(Preference<T> preference) {
    return new Builder<>(preference);
  }

  public PreferenceMultiOptionPopup(Context context, Preference<T> preference, List<Option<T>> options) {
    super(context);
    this.preference = preference;
    this.options = options;

    ArrayList<MenuStructure.SingleLineItem> menuOptions = new ArrayList<>();
    for (int index = 0; index < options.size(); index++) {
      Option<T> option = options.get(index);
      menuOptions.add(MenuStructure.SingleLineItem.create(index, context.getString(option.titleRes()), option.iconRes()));
    }
    MenuStructure menuStructure = MenuStructure.create(Optional.empty(), menuOptions);
    createMenuLayout(context, menuStructure);
  }

  @Override
  protected void handleAction(Context c, int index) {
    Option<T> clickedOption = options.get(index);
    preference.set(clickedOption.preferenceValue());
    dismiss();
  }

  @Override
  protected Rect calculateTransitionEpicenter(View anchor, ViewGroup popupDecorView, Point showLocation) {
    return NestedOptionsPopupMenu.transitionEpicenter(anchor, popupDecorView);
  }

  @AutoValue
  public abstract static class Option<T> {

    public abstract T preferenceValue();

    @StringRes
    public abstract int titleRes();

    @DrawableRes
    public abstract int iconRes();

    public static <T> Option<T> create(T preferenceValue, @StringRes int titleRes, @DrawableRes int iconRes) {
      return new AutoValue_PreferenceMultiOptionPopup_Option<>(preferenceValue, titleRes, iconRes);
    }
  }

  public static class Builder<T> {

    private Preference<T> preference;
    private List<Option<T>> options;

    public Builder(Preference<T> preference) {
      this.preference = preference;
    }

    public Builder<T> addOption(T preferenceValue, @StringRes int titleRes, @DrawableRes int iconRes) {
      if (options == null) {
        options = new ArrayList<>();
      }
      options.add(Option.create(preferenceValue, titleRes, iconRes));
      return this;
    }

    public PreferenceMultiOptionPopup<T> build(Context context) {
      return new PreferenceMultiOptionPopup<>(context, preference, options);
    }
  }
}
