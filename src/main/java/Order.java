import java.util.ArrayList;

/**
 * Created by BUBBABAIRD on 4/19/17.
 */
public class Order {
    private String table_id;

    public Order() {

    }

    public Order(String table_id) {
        this.table_id = table_id;
    }

    public String gettable_id() {
        return table_id;
    }

    public void settable_id(String table_id) {
        this.table_id = table_id;
    }
}
