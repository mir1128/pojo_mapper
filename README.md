# usage


**guava版**


```
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
```

**java8版**

 not implement yetj.