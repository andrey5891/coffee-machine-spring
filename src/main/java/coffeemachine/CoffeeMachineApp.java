package coffeemachine;

import coffeemachine.component.*;
import coffeemachine.component.impl.*;
import coffeemachine.controller.BuyCoffeeController;
import coffeemachine.converter.Converter;
import coffeemachine.converter.impl.CoffeeTypeDtoToCoffeeTypeEnumConverter;
import coffeemachine.converter.impl.SupplyEntityToModelConverterImpl;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.entity.Supply;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import coffeemachine.repository.impl.MoneyLocationRepositoryImpl;
import coffeemachine.repository.impl.MoneyRepositoryImpl;
import coffeemachine.repository.impl.SupplyRepositoryImpl;
import coffeemachine.repository.impl.SupplyTypeRepositoryImpl;
import coffeemachine.service.BuyCoffeeService;
import coffeemachine.service.impl.BuyCoffeeServiceImpl;

import java.util.Scanner;

public class CoffeeMachineApp {
    public static void main(String[] args) {
        SupplyTypeRepository supplyTypeRepository = new SupplyTypeRepositoryImpl();
        SupplyRepository supplyRepository = new SupplyRepositoryImpl();
        MoneyLocationRepository moneyLocationRepository = new MoneyLocationRepositoryImpl();
        MoneyRepository moneyRepository = new MoneyRepositoryImpl();
        Converter<Supply, SupplyModel> supplyToSupplyModelConverter = new SupplyEntityToModelConverterImpl();
        Converter<CoffeeTypeDto, CoffeeVariantEnum> coffeeTypeDtoToCoffeeVariantEnumConverter = new CoffeeTypeDtoToCoffeeTypeEnumConverter();

        CheckSupplyForCoffeeTypeComponent checkSupplyForCoffeeTypeComponent = new CheckSupplyForCoffeeTypeComponentImpl(supplyRepository, supplyTypeRepository);
        MoneyAcceptanceComponent moneyAcceptanceComponent = new MoneyAcceptanceComponentImpl(moneyRepository, moneyLocationRepository);
        MoneyReceivingComponent moneyReceivingComponent = new MoneyReceivingComponentImpl(moneyRepository, moneyLocationRepository);
        SupplyManagerComponent supplyManagerComponent = new SupplyManagerComponentImpl(supplyRepository, supplyTypeRepository);
        SupplyRemainingComponent supplyRemainingComponent = new SupplyRemainingComponentImpl(supplyRepository, supplyTypeRepository, supplyToSupplyModelConverter);

        BuyCoffeeService buyCoffeeService = new BuyCoffeeServiceImpl(checkSupplyForCoffeeTypeComponent, moneyAcceptanceComponent, supplyManagerComponent, coffeeTypeDtoToCoffeeVariantEnumConverter);
        BuyCoffeeController buyCoffeeController = new BuyCoffeeController(buyCoffeeService);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter coffee type: ");
        String s = scanner.nextLine();
        CoffeeTypeDto coffeeTypeDto = CoffeeTypeDto.builder().coffeeType(s.toUpperCase()).build();

        System.out.println(buyCoffeeController.buyCoffee(coffeeTypeDto).getMessage());
    }
}
