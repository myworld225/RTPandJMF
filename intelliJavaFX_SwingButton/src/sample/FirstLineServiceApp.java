/**
 *  shows an implementation of the Service class which reads the first line from any URL and returns it as a string.
 */
package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 2016-07-21.
 */
public class FirstLineServiceApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FirstLineService service = new FirstLineService();
        service.setUrl("http://google.com");
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
            @Override public void handle(WorkerStateEvent e){
                System.out.println("done:" + e.getSource().getValue());
            }
        });
        service.start();
    }

    public static void main(String[] args){
        launch(args);
    }

    private static class FirstLineService extends Service<String>{

        private StringProperty url = new SimpleStringProperty();

        public final void setUrl(String value){
            url.set(value);
        }
        public final String getUrl(){
            return url.get();
        }
        public final StringProperty urlProperty(){
            return url;
        }
        @Override
        protected Task<String> createTask() {
            return new Task<String>(){
                @Override
                protected String call() throws IOException, MalformedURLException{
                    try{
                        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(getUrl()).openStream()));
                        return in.readLine();
                    } catch (Exception e){

                    }
                    return null;
                }
            };
        }
    }
}
