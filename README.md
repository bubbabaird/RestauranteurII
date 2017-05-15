**NOTE: This is an API. That is, no form submission or template rendering!**

Let's start with some context. The front-end class has been working on the front-end website for a restaurant. It uses an API. Luke has built their API for them. Our goal is to swap out Luke's API for our own. We can do this if we just have our API use the same routes, and retrieve/return the same JSON.

So, let's think of the needs that a restaurant has. Restaurants have menus that, as customers, we should be able to choose from. For Part One, let's let customers view our menu.

## GET /menu
Returns an array of available menu items.

```
RESPONSE 
[
  {
    id: <number>,
    name: <string>,
    description: <string>,
    price: <number>,
    available: <boolean>
  },
  {
    id: <number>,
    name: <string>,
    description: <string>,
    price: <number>,
    available: <boolean>
  },
  {
    id: <number>,
    name: <string>,
    description: <string>,
    price: <number>,
    available: <boolean>
  },
]
```

That is the JSON for a List of menu items. (dare I say, maybe an `ArrayList<MenuItem>`? but I digress). Come up with 5 items to put in your API's menu.


Listing the menu items is important, but we also need customers to be able to order food off the menu.

## POST /order/<menu_id>
Create a new order for the specified menu item. The `table_id` can be any string value (you can come up with that on your own, like "Bob's Table" or "Party of 5." Orders to the same table id will eventually be a part of the same "Bill".

```
REQUEST 
{
    table_id: <string>
}
```

Finally, we will want to be able to view the bill for a given table (like, for "Bob's Table" or "Party of 5"). 

## GET /bill/<table_id>
```
RESPONSE 
{
  table_id: <string>,
  items: [
    {
      id: <number>,
      name: <string>,
      price: <number>
    }
  ]
}
```

Notice that I have not given you specific instructions on how to store this data. Part of this assignment is to think critically about the data structures (various classes, ArrayLists, HashMaps) you'll need to solve this problem.


## Requirements
Create a API for the route patterns above.
