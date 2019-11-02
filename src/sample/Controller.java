package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class Controller {
    /**
     * Pane on which we create shapes on.
     */
    @FXML
    private Pane canvas;
    /**
     * Circle ToggleButton
     */
    @FXML
    private ToggleButton circlebtn;
    /**
     *  Polygon ToggleButton
     */
    @FXML
    private ToggleButton polygonbtn;
    /**
     *  Rectangle ToggleButton
     */
    @FXML
    private ToggleButton rectanglebtn;
    /**
     * Edit ToggleButton
     */
    @FXML
    private ToggleButton editbtn;
    /**
     * ColorPicker control
     */
    @FXML
    private ColorPicker cp;
    /**
     * Mouse position Label
     */
    @FXML
    private Label label;

    /**
     * Current selected shape
     */
    private Shapes currentMyShape;

    /**
     * List of created shapes
     */

    private List<Shapes> myShapeList = new ArrayList<>();

    /**
     * Function looking for a nearest shape from a mouse event
     *
     * @param e Mouse event details
     * @return Function returns nearest shapes
     */

    private Shapes FindShape(MouseEvent e) {
        for (Shapes myShape : myShapeList) {
            if (myShape.isSelected(e)) {
                return myShape;
            }
        }
        return null;
    }

    /**
     * Function starts to create circle or rectangle, or checks if we clicked shape, depending on button selected.
     *
     * @param e Details of mouse press
     */

    @FXML
    void canvasmousepressed(MouseEvent e) {
        if (circlebtn.isSelected()) {
            currentMyShape = new MyCircle();
            Shapes myShape = currentMyShape;
            canvas.getChildren().add((Shape) myShape);
            myShape.firstPnt(e);
            myShape.setColor(cp.getValue());
        } else if (rectanglebtn.isSelected()) {
            currentMyShape = new MyRectangle();
            Shapes myShape = currentMyShape;
            canvas.getChildren().add((Shape) myShape);
            myShape.firstPnt(e);
            myShape.setColor(cp.getValue());

        } else if (editbtn.isSelected()) {
            currentMyShape = FindShape(e);
            if (currentMyShape != null) {
                currentMyShape.getLocation(e);
            }
        }
    }

    /**
     * Function creates circle or rectangle after mouse released, depending on button selected.
     *
     * @param e Details of mouse release
     */

    @FXML
    void canvasmousereleased(MouseEvent e) {
        if (circlebtn.isSelected()) {
            Shapes myShape = currentMyShape;
            myShape.secondPnt(e);
            myShapeList.add(myShape);
            currentMyShape = null;
        } else if (rectanglebtn.isSelected()) {
            Shapes myShape = currentMyShape;
            myShape.secondPnt(e);
            myShapeList.add(myShape);
            currentMyShape = null;
        }
    }

    /**
     * Function creates polygon on mouse click, or check if we clicked on shape, depending on button selected.
     *
     * @param e Details of mouse click
     */

    @FXML
    void canvasmouseclicked(MouseEvent e) {
        if (polygonbtn.isSelected()) {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (currentMyShape == null) {
                    currentMyShape = new MyPolygon();
                    Shapes myShape = currentMyShape;
                    ((Shape) myShape).setFill(cp.getValue());
                    ((Shape) myShape).setStroke(cp.getValue());
                    canvas.getChildren().add((Shape) myShape);
                }
                Shapes myShape = currentMyShape;
                myShape.points(e);
            } else {
                Shapes myShape = currentMyShape;
                myShape.points(e);
                myShapeList.add(myShape);
                currentMyShape = null;
            }
        }
        if (editbtn.isSelected()) {
            currentMyShape = FindShape(e);
        }

    }

    /**
     * Function shows details of cursor position after mouse draged and set a new location of selected shape.
     *
     * @param e Details of mouse dragged
     */

    @FXML
    void canvasmousedragged(MouseEvent e) {
        label.setText("X: " + e.getX() + "Y: " + e.getY());
        if (editbtn.isSelected()) {
            if (currentMyShape != null) {
                currentMyShape.setLocation(e);
            }
        }
    }

    /**
     * Function shows details of cursor position after mouse move.
     *
     * @param e Details of mouse moved
     */

    @FXML
    void canvasmousemoved(MouseEvent e) {
        label.setText("X: " + e.getX() + "Y: " + e.getY());
    }

    /**
     * Function changes color of selected shape.
     */

    @FXML
    void colourbtnclicked() {
        if (currentMyShape != null) {
            currentMyShape.setColor(cp.getValue());
        }
    }

    /**
     * Function deletes  selected shape.
     */

    @FXML
    void deletebtnclicked() {
        if (currentMyShape != null) {
            myShapeList.remove(currentMyShape);
            canvas.getChildren().remove(currentMyShape);
            currentMyShape = null;
        }
    }

    /**
     * Function deletes all shapes.
     */

    @FXML
    void newbtn() {
        for (Shapes shape : myShapeList) {
            canvas.getChildren().remove(shape);
        }
        myShapeList.clear();
    }

    /**
     * Function changes size of selected shape.
     *
     * @param e Details of scroll event.
     */

    @FXML
    void canvasscroll(ScrollEvent e) {
        if (currentMyShape != null) {
            if (e.getDeltaY() > 0) {
                {
                    currentMyShape.setBigger();
                }
            } else
                currentMyShape.setSmaller();
        }
    }

    /**
     * Function shows info about application.
     */

    @FXML
    void infobtn() {
        StackPane pane = new StackPane();
        TextArea info = new TextArea("Michal Tarka \n \n" + "This is a simple paint-like application, made in JavaFX." + "\n");
        info.setEditable(false);
        pane.getChildren().add(info);
        Stage stage = new Stage();
        stage.setTitle("Info");
        stage.setScene(new Scene(pane, 600, 300));
        stage.show();
    }

    /**
     * Function saves image of canvas.
     */

    @FXML
    void savebtn() {

        FileChooser savefile = new FileChooser();
        savefile.setTitle("Save File");


        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG (*.png)", "*.png");
        savefile.getExtensionFilters().add(extFilter);


        Window primaryStage = null;
        File file = savefile.showSaveDialog(null);


        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage(800, 530);
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                System.out.println("Error!");
            }
        }

    }

    /**
     * Changes background color of selected button and turn off others.
     */

    @FXML
    void circlebuttonpressed() {
        polygonbtn.setSelected(false);
        rectanglebtn.setSelected(false);
        editbtn.setSelected(false);
        editbtn.setStyle("-fx-background-color: null  ");
        circlebtn.setStyle("-fx-background-color: darkgrey ");
        polygonbtn.setStyle("-fx-background-color: null ");
        rectanglebtn.setStyle("-fx-background-color: null ");

    }

    /**
     * Changes background color of selected button and turn off  others.
     */

    @FXML
    void rectanglebuttonpressd() {
        circlebtn.setSelected(false);
        polygonbtn.setSelected(false);
        editbtn.setSelected(false);
        editbtn.setStyle("-fx-background-color: null ");
        circlebtn.setStyle("-fx-background-color: null ");
        polygonbtn.setStyle("-fx-background-color: null ");
        rectanglebtn.setStyle("-fx-background-color: darkgrey ");
    }

    /**
     * Changes background color of selected button and turn off  others.
     */

    @FXML
    void polygonbuttonpressed() {
        circlebtn.setSelected(false);
        editbtn.setSelected(false);
        rectanglebtn.setSelected(false);
        editbtn.setStyle("-fx-background-color: null");
        circlebtn.setStyle("-fx-background-color: null ");
        polygonbtn.setStyle("-fx-background-color: darkgrey ");
        rectanglebtn.setStyle("-fx-background-color: null ");
    }

    /**
     * Changes background color of selected button and turn off others.
     */

    @FXML
    void editbuttonpressed() {
        circlebtn.setSelected(false);
        polygonbtn.setSelected(false);
        rectanglebtn.setSelected(false);
        editbtn.setStyle("-fx-background-color: darkgrey  ");
        circlebtn.setStyle("-fx-background-color: null ");
        polygonbtn.setStyle("-fx-background-color: null ");
        rectanglebtn.setStyle("-fx-background-color: null ");
    }
}