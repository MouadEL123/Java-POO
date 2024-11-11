package net.elazzioui.model;

public interface Condition<T> {
    boolean test(T t);
}
