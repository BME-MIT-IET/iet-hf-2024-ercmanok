package com.mycompany.app;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import java.awt.Frame;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UnitTest extends AssertJSwingJUnitTestCase{

  @Override
  public void onSetUp() {
  }

  @AfterEach
  public void testCleanup(){
    robot().cleanUp();
  }

  @Test
  public void testNewGameWindow(){
    this.setUpRobot();
    application(View.class).start();

    FrameFixture frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
      protected boolean isMatching(Frame frame) {
        return "Sivatagi vízhálózat".equals(frame.getTitle()) && frame.isShowing();
      }
    }).using(robot());
    frame.button(JButtonMatcher.withText("ÚJ JÁTÉK INDÍTÁSA")).requireEnabled().requireEnabled().click();
    frame.button(JButtonMatcher.withText("VISSZA")).click();
    frame.button(JButtonMatcher.withText("ÚJ JÁTÉK INDÍTÁSA")).click();
    
    frame.button(JButtonMatcher.withName("bNewPlumber")).click();
    frame.table("plumberTable").cell("Szerelő 1").enterValue("p1");
    frame.table("plumberTable").cell("Szerelő 2").enterValue("p2");
    frame.table("plumberTable").cell("Szerelő 3").enterValue("p3");

    frame.button(JButtonMatcher.withName("bNewSaboteur")).click();
    frame.table("saboteurTable").cell("Szabotőr 1").enterValue("s1");
    frame.table("saboteurTable").cell("Szabotőr 2").enterValue("s2");
    frame.table("saboteurTable").cell("Szabotőr 3").enterValue("s3");

    frame.button(JButtonMatcher.withName("bNewPlumber")).click();
    frame.table("plumberTable").selectRows(3);
    frame.button(JButtonMatcher.withName("bDeletePlumber")).click();

    frame.button(JButtonMatcher.withName("bNewSaboteur")).click();
    frame.table("saboteurTable").selectRows(3);
    frame.button(JButtonMatcher.withName("bDeleteSaboteur")).click();

    frame.button(JButtonMatcher.withText("JÁTÉK INDÍTÁSA")).click();

    
    frame.label(JLabelMatcher.withName("lNamePlumber")).requireText("p1");
    frame.button(JButtonMatcher.withText("Mozgás")).requireVisible().requireEnabled();
    frame.button(JButtonMatcher.withText("Cső lyukasztása")).requireVisible().requireDisabled();
    frame.button(JButtonMatcher.withText("Cső felvétele")).requireVisible().requireEnabled();
    frame.button(JButtonMatcher.withName("bRedirectPlumber")).requireVisible();
    frame.button(JButtonMatcher.withText("Javítás")).requireVisible().requireDisabled();
    frame.button(JButtonMatcher.withName("bStickyPlumber")).requireVisible().requireDisabled();
    frame.button(JButtonMatcher.withText("Cső lerakása")).requireVisible().requireDisabled();
    frame.button(JButtonMatcher.withName("bPlacePipePlumber")).requireVisible().requireDisabled();

    frame.button(JButtonMatcher.withText("Mozgás")).click();
    frame.button(JButtonMatcher.withText("MÉGSE")).requireVisible().requireEnabled().click();

    frame.button(JButtonMatcher.withText("KÖR VÉGE")).click();
    frame.label(JLabelMatcher.withName("lNamePlumber")).requireText("p2");
    frame.button(JButtonMatcher.withText("KÖR VÉGE")).click();
    frame.label(JLabelMatcher.withName("lNamePlumber")).requireText("p3");
    frame.button(JButtonMatcher.withText("KÖR VÉGE")).click();

    frame.label(JLabelMatcher.withName("lNameSaboteur")).requireText("s1");
    frame.button(JButtonMatcher.withText("Mozgás")).requireVisible().requireEnabled();
    frame.button(JButtonMatcher.withName("bStickySaboteur")).requireVisible().requireDisabled();
    frame.button(JButtonMatcher.withText("Cső lyukasztása")).requireVisible().requireDisabled();
    frame.button(JButtonMatcher.withName("bRedirectSaboteur")).requireVisible();
    frame.button(JButtonMatcher.withName("bSlippery")).requireVisible().requireDisabled();

    frame.button(JButtonMatcher.withText("Mozgás")).click();
    frame.button(JButtonMatcher.withText("MÉGSE")).requireVisible().requireEnabled().click();

    frame.menuItem("mFile").click();
    frame.menuItem("miExitToMenu").click();

    frame.button(JButtonMatcher.withText("JÁTÉK FOLYTATÁSA")).requireVisible().requireEnabled().click();
    frame.label(JLabelMatcher.withName("lNameSaboteur")).requireText("s1");

    frame.button(JButtonMatcher.withText("KÖR VÉGE")).click();
    frame.label(JLabelMatcher.withName("lNameSaboteur")).requireText("s2");
    frame.button(JButtonMatcher.withText("KÖR VÉGE")).click();
    frame.label(JLabelMatcher.withName("lNameSaboteur")).requireText("s3");
    frame.button(JButtonMatcher.withText("KÖR VÉGE")).click();
    frame.label(JLabelMatcher.withName("lRounds")).requireText("2. Kör");

    frame.menuItem("mGame").click();
    frame.menuItem("miSaveGame").click();
    frame.fileChooser("saveFileChooser").fileNameTextBox().enterText("testSave");
    frame.fileChooser("saveFileChooser").approveButton().click();
    frame.menuItem("mFile").click();
    frame.menuItem("miExitToMenu").click();

    frame.button(JButtonMatcher.withText("ÚJ JÁTÉK INDÍTÁSA")).requireEnabled().requireEnabled().click();
    frame.button(JButtonMatcher.withText("JÁTÉK INDÍTÁSA")).click();
    frame.menuItem("mGame").click();
    frame.menuItem("miLoadGame").click();

    frame.fileChooser("loadFileChooser").fileNameTextBox().enterText("testSave");
    frame.fileChooser("loadFileChooser").approveButton().click();
    frame.label(JLabelMatcher.withName("lNamePlumber")).requireText("p1");
    frame.button(JButtonMatcher.withText("KÖR VÉGE")).click();
    frame.label(JLabelMatcher.withName("lNamePlumber")).requireText("p2");
    frame.menuItem("mGame").click();
    frame.menuItem("miSaveGame").click();
    frame.fileChooser("saveFileChooser").fileNameTextBox().enterText("testSave");
    frame.fileChooser("saveFileChooser").approveButton().click();

    frame.menuItem("mFile").click();
    frame.menuItem("miExitToMenu").click();

    frame.button(JButtonMatcher.withText("KORÁBBI JÁTÉK BETÖLTÉSE")).requireEnabled().requireEnabled().click();
    frame.fileChooser("bLoadFileChooser").fileNameTextBox().enterText("testSave");
    frame.fileChooser("bLoadFileChooser").approveButton().click();
    frame.label(JLabelMatcher.withName("lNamePlumber")).requireText("p2");

    frame.button(JButtonMatcher.withText("Cső felvétele")).click();
    frame.button(JButtonMatcher.withText("MÉGSE")).requireVisible().requireEnabled().click();

    frame.menuItem("mFile").click();
    frame.menuItem("miExitToMenu").click();
    frame.button(JButtonMatcher.withText("KILÉPÉS A PROGRAMBÓL")).requireEnabled().requireEnabled().click();
  }
}