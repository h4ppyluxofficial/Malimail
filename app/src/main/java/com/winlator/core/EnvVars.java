package com.winlator.core;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class EnvVars implements Iterable<String> {
    private final LinkedHashMap<String, String> data = new LinkedHashMap<>();

    public EnvVars() {
        applyMaliFixes();
    }

    public EnvVars(String values) {
        applyMaliFixes();
        putAll(values);
    }

    private void applyMaliFixes() {
        data.put("MESA_VK_WSI_PRESENT_MODE", "fifo");
        data.put("DXVK_STATE_CACHE", "0");
        data.put("PAN_MESA_DEBUG", "sync");
    }

    public EnvVars put(String name, Object value) {
        data.put(name, String.valueOf(value));
        return this;
    }

    public void putAll(String[] items) {
        if (items == null) return;
        for (String item : items) {
            int index = item.indexOf("=");
            if (index != -1) {
                String name = item.substring(0, index);
                String value = item.substring(index + 1);
                data.put(name, value);
            }
        }
    }

    public void putAll(String values) {
        if (values == null || values.isEmpty()) return;
        putAll(values.split(" "));
    }

    public void putAll(EnvVars envVars) {
        data.putAll(envVars.data);
    }

    public String get(String name) {
        return data.getOrDefault(name, "");
    }

    public void remove(String name) {
        data.remove(name);
    }

    public boolean has(String name) {
        return data.containsKey(name);
    }

    public void clear() {
        data.clear();
        applyMaliFixes();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    @NonNull
    @Override
    public String toString() {
        return String.join(" ", toStringArray());
    }

    public String toEscapedString() {
        StringBuilder result = new StringBuilder();
        for (String key : data.keySet()) {
            if (result.length() > 0) result.append(" ");
            String value = data.get(key);
            result.append(key).append("=").append(value.replace(" ", "\\ "));
        }
        return result.toString();
    }

    public String[] toStringArray() {
        String[] stringArray = new String[data.size()];
        int index = 0;
        for (String key : data.keySet()) stringArray[index++] = key + "=" + data.get(key);
        return stringArray;
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return data.keySet().iterator();
    }
}
