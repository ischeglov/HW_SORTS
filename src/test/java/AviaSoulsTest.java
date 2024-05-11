import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class AviaSoulsTest {

    AviaSouls airTickets = new AviaSouls();

    Ticket ticketOne = new Ticket("Уфа", "Санкт-Петербург", 5_900, 7, 8);
    Ticket ticketSecond = new Ticket("Санкт-Петербург", "Шанхай", 16_650, 2, 6);
    Ticket ticketThird = new Ticket("Шанхай", "Денпасар", 16_700, 8, 13);
    Ticket ticketFourth = new Ticket("Денпасар", "Шанхай", 16_650, 1, 8);
    Ticket ticketFifth = new Ticket("Шанхай", "Санкт-Петербург", 16_750, 17, 22);
    Ticket ticketSixth = new Ticket("Санкт-Петербург", "Уфа", 7_150, 11, 17);
    Ticket ticketSeventh = new Ticket("Санкт-Петербург", "Шанхай", 15_400, 2, 8);
    Ticket ticketEighth = new Ticket("Санкт-Петербург", "Шанхай", 17_800, 2, 22);
    Ticket ticketNinth = new Ticket("Уфа", "Санкт-Петербург", 5_900, 7, 10);

    @BeforeEach
    void setUp() {
        airTickets.add(ticketOne);
        airTickets.add(ticketSecond);
        airTickets.add(ticketThird);
        airTickets.add(ticketFourth);
        airTickets.add(ticketFifth);
        airTickets.add(ticketSixth);
        airTickets.add(ticketSeventh);
        airTickets.add(ticketEighth);
        airTickets.add(ticketNinth);
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Тесты завершены");
    }

    @Test
    public void shouldAllTicket() {
        Ticket[] expected = {ticketOne, ticketSecond, ticketThird, ticketFourth, ticketFifth, ticketSixth,
                ticketSeventh, ticketEighth, ticketNinth};
        Ticket[] actual = airTickets.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareToReturnsMinOne() {
        int expected = -1;
        int actual = ticketOne.compareTo(ticketSecond);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToReturnsPlusOne() {
        int expected = 1;
        int actual = ticketSecond.compareTo(ticketOne);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToReturnsZero() {
        int expected = 0;
        int actual = ticketSecond.compareTo(ticketFourth);

        assertEquals(expected, actual);
    }

    @Test
    public void ShouldSearchTicketsInAscendingOrderOfPriceDestinationsToShanghai() {
        Ticket[] expected = {ticketSeventh, ticketSecond, ticketEighth};
        Ticket[] actual = airTickets.search("Санкт-Петербург", "Шанхай");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsInAscendingOrderOfPriceDestinationsOneCoincidencesOne() {
        Ticket[] expected = {ticketSixth};
        Ticket[] actual = airTickets.search("Санкт-Петербург", "Уфа");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsInAscendingOrderOfPriceDestinationsOneCoincidencesZero() {
        Ticket[] expected = {};
        Ticket[] actual = airTickets.search("Санкт-Петербург", "Москва");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTicketsInAscendingOrderOfFlightTimeDestinationsToShanghai() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticketSecond, ticketSeventh, ticketEighth};
        Ticket[] actual = airTickets.searchAndSortBy("Санкт-Петербург", "Шанхай", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTicketsInAscendingOrderOfFlightTimeDestinationsToSaintPetersburg() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticketOne, ticketNinth};
        Ticket[] actual = airTickets.searchAndSortBy("Уфа", "Санкт-Петербург", comparator);

        assertArrayEquals(expected, actual);
    }
}