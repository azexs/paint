package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class MyRectangle extends Rectangle implements Shapes {

    private double x;
    private double y;
    private double diffX;
    private double diffY;

    /**
     * Function gets first point of rectangle position.
     *
     * @param e Mouse event
     */

    @Override
    public void firstPnt(MouseEvent e) {
        x = e.getX();
        y = e.getY();

    }

    /**
     * Function gets second point of rectangle position and calculates dimensions.
     *
     * @param e Mouse event
     */

    @Override
    public void secondPnt(MouseEvent e) {
        double x2 = e.getX();
        double y2 = e.getY();
        if (x < x2) this.setX(x);
        else this.setX(x2);
        if (y < y2) this.setY(y);
        else this.setY(y2);
        this.setWidth(Math.abs(x - x2));
        this.setHeight(Math.abs(y - y2));

    }

    /**
     * Function checks, if we clicked on rectangle.
     *
     * @param e Mouse event
     * @return Returns true if we clicked on rectangle, else false.
     */

    @Override
    public boolean isSelected(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (getX() < x && x < getX() + getWidth() && getY() < y && y < getY() + getHeight()) return true;
        return false;

    }

    /**
     * Function calculates distance of mouse event and rectangle position.
     *
     * @param e Mouse event
     */

    @Override
    public void getLocation(MouseEvent e) {
        diffX = e.getX() - getX();
        diffY = e.getY() - getY();

    }

    /**
     * Function sets a new rectangle position.
     *
     * @param e Mouse event
     */

    @Override
    public void setLocation(MouseEvent e) {
        setX(e.getX() - diffX);
        setY(e.getY() - diffY);

    }

    /**
     * Function changes rectangle color.
     *
     * @param color Color selected in Colour Picker
     */
    @Override
    public void setColor(Color color) {
        setFill(color);

    }

    /**
     * Function increase rectangle size.
     */

    @Override
    public void setBigger() {
        setX(getX() - 3);
        setY(getY() - 3);
        setWidth(getWidth() + 6);
        setHeight(getHeight() + 6);

    }

    /**
     * Function decrease rectangle size.
     */

    @Override
    public void setSmaller() {
        setX(getX() + 3);
        setY(getY() + 3);
        setWidth(getWidth() - 6);
        setHeight(getHeight() - 6);

    }

    /**
     * Unused function.
     *
     * @param e Mouse event 
     */

    public void points(MouseEvent e) {
        //
    }

}
