package io.flaggton.pixxelator.views;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.services.JfxUiService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class MainController implements Initializable {
    @FXML
    private BorderPane borderPane;

    private final JfxUiService jfxUiService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button button = new Button();
        ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(button);
        borderPane.setCenter(zoomableScrollPane);
    }
    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }
    public void onNewCanvasButtonClick(){
        System.out.println("onNewCanvasButtonClick()");
    }
}

