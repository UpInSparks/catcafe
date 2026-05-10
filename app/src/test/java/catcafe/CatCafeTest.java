package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatCafeTest {

    private CatCafe cafe;
    private FelineOverLord mimi;
    private FelineOverLord luna;

    @BeforeEach
    void setUp() {
        cafe = new CatCafe();
        mimi = new FelineOverLord("Mimi", 3);
        luna = new FelineOverLord("Luna", 5);
    }

    //###################################################################//
    @Test
    // leeres Cafe, Anzahl an Katzen = 0
    void getCatCount_emptyCafe_returnsZero() {
        // given
        // when
        long count = cafe.getCatCount();
        // then
        assertEquals(0, count);
    }

    @Test
        // eine Katze hinzufügen, Anzahl an Katzen = 1
    void addCat_oneCat_countIsOne() {
        // given
        // when
        cafe.addCat(mimi);
        // then
        assertEquals(1, cafe.getCatCount());
    }

    @Test
        // zwei Katzen hinzufügen(gleichzeitig), Anzahl an Katzen = 2
    void addCat_multipleCats_countMatchesNumberOfCatsAdded() {
        // given
        // when
        cafe.addCat(mimi);
        cafe.addCat(luna);
        // then
        assertEquals(2, cafe.getCatCount());
    }

    @Test
        // Katze Null hinzufügen, Nullpointer
    void addCat_nullCat_throwsNullPointerException() {
        // given
        // when + then
        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }

    // ##########################################################
// Katzen beim Namen
    @Test
    // 2 Katzen im Cafe, ist Mimi da, name existiert
    void getCatByName_existingName_returnsCorrectCat() {
        // given
        cafe.addCat(mimi);
        cafe.addCat(luna);
        // when
        FelineOverLord result = cafe.getCatByName("Mimi");
        // then
        assertEquals(mimi, result);
    }

    @Test
        // Mimi im cafe, ist unbekannte katze da, null
    void getCatByName_nonExistingName_returnsNull() {
        // given
        cafe.addCat(mimi);
        // when
        FelineOverLord result = cafe.getCatByName("Unbekannt");
        // then
        assertNull(result);
    }

    @Test
        // leeres Cafe, ist mimi da, null
    void getCatByName_emptyCafe_returnsNull() {
        // given
        // when
        FelineOverLord result = cafe.getCatByName("Mimi");
        // then
        assertNull(result);
    }

    // ################################################################
// Katze bei Gewicht finden
    @Test
    // 2 Katzen im Cafe, ist eine Katze da die 3 oder 4 wiegt, ja mimi
    void getCatByWeight_catWithinRange_returnsCorrectCat() {
        // given
        cafe.addCat(mimi); // Gewicht 3
        cafe.addCat(luna); // Gewicht 5
        // when
        FelineOverLord result = cafe.getCatByWeight(3, 4); // [3, 4) → Mimi
        // then
        assertEquals(mimi, result);
    }

    @Test
        // 2 Katzen im cafe, ist eine Katze mit Hohem gewicht da, Keine KAtze im Bereich
    void getCatByWeight_noCatInRange_returnsNull() {
        // given
        cafe.addCat(mimi); // Gewicht 3
        cafe.addCat(luna); // Gewicht 5
        // when
        FelineOverLord result = cafe.getCatByWeight(10, 20);
        // then
        assertNull(result);
    }

    @Test
        // mimi im Cafe, gibt es eine Katze mit negativ gewicht, nein
    void getCatByWeight_negativeMinWeight_returnsNull() {
        // given
        cafe.addCat(mimi);
        // when
        FelineOverLord result = cafe.getCatByWeight(-1, 5);
        // then
        assertNull(result);
    }
}

