package com.f2prateek.progressbutton;

import android.app.Activity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
// By default the root of the project (instead of the module root) is being used.
// This requires the path to be set manually.
@Config(manifest = "progressbutton/AndroidManifest.xml")
public class ProgressButtonTest {
  private Activity activity;
  private ProgressButton button;

  @Before
  public void setUp() throws Exception {
    activity = Robolectric.buildActivity(Activity.class).create().start().resume().get();
    button = new ProgressButton(activity);
  }

  @Test
  public void testInit() throws Exception {
    assertThat(button).isNotNull();
  }

  @Test
  public void testPin() throws Exception {
    button.setPinned(false);
    assertThat(button.isChecked()).isEqualTo(button.isPinned()).isFalse();

    button.setPinned(true);
    assertThat(button.isChecked()).isEqualTo(button.isPinned()).isTrue();
  }

  @Test
  public void testToggle() throws Exception {
    // Setup
    button.setPinned(false);
    assertThat(button.isChecked()).isEqualTo(button.isPinned()).isFalse();

    // Toggle twice and verify each time
    button.toggle();
    assertThat(button.isChecked()).isEqualTo(button.isPinned()).isTrue();
    button.toggle();
    assertThat(button.isChecked()).isEqualTo(button.isPinned()).isFalse();
  }

  @Test
  public void testValidProgressValueZero() throws Exception {
    button.setProgress(0);
    assertThat(button.getProgress()).isEqualTo(0);
  }

  @Test
  public void testValidProgressValueMax() throws Exception {
    button.setProgress(100);
    assertThat(button.getProgress()).isEqualTo(100);
  }

  @Test
  public void testInvalidProgressValue() throws Exception {
    Exception exception = null;
    try {
      button.setProgress(101);
    } catch (Exception e) {
      exception = e;
    }

    assertThat(exception).isNotNull()
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Progress (101) must be between 0 and 100");
  }

  @Test
  public void testAnotherInvalidProgressValue() throws Exception {
    Exception exception = null;
    try {
      button.setProgress(-1);
    } catch (Exception e) {
      exception = e;
    }

    assertThat(exception).isNotNull()
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Progress (-1) must be between 0 and 100");
  }
}