package lab.cb.scmd.calmorph;

import java.util.Vector;

public class BudValidation {

    private static final double _bud_length_threshold = 1.5;
    private static final double _mother_length_threshold = 0.2;
    private static final double _perpendicular_length_threshold = 0.4;
    private static final double _perpendicular_length_threshold_primary = 0.2;

    /**
     * @param cells
     * @param size
     * @param width
     * @return
     */
    static Cell[] validation(Cell[] cells, final int size, final int width) {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].neck == null || cells[i].neck[0] == cells[i].neck[cells[i].neck.length - 1] ||
                    cells[i].bud_edge.size() <= 0) {
                continue;
            }

            double neck_length = Math.sqrt(getDistance(cells[i].neck[0], cells[i].neck[cells[i].neck.length - 1], width));
            int neck_middle = getMiddlePoint(cells[i].neck[0], cells[i].neck[cells[i].neck.length - 1], width);
            Line line = new Line();
            Line perpendicular = line.calculatePerpendicularLine(cells[i].neck[0],
                    neck_middle, cells[i].neck[cells[i].neck.length - 1], width);
            int bud_middle = Line.findTheNearestPoint(perpendicular, cells[i].bud_edge, width);
            double bud_perpendicular_length = Math.sqrt(getDistance(neck_middle, bud_middle, width));

            if (budPerpendicularLengthValidationPrimary(neck_length, bud_perpendicular_length)) {
                deleteBudEdge(cells, i);
                continue;
            }

      /*
      int bud_center = getMiddlePoint(neck_middle, bud_middle, width);
      double[] max_min = calculateMaxAndMinDistances(bud_center, width, cells[i].bud_edge);
      setNeckAndBudMiddle(neck_middle, bud_middle, bud_center, cells, i);
      setGradCeptMiddle(perpendicular.getGradient(), perpendicular.getIntercept(), neck_middle, cells, i);
      */
            if (motherLengthValidation(cells[i].mother_edge.size(), cells[i].bud_edge.size()) &&
                    (budPerpendicularLengthValidation(neck_length, bud_perpendicular_length) ||
                            budLengthValidation(neck_length, cells[i].bud_edge.size()))) {
                deleteBudEdge(cells, i);
            }
        }

        return cells;
    }

    private static void deleteBudEdge(Cell[] cells, int i) {
        cells[i].mother_edge = budEdgeConcatinatesMotherEdge(cells[i].mother_edge, cells[i].bud_edge);
        cells[i].bud_edge = new Vector();
        cells[i].neck = null;
        cells[i].bud_ratio = 0;
        cells[i].setGroup(1);
    }

    protected static void setNeckAndBudMiddle(int neck_middle, int bud_middle, int bud_center, Cell[] cells, int i) {
        cells[i].neck_and_bud_middle = new int[3];
        cells[i].neck_and_bud_middle[0] = neck_middle;
        cells[i].neck_and_bud_middle[1] = bud_middle;
        cells[i].neck_and_bud_middle[2] = bud_center;
    }

    protected static void setGradCeptMiddle(double gradient, double intercept, double middle, Cell[] cells, int i) {
        cells[i].grad_cept_middle = new double[3];
        cells[i].grad_cept_middle[0] = gradient;
        cells[i].grad_cept_middle[1] = intercept;
        cells[i].grad_cept_middle[2] = middle;
    }

    private static double getDistance(int start, int end, int width) {
        int x1 = start % width;
        int y1 = start / width;
        int x2 = end % width;
        int y2 = end / width;
        return ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    private static int getMiddlePoint(int start, int end, int width) {
        int x1 = start % width;
        int y1 = start / width;
        int x2 = end % width;
        int y2 = end / width;
        return (x1 + x2) / 2 + (y1 + y2) / 2 * width;
    }

    /**
     * 妥当なbudか判定 1 （budの外周の長さ）
     *
     * @param neck_length
     * @param bud_edge_length
     * @return : 妥当なbud = true,  budではない = false
     */
    private static boolean budLengthValidation(double neck_length, double bud_edge_length) {
        return neck_length * _bud_length_threshold > bud_edge_length;
    }

    /**
     * 妥当なbudか判定 2 （motherの外周の長さ）
     *
     * @param mother_edge_length
     * @param bud_edge_length
     * @return
     */
    private static boolean motherLengthValidation(double mother_edge_length, double bud_edge_length) {
        return bud_edge_length > mother_edge_length * _mother_length_threshold;
    }

    /**
     * 妥当なbudか判定 3 （budの外周の中間点と budのnecksの中間点 間の長さ、が budのnecks間の長さ の閾値倍以下なら budでない。）
     *
     * @param neck_length
     * @param bud_perpendicular_length
     * @return
     */
    private static boolean budPerpendicularLengthValidation(double neck_length, double bud_perpendicular_length) {
        return neck_length * _perpendicular_length_threshold > bud_perpendicular_length;
    }

    /**
     * 妥当なbudか判定 3-2
     *
     * @param neck_length
     * @param bud_perpendicular_length
     * @return
     */
    private static boolean budPerpendicularLengthValidationPrimary(double neck_length, double bud_perpendicular_length) {
        return neck_length * _perpendicular_length_threshold_primary > bud_perpendicular_length;
    }

    private static Vector budEdgeConcatinatesMotherEdge(Vector mother_edge, Vector bud_edge) {
        mother_edge.addAll(bud_edge);
        return mother_edge;
    }

    protected static double[] calculateMaxAndMinDistances(int center, int width, Vector<Integer> edge) {
        double[] result = new double[2];
        double max = getDistance(center, edge.get(0), width);
        double min = max;

        for (int i = 1; i < edge.size(); i++) {
            double distance = getDistance(center, edge.get(i), width);

            if (min > distance) {
                min = distance;
            } else if (max < distance) {
                max = distance;
            }
        }

        result[0] = max;
        result[1] = min;
        return result;
    }
}
