package com.f2prateek.progressbutton;

import android.app.Activity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class) @Config(manifest = "progressbutton/AndroidManifest.xml")
public class ProgressButtonTest {
  private Activity activity;
  private ProgressButton view;

  @Before
  public void setUp() throws Exception {
    activity = Robolectric.buildActivity(Activity.class).create().start().resume().get();
    view = new ProgressButton(activity);
  }

  @Test
  public void testInit() throws Exception {
    assertThat(view).isNotNull();
  }
}
