import com.google.common.base.Function;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class PojoMapper<T, R> {
    private static class Value {
        private String value;
        private Function fieldMapper;

        public Value(String value, Function fieldMapper) {
            this.value = value;
            this.fieldMapper = fieldMapper;
        }

        public String getValue() {
            return value;
        }

        public Function getFieldMapper() {
            return fieldMapper;
        }
    }

    private Map<String, Value> mapper = new HashMap();

    public R mapping(T t, Class<R> cr) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        R r = cr.newInstance();
        for (String name : mapper.keySet()) {
            Field f1 = t.getClass().getDeclaredField(name);
            f1.setAccessible(true);
            Object src = f1.get(t);

            Value v = mapper.get(name);
            Field f2 = r.getClass().getDeclaredField(v.getValue());
            f2.setAccessible(true);

            f2.set(r, v.getFieldMapper() == null ? src :  v.getFieldMapper().apply(src));
        }

        return r;
    }


    public static PojoMapper of(Object... args) {
        if (args.length % 3 != 0) {
            return null;
        }

        PojoMapper result = new PojoMapper();
        for (int i = 0; i < args.length; i += 3) {
            result.mapper.put(args[i], new Value((String) args[i+1], (Function) args[i+2]));
        }

        return result;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        PojoMapper<Car, Pickup> simpleMapper = PojoMapper.of(
                "name", "naming", null,
                "price", "cost", new Function<BigDecimal, String>() {
                    @Override
                    public String apply(BigDecimal input) {
                        return input.toString();
                    }
                }
        );

        Pickup smart = simpleMapper.mapping(new Car("smart", new BigDecimal("300000")), Pickup.class);
        System.out.println(smart);

    }
}
