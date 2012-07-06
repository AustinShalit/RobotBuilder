/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotbuilder.data;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Alex Henning
 */
public class Macro {
    private String name;
    LinkedList<Expansion> expansions = new LinkedList<Expansion>();

    public Macro(String name) {
        System.out.println("Creating macro "+name);
        this.name = name;
    }
    
    public void addExpansion(String name, String type, String defaultValue, String defaultDefault, LinkedList<Object> choices) {
        expansions.add(new Expansion(name, type, defaultValue, defaultDefault, choices));
    }

    /**
     * 
     * @param key
     * @param prop
     * @param props 
     */
    public void expand(String key, JSONObject prop, JSONObject props) {
        for (Expansion expansion : expansions) {
            try {
                JSONObject expanded = new JSONObject();
                expanded.put("Type", expansion.type);
                String defaultVal = prop.optString(expansion.defaultValue, null);
                if (defaultVal != null) {
                    System.out.println("Default[0]: "+defaultVal);
                    expanded.put("Default", defaultVal);
                } else {
                    System.out.println("Default[1]: "+expansion.defaultDefault);
                    expanded.put("Default", expansion.defaultDefault);
                }
                expanded.put("Choices", expansion.choices);
                props.put(key+" "+expansion.name, expanded);
            } catch (JSONException ex) {
                Logger.getLogger(Macro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Cleanup
        props.remove(key);
    }
    
    private class Expansion {
        String name, type, defaultValue, defaultDefault;
        LinkedList<Object> choices;

        public Expansion(String name, String type, String defaultValue, String defaultDefault, LinkedList<Object> choices) {
            this.name = name;
            this.type = type;
            this.defaultValue = defaultValue;
            this.defaultDefault = defaultDefault;
            this.choices = choices;
        }
    }
}