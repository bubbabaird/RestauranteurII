import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BUBBABAIRD on 4/19/17.
 */
public class Bill {
    String tableId;
    ArrayList<MenuItem>items;

    public Bill() {

    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public Bill(String tableId, ArrayList<MenuItem> items) {
        this.tableId = tableId;
        this.items = items;

    }

    public List getIndividualOrders() {
        return items.stream().map(mi ->
            new Object() {
                public String objTableId = tableId;
                public MenuItem item = mi;
            }
        ).collect(Collectors.toList());
    }
}
