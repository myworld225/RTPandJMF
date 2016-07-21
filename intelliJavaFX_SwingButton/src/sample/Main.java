/**
 * Embedding Swing Content in JavaFX Applications
 */
package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javax.swing.*;

public class Main extends Application {

    //javafx.scene.control.Button
    public static Button fxButton;

    @Override
    public void start(Stage stage) throws Exception{

        final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);

        FlowPane pane = new FlowPane();
        fxButton = new Button("Enable JButton");
        fxButton.setTooltip(
                new Tooltip("Click this button to enable the Swing button."));
        fxButton.setStyle("-fx-font: 18 arial; -fx-base: #67ffc6;");
        fxButton.setDisable(true);

        //set eventhandler on fxButton
        fxButton.setOnAction((e) -> {
            SwingUtilities.invokeLater(() ->{
                SwingComponent.b1.setEnabled(true);
            });
            fxButton.setDisable(true);
        });

        pane.getChildren().addAll(swingNode, fxButton);

        Scene scene = new Scene(pane, 300, 100);
        stage.setScene(scene);
        stage.setTitle("Enable Button Sample");
        stage.show();


    }

    private void createSwingContent(final SwingNode swingNode){
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(new SwingComponent());
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
