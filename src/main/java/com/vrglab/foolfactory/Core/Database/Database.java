package com.vrglab.foolfactory.Core.Database;

import java.util.*;

public abstract class Database<t> {
    private Map<String, t> item_list = new Hashtable<>();

    public void Store(String name, t i) {
        if(!item_list.containsKey(name))
            item_list.put(name, i);
    }

    public t GetEntry(String name) {
        return item_list.get(name);
    }

    public Collection<t> getDatalist() {
        return item_list.values();
    }
}
