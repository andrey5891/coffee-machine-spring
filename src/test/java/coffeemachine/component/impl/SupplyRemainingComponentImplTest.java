package coffeemachine.component.impl;

import coffeemachine.component.SupplyRemainingService;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.MoneyRepository;
import coffeemachine.repository.SupplyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class SupplyRemainingServiceImplTest {
    @Mock
    private SupplyRepository supplyRepository;

    @Mock
    private MoneyRepository moneyRepository;

    @InjectMocks
    private SupplyRemainingServiceImpl supplyRemainingService;

    private static final List<SupplyModel> supplyModelList = List.of(
            SupplyModel.builder().supplyTypeId(1L).amount(150).build(),
            SupplyModel.builder().supplyTypeId(2L).amount(150).build(),
            SupplyModel.builder().supplyTypeId(3L).amount(150).build(),
            SupplyModel.builder().supplyTypeId(4L).amount(150).build()
    );

    @Test
    public void mocksNotNull() {
        assertNotNull(supplyRepository);
        assertNotNull(moneyRepository);
        assertNotNull(supplyRemainingService);
    }


    /*@Test
    public void getSupplyRemaining() {
        assertEquals(supplyModelList, supplyRemainingService.getRemainingSupply());
    }*/
}
