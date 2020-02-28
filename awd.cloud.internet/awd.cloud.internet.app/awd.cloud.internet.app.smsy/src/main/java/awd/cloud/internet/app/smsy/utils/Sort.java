package awd.cloud.internet.app.smsy.utils;

/**
 * 排序
 *
 */
public class Sort extends Column {

    private String order = "asc";

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Sort() {
    }

    public Sort(String name) {
        setName(name);
    }

    public void asc() {
        this.order = "asc";
    }

    public void desc() {
        this.order = "desc";
    }

    @Override
    public int hashCode() {
        return String.valueOf(getName()).concat(order).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.hashCode() == obj.hashCode();
    }

}
