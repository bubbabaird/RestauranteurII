import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import spark.Spark;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://newline.theironyard.com/paths/1040/units/5895/assignments/15402
public class Main {
    public static Gson gson = new Gson();

    public static ArrayList<MenuItem> menuItems = new ArrayList<>();


    public static void main(String[] args) {
        Spark.init();
        // created a hashmap of table names, and menu menuItems that they've ordered.
        // hashmap, stores tables and menu menuItems
        HashMap<String, ArrayList<MenuItem>> tables = new HashMap<>();
        // make a table and store it and it's value in the hashmap above ^

        tables.put("table1", new ArrayList<>());

//        MenuItem num1 = new MenuItem(1, "Number 1", "Hamburger with fries and a coke", 2.99, true);
//        MenuItem num2 = new MenuItem(2, "Number 2", "Cheeseburger with fries and a coke", 3.99, true);
//        MenuItem num3 = new MenuItem(3, "Number 3", "Chicken BLT", 4.99, true);

        menuItems.add(new MenuItem(1, "Number 1", "Hamburger with fries and a coke", 2.99, true));
        menuItems.add(new MenuItem(2, "Number 2", "Cheeseburger with fries and a coke", 3.99, true));
        menuItems.add(new MenuItem(3, "Number 3", "Chicken BLT", 4.99, true));

        // order an item from the menu
        Spark.post("/order/:menu_id", (request, response) -> {
            // get table id from json
            int orderId = Integer.valueOf(request.params("menu_id"));

            MenuItem currentMenuItem = null;
            // run block of code for every item in the menu
            for(MenuItem a : menuItems) {
                if (a.getId() == orderId) {
                    currentMenuItem = a;
                    break;
                }
            }
            Order a = gson.fromJson(request.body(), Order.class);
            tables.putIfAbsent(a.gettable_id(), new ArrayList<>());
            tables.get(a.gettable_id()).add(currentMenuItem);

            return gson.toJson(menuItems);
        });

        // get the menu
        Spark.get("/menu", (req, res) -> gson.toJson(menuItems));

        // using a loop, find the item that they asked for:
        Spark.get("/bill/:table_id", (request, response) -> {
            String tableId = (request.params("table_id"));
            ArrayList tableOrders = tables.get(tableId);
            Bill h = new Bill(tableId, tableOrders);
            return gson.toJson(h);
        });

        Spark.get("/order", ((request, response) -> {
            List individualOrders = new ArrayList();

            for (String key : tables.keySet()) {
                ArrayList items = tables.get(key);

                Bill b = new Bill(key, items);

                individualOrders.addAll(b.getIndividualOrders());
            }

            return gson.toJson(individualOrders);
        }));

        Spark.get("/order/:table_id", (request, response) -> {
            String tableId = request.params("table_id");
            Bill b = new Bill(tableId, tables.get(tableId));
            return gson.toJson(b);

//            return new JsonSerializer().deep(true).serialize(b.getIndividualOrders());
//            return new Json
        });
    }
}

// get the body of the request (the stuff you posted), and then turn it into an event.
// Event e = parser.parse(req.body(), Event.class);

// events.putIfAbsent(e.getEventID), new ArrayList<>();