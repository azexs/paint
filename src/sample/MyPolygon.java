package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public class MyPolygon extends Polygon implements Shapes {

    private double midx;
    private double midy;
    private double diffX;
    private double diffY;


    /**
     * List of polygon points
     */
    List<Double> points = new ArrayList<>();

    /**
     * Function add new points to a polygon.
     *
     * @param e Mouse event
     */
    public void points(MouseEvent e) {

        this.getPoints().addAll(e.getX(), e.getY());

    }

    /**
     * Function get location of a mouse event and save polygon points in ArrayList points.
     *
     * @param e Mouse event
     */

    public void getLocation(MouseEvent e) {
        diffX = e.getX();
        diffY = e.getY();
        int i = 0;
        while (i < this.getPoints().size()) {
            points.add(this.getPoints().get(i));
            i = i + 1;
        }
    }

    /**
     * Function sets a new location depends on mouse event.
     *
     * @param e Mouse event.
     */

    public void setLocation(MouseEvent e) {
        int i = 0;

        while (i < this.getPoints().size()) {
            if (e.getX() > diffX) {
                this.getPoints().set(i, points.get(i) + (e.getX() - diffX));
            } else {
                this.getPoints().set(i, points.get(i) - (diffX - e.getX()));
            }
            i = i + 2;
        }

        int y = 1;
        while (y < this.getPoints().size()) {
            if (e.getY() > diffY) {
                this.getPoints().set(y, points.get(y) + (e.getY() - diffY));
            } else {
                this.getPoints().set(y, points.get(y) - (diffY - e.getY()));
            }
            y = y + 2;
        }

    }

    /**
     * Function changes polygon color.
     *
     * @param color Color selected in Colour Picker
     */

    public void setColor(Color color) {
        setFill(color);
    }

    /**
     * Function increase polygon size.
     */

    public void setBigger() {
        int i = 0;
        while (i < this.getPoints().size()) {
            double temp;
            temp = this.getPoints().get(i);

            if (temp < midx) {
                temp = temp - 5;
                this.getPoints().set(i, temp);
            } else {
                temp = temp + 5;
                this.getPoints().set(i, temp);
            }
            i = i + 2;
        }

        int y = 1;
        while (y < this.getPoints().size()) {
            double temp;
            temp = this.getPoints().get(y);
            if (temp < midy) {
                temp = temp - 5;
                this.getPoints().set(y, temp);
            } else {
                temp = temp + 5;
                this.getPoints().set(y, temp);
            }
            y = y + 2;
        }
    }

    /**
     * Function decrease polygon size.
     */

    public void setSmaller() {
        int i = 0;
        while (i < this.getPoints().size()) {
            double temp;
            temp = this.getPoints().get(i);
            if (temp < midx) {
                temp = temp + 5;
                this.getPoints().set(i, temp);
            } else {
                temp = temp - 5;
                this.getPoints().set(i, temp);
            }
            i = i + 2;
        }

        int y = 1;
        while (y < this.getPoints().size()) {
            double temp;
            temp = this.getPoints().get(y);


            if (temp < midy) {
                temp = temp + 5;
                this.getPoints().set(y, temp);
            } else {
                temp = temp - 5;
                this.getPoints().set(y, temp);
            }

            y = y + 2;
        }
    }

    /**
     * Function checks, if we clicked on polygon.
     *
     * @param e Mouse event
     * @return Returns true if we clicked on polygon, else false.
     */

    public boolean isSelected(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Double minx = this.getPoints().get(0);
        Double maxx = this.getPoints().get(0);
        Double miny = this.getPoints().get(1);
        Double maxy = this.getPoints().get(1);
        int i = 2;
        int z = 3;

        while (i < this.getPoints().size()) {
            if (this.getPoints().get(i) < minx) minx = this.getPoints().get(i);
            if (this.getPoints().get(i) > maxx) maxx = this.getPoints().get(i);
            i = i + 2;
        }

        while (z < this.getPoints().size()) {
            if (this.getPoints().get(z) < miny) miny = this.getPoints().get(z);
            if (this.getPoints().get(z) > maxy) maxy = this.getPoints().get(z);
            z = z + 2;
        }

        midx = (maxx + minx) / 2;
        midy = (maxy + miny) / 2;

        if (((minx < x && x < maxx) && (miny < y && y < maxy))) return true;
        return false;
    }

    /**
     * Unused function.
     */

    public void firstPnt(MouseEvent e) {
        //}
    }

    /**
     * Unused function.
     */

    public void secondPnt(MouseEvent e) {
        //
    }

}