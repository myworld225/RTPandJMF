package withjavafx;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Fxmedia extends Application {
	JFrame frame = new JFrame();
	JDesktopPane desktop = new JDesktopPane();
	@Override
	public void start(Stage stage) throws Exception{
		final JFXPanel fxPanel = new JFXPanel();
		final SwingNode swingNode = new SwingNode();
		Player player;
		MediaLocator ml = new MediaLocator("vfw:Microsoft WDM Image Capture (Win32):0");
		try {
			player = Manager.createPlayer(ml);
			desktop.add(new JMFrame(player,"hi"));
			//frame.setLayout(new BorderLayout());
			frame.add(desktop);
//			frame.add(fxPanel);
//			frame.show();
			frame.setSize(640, 480);
			frame.setVisible(true);
			Platform.runLater(()->{
				initFX(fxPanel);
			});
			
//			SwingUtilities.invokeLater(() -> {
//				swingNode.setContent(desktop);
//			});
		} catch (NoPlayerException e) {
			System.err.println("Error: " + e);
		}
		
//		StackPane pane = new StackPane();
//		pane.getChildren().add(swingNode);
//		
//		Scene scene = new Scene(pane,600,600);
//		stage.setScene(scene);
//		stage.setTitle("JMF is working?");
//		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
	
	private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        Text  text  =  new  Text();
        
        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");

        root.getChildren().add(text);

        return (scene);
    }
}


class JMFrame extends JInternalFrame implements ControllerListener {
	Player mplayer;
	Component visual = null;
	Component control = null;
	int videoWidth = 100;
	int videoHeight = 100;
	int controlHeight = 30;
	int insetWidth = 10;
	int insetHeight = 30;
	boolean firstTime = true;

	public JMFrame(Player player, String title) {
		super(title, true, true, true, true);
		getContentPane().setLayout(new BorderLayout());
		setSize(320, 10);
		setLocation(50, 50);
		setVisible(true);
		mplayer = player;
		mplayer.addControllerListener((ControllerListener) this);
		mplayer.realize();
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent ife) {
				mplayer.close();
			}
		});

	}

	/**
	 * JMF의 컴포넌트의 종류는 두가지 (Visual과 ControlPanel가 있다)
	 */
	public void controllerUpdate(ControllerEvent ce) {
		if (ce instanceof RealizeCompleteEvent) {
			mplayer.prefetch();
		} else if (ce instanceof PrefetchCompleteEvent) {
			if (visual != null)
				return;

			if ((visual = mplayer.getVisualComponent()) != null) {
				Dimension size = visual.getPreferredSize();
				videoWidth = size.width;
				videoHeight = size.height;
				getContentPane().add("Center", visual);
			} else
				videoWidth = 320;
			if ((control = mplayer.getControlPanelComponent()) != null) {
				controlHeight = control.getPreferredSize().height;
				getContentPane().add("South", control);
			}
			setSize(videoWidth + insetWidth, videoHeight + controlHeight + insetHeight);
			validate();
			mplayer.start();
		} else if (ce instanceof EndOfMediaEvent) {
			mplayer.setMediaTime(new Time(0));
			mplayer.start();
		}
	}
}