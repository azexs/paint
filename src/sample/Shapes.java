package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface Shapes {
    void firstPnt(MouseEvent mouseEvent);

    void secondPnt(MouseEvent mouseEvent);

    void points(MouseEvent mouseEvent);

    void getLocation(MouseEvent mouseEvent);

    void setLocation(MouseEvent mouseEvent);

    void setColor(Color color);

    void setBigger();

    void setSmaller();

    boolean isSelected(MouseEvent mouseEvent);
}
