package riskMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mini_project_group.Main;

public class RiskMatrix {

    private static final String HORIZONTAL_SEP = "-";
    private String verticalSep;
    private String joinSep;
    private String[] headers = {"RiskName", "Probability", "Impact", "Risk"};
    private List<String[]> rows = new ArrayList<>();
    private List<Risk> risks;
    private boolean rightAlign;

    public void addMain(Main main) {
        this.risks = main.searchForProject(1).getRisksFromProject();
    }

    public RiskMatrix() {
        setShowVerticalLines(false);
    }

    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }

    public void setShowVerticalLines(boolean showVerticalLines) {
        verticalSep = showVerticalLines ? "|" : "";
        joinSep = showVerticalLines ? "+" : " ";
    }

    public void setHeaders(String... headers) {
        this.headers = headers;
    }

    public void addRow(String... cells) {
        rows.add(cells);
    }

    public void print() {

            System.out.println("\t\t\t Risk Matrix");
            setShowVerticalLines(true);

            int[] maxWidths = headers != null ?
                    Arrays.stream(headers).mapToInt(String::length).toArray() : null;

            for (String[] cells : rows) {
                if (maxWidths == null) {
                    maxWidths = new int[cells.length];
                }
                if (cells.length != maxWidths.length) {
                    throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
                }
                for (int i = 0; i < cells.length; i++) {
                    maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
                }

            }

            if (headers != null) {
                printLine(maxWidths);
                printRow(headers, maxWidths);
                printLine(maxWidths);
            }
            for (String[] cells : rows) {
                printRow(cells, maxWidths);
            }
            if (headers != null) {
                printLine(maxWidths);
            }
            System.out.println("\n");
        }

    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println();
    }


    private void printRow(String[] cells, int[] maxWidths) {

        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
            if (rightAlign) {
                System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            } else {
                System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            }
        }
        System.out.println();
    }

    public void addRisk(String riskName, int riskProbability, int riskImpact, Main main) {
        Risk temp = new Risk(riskName, riskProbability, riskImpact);
        risks.add(temp);
        syncRows();
    }

    public Risk retrieveRisk(String name) throws NullPointerException {
        for (Risk risk : risks) {
            if (risk.getRiskName().equals(name)) {
                return risk;
            }
        }
        return null;
    }

    public void removeRisk(String name) throws NullPointerException {
        Risk temp = retrieveRisk(name);
        risks.remove(temp);
        syncRows();
    }

    public void syncRows() {
        rows.clear();
        for (Risk risk : risks) {
            rows.add(risk.toArray());
        }
    }
}