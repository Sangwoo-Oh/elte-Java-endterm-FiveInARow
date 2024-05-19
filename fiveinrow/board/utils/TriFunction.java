package fiveinrow.board.utils;

@FunctionalInterface
public interface TriFunction<T1,T2,T3,T4>{
    public T4 apply(T1 t1, T2 t2, T3 t3);
}
