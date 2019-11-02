package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class MyCircle extends Circle implements Shapes {
    private double x;
    private double y;
    private double diffX;
    private double diffY;

    /**
     * Function gets circle position.
     *
     * @param e Mouse event
     */

    @Override
    public void firstPnt(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        this.setCenterX(x);
        this.setCenterY(y);

    }

    /**
     * Function calculates radius.
     *
     * @param e Mouse event
     */

    @Override
    public void secondPnt(MouseEvent e) {
        this.setRadius((Math.abs(e.getX() - x) + (Math.abs(e.getY() - y))) / 2);

    }

    /**
     * Function checks, if we clicked on circle.
     *
     * @param e Mouse event
     * @return Returns true if we clicked on circle, else false.
     */

    @Override
    public boolean isSelected(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        return Math.pow((x - getCenterX()), 2) + Math.pow((y - getCenterY()), 2) < Math.pow(getRadius(), 2);

    }

    /**
     * Function calculates distance of mouse event and circle center.
     *
     * @param e Mouse event
     */

    @Override
    public void getLocation(MouseEvent e) {
        diffX = e.getX() - getCenterX();
        diffY = e.getY() - getCenterY();

    }

    /**
     * Function sets a new location.
     *
     * @param e Mouse event
     */

    @Override
    public void setLocation(MouseEvent e) {
        setCenterX(e.getX() - diffX);
        setCenterY(e.getY() - diffY);

    }

    /**
     * Function changes circle color.
     *
     * @param color Color selected in Colour Picker
     */

    @Override
    public void setColor(Color color) {
        setFill(color);

    }

    /**
     * Function increase circle size.
     */

    @Override
    public void setBigger() {
        setRadius(getRadius() + 2);
    }

    /**
     * Function decrease circle size.
     */

    @Override
    public void setSmaller() {
        setRadius(getRadius() - 2);

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
