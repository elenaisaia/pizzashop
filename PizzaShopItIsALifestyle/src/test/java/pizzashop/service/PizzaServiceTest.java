package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {
    public static final String TEST_FILENAME = "data/test.txt";
    private MenuRepository menuRepository;
    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    private static final PaymentType type = PaymentType.Card;

    @BeforeEach
    void setUp() {
        clearTestFile();
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository(TEST_FILENAME);
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    private void clearTestFile() {
        try (FileWriter writer = new FileWriter(TEST_FILENAME)) {
            writer.write("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("TC1_ECP")
    @Timeout(1)
    void givenTable1AndVal420Point69_whenAddPayment_thenExceptionIsThrown() {
        //given
        int table = 1;
        double amount = 420.69;

        //when
        try {
            pizzaService.addPayment(table, type, amount);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        //then
        assert(paymentRepository.getAll().contains(new Payment(table, type, amount)));
    }

    @Test
    @DisplayName("TC2_ECP")
    void givenTable3AndValMinus455_whenAddPayment_thenExceptionIsThrown() {
        //given
        int table = 3;
        double amount = -455;

        //when + then
        assertThrows(Exception.class, () -> {
            pizzaService.addPayment(table, type, amount);
        });
    }

    @Test
    @DisplayName("TC3_ECP")
    void givenTableMinus5AndVal800Point85_whenAddPayment_thenExceptionIsThrown() {
        //given
        int table = -5;
        double amount = 800.55;

        //when + then
        assertThrows(Exception.class, () -> {
            pizzaService.addPayment(table, type, amount);
        });
    }

    @Test
    @DisplayName("TC1_BVA")
    void givenTable0AndVal0Point5_whenAddPayment_thenExceptionIsThrown() {
        //given
        int table = 0;
        double amount = 0.5;

        //when + then
        assertThrows(Exception.class, () -> {
            pizzaService.addPayment(table, type, amount);
        });
    }

    @Test
    @DisplayName("TC2_BVA")
    @Timeout(1)
    void givenTable8AndVal0Point5_whenAddPayment_thenExceptionIsThrown() {
        //given
        int table = 8;
        double amount = 0.5;

        //when
        try {
            pizzaService.addPayment(table, type, amount);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        //then
        assert(paymentRepository.getAll().contains(new Payment(table, type, amount)));
    }

    @Test
    @DisplayName("TC3_BVA")
    void givenTable1AndVal0_whenAddPayment_thenExceptionIsThrown() {
        //given
        int table = 1;
        double amount = 0;

        //when + then
        assertThrows(Exception.class, () -> {
            pizzaService.addPayment(table, type, amount);
        });
    }

    @Test
    @DisplayName("TC4_BVA")
    @Timeout(1)
    void givenTable1AndVal0Point5_whenAddPayment_thenExceptionIsThrown() {
        //given
        int table = 1;
        double amount = 0.5;

        //when
        try {
            pizzaService.addPayment(table, type, amount);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        //then
        assert(paymentRepository.getAll().contains(new Payment(table, type, amount)));
    }
}