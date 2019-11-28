package collections.interfaces;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface MyListInterface<T> extends Collection<T> {
    @Override
    boolean add(T t);
    public Array getAll();
    public <T> Optional getIf(Predicate<T> filter);
    public boolean addIf(Predicate<T> filter, T value);
}
