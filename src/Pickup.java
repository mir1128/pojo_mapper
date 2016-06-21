public class Pickup {
    private String naming;
    private String cost;
    private Integer power;

    public Pickup() {
    }

    public String getNaming() {
        return naming;
    }

    public void setNaming(String naming) {
        this.naming = naming;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Pickup{" +
                "naming='" + naming + '\'' +
                ", cost='" + cost + '\'' +
                ", power=" + power +
                '}';
    }
}
