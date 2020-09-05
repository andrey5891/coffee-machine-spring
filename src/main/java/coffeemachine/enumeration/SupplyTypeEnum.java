package coffeemachine.enumeration;

import coffeemachine.exception.NoSuchSupplyTypeEnumByCodeException;

import java.util.Arrays;

public enum SupplyTypeEnum {
    WATER("WATER", 1L),
    MILK("MILK", 2L),
    COFFEE("COFFEE", 3L),
    CUP("CUP", 4L);

    private final String name;

    private final Long code;

    SupplyTypeEnum(String name, Long code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }

    public static SupplyTypeEnum getSupplyTypeEnumByCode(Long code) {
        return Arrays.stream(SupplyTypeEnum.values())
                .filter((e) -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow(NoSuchSupplyTypeEnumByCodeException::new);
    }
}
