import com.example.sanrio.Entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

    public class CategoryTest {

        @Test
        public void testGetterAndSetter() {
            Category category = new Category();
            category.setName("Test Category");

            assertEquals("Test Category", category.getName());
        }

        @Test
        public void testDefaultConstructor() {
            Category category = new Category();
            assertNull(category.getName());
        }
    }

