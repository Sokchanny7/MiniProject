package mini.project.control;

import java.util.ArrayList;

public class Expression {

    private String pattern;
    private String option;
    private String line;
    private ArrayList<Object> datas = new ArrayList<>();

    public String getOption() {
        return option;
    }

    private String getPattern() {
        return pattern;
    }

    /**
     * get pattern and set
     */
    public Expression(String pattern) {
        this.pattern = pattern;
    }

    public boolean setOption() {
        String[] temp = this.getPattern().split("#");
        if (temp.length == 2) {
            this.option = temp[0];
            this.line = temp[1];
            return true;
        }
        return false;
    }

    public ArrayList<Object> getData() {
        return datas;
    }

    private String getLine() {
        return line;
    }

    public boolean isMatchdata() {
        String[] data = getLine().split("-");
        /**
         * If splitting data is match with product fields
         */

        switch (getOption().toLowerCase()) {
            case "s":
                if (data.length == 1) {
                    this.datas.add(data[0]);
                    return true;
                }
                break;
            case "d":
            case "r":
            case "g":
            case "se":
                if (data.length == 1 && data[0] != "") {
                    try {
                        Float.parseFloat(data[0]);
                        this.datas.add(data[0]);
                        return true;
                    } catch (Exception e) {
                        //e.printStackTrace();
                        return false;
                    }
                }
                return false;
            case "w":
                if (data.length == 4) {
                    try {
                        this.datas.add(data[0]);
                        this.datas.add(Double.parseDouble(data[1].trim()));
                        this.datas.add(Long.parseLong(data[2].trim()));
                        this.datas.add(data[3]);
                    } catch (Exception ex) {
                        return false;
                    }
                    return true;
                }
                break;
            case "u":
                if (data.length == 5) {
                    try {
                        this.datas.add(data[0]);
                        this.datas.add(data[1]);
                        this.datas.add(Double.parseDouble(data[2]));
                        this.datas.add(Long.parseLong(data[3]));
                        this.datas.add(data[4]);
                    } catch (Exception ex) {
                        return false;
                    }
                    return true;
                }
                break;

        }
        return false;
    }
}
