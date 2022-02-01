package riskMatrix;

import java.util.Arrays;

public class Risk {
    private String riskName;
    private int riskProbability;
    private int riskImpact;

    public Risk(String riskName, int riskProbability, int riskImpact) {
        this.riskName = riskName;
        this.riskProbability = riskProbability;
        this.riskImpact = riskImpact;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public int getRiskProbability() {
        return riskProbability;
    }

    public void setRiskProbability(int riskProbability) {
        this.riskProbability = riskProbability;
    }

    public int getRiskImpact() {
        return riskImpact;
    }

    public void setRiskImpact(int riskImpact) {
        this.riskImpact = riskImpact;
    }

    public int getRisk() {
        return getRiskProbability() * getRiskImpact();
    }


    public String getRiskProbabilityText() {
        String riskProbabilityText;
        switch (getRiskProbability()) {
            case 1: {
                riskProbabilityText = "Very unlikely";
                break;
            }
            case 2: {
                riskProbabilityText = "unlikely";
                break;
            }
            case 3: {
                riskProbabilityText = "Possible";
                break;
            }
            case 4: {
                riskProbabilityText = "Likely";
                break;
            }
            case 5: {
                riskProbabilityText = "Very likely";
                break;
            }
            default:
                riskProbabilityText = "Erorr";

        }

        return riskProbabilityText;
    }

    public String getRiskImpactText() {
        String riskImpactText;
        switch (getRiskImpact()) {
            case 1: {
                riskImpactText = "Very low";
                break;
            }
            case 2: {
                riskImpactText = "Low";
                break;
            }
            case 3: {
                riskImpactText = "Medium";
                break;
            }
            case 4: {
                riskImpactText = "High";
                break;
            }
            case 5: {
                riskImpactText = "Very High";
                break;
            }
            default:
                riskImpactText = "Error";

        }
        return riskImpactText;
    }

    public String getRiskText() {
        String riskText;
        switch (getRisk()) {
            case 1:
            case 2:
            case 3:
            case 4: {
                riskText = "Minor";
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: {
                riskText = "Moderate";
                break;
            }
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20: {
                riskText = "Major";
                break;
            }
            case 21:
            case 22:
            case 23:
            case 24:
            case 25: {
                riskText = "sever";
                break;
            }
            default:
                riskText = "Error";

        }
        return riskText;
    }

    @Override
    public String toString() {
        return "Risk{" +
                "riskName='" + riskName + '\'' +
                ", riskProbability=" + getRiskProbabilityText() +
                ", riskImpact=" + getRiskImpactText() +
                ", risk=" + getRiskText() +
                '}';
    }

    public String[] toArray() {
        String[] array = {this.riskName, this.getRiskImpactText(), this.getRiskProbabilityText(), this.getRiskText()};
        return array;
    }


}
