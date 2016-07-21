/**
 * Embedding Swing Content in JavaFX Applications
 */
package sample;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ConcurrentTaskProgressBar extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        //create the task by overriding the call method
        Task task = new Task<Void>(){
            @Override public Void call(){
                final int max = 1000000;
                for (int i=1; i<=max; i++){
                    if(isCancelled())
                        break;
                    updateProgress(i,max);
                    if (i % 100 == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e){
                        if(isCancelled()){
                            updateMessage("Cancelled");
                            break;
                        }
                    }
                    }
                }
                System.err.println(Thread.currentThread().getName() + " is done now.");
                return null;
            }

        };
        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(task.progressProperty());

        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane, 300, 300);
        pane.getChildren().add(bar);


        stage.setScene(scene);
        stage.setTitle("hello progress bar");
        stage.show();
        System.err.println(Thread.currentThread().getName() + " is working now.");

        new Thread(task).start();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
