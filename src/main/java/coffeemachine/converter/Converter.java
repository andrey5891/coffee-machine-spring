package coffeemachine.converter;

public interface Converter<S, T> {
    T convert(S source);
}