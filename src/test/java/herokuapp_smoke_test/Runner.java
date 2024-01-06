package herokuapp_smoke_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        C01CreateBooking.class,
        C02GetCreatedBooking.class,
        C03UpdateCreatedBooking.class,
        C04PartiallyUpdateBooking.class,
        C05DeleteBooking.class,
        C06GetDeletedBooking.class

})
public class Runner {

}
