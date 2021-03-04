package demo.util;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import demo.model.Menu;

public class Utils {
    public static JsonArray getMenuJson(List<Menu> menuList) {
        JsonArray jsonArray = new JsonArray();
        for (Menu menu : menuList) {
            JsonArray child = new JsonArray();
            for (Menu menu1 : menu.getChild()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", menu1.getId());
                jsonObject.addProperty("iconCls", "fa fa-send-o");
                jsonObject.addProperty("text", menu1.getName());
                jsonObject.addProperty("url", menu1.getUrl());
                child.add(jsonObject);
            }
            JsonObject parent = new JsonObject();
            parent.addProperty("id", menu.getId());
            parent.addProperty("iconCls", "fa fa-send-o");
            parent.addProperty("text", menu.getName());
            parent.add("children", child);
            jsonArray.add(parent);
        }
        return jsonArray;
    }
}
