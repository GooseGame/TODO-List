import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    private String defaultDesc = "description";
    private String additionalDesc = "one more";
    private String id1 = "1";
    private String id2 = "2";
    private String rewriteOpt = "rewrite";
    private String doneOpt = "done";
    private String defaultOpt = "default";
    private String wrong = "my life";

    @BeforeEach
    void setUp() {
        Todo.clear("default");
    }

    @Test
    void makeNewItemDefault() {
        assertTrue(Todo.makeNewItem(defaultDesc, id1));
    }
    @Test
    void makeNewItemWithSameId() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.makeNewItem(defaultDesc, id1));
    }
    @Test
    void makeNewWrongIdItem() {
        assertFalse(Todo.makeNewItem(defaultDesc, wrong));
    }

    @Test
    void showItemDefault() {
        Todo.makeNewItem(defaultDesc, id1);
        assertTrue(Todo.showItem(id1));
    }
    @Test
    void showUndefinedItem() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.showItem(id2));
    }
    @Test
    void showWringIdItem() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.showItem(wrong));
    }

    @Test
    void showItemsDefaultWithDefOption() {
        Todo.makeNewItem(defaultDesc, id1);
        Todo.makeNewItem(defaultDesc, id2);
        assertTrue(Todo.showItems(defaultOpt));
    }
    @Test
    void showItemsDefaultWithOption() {
        Todo.makeNewItem(defaultDesc, id1);
        Todo.makeNewItem(defaultDesc, id2);
        Todo.setState(id1, 1);
        assertTrue(Todo.showItems(doneOpt));
    }
    @Test
    void showUndefinedItems() {
        assertFalse(Todo.showItems(defaultOpt));
    }
    @Test
    void showUndefinedItemsWithDoneOpt() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.showItems(doneOpt));
    }
    @Test
    void showUndefinedItemsWithWrongOpt() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.showItems(wrong));
    }

    @Test
    void clearItemsWithWrongOpt() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.clear(wrong));
    }
    @Test
    void clearItemsWithSomeOpt() {
        Todo.makeNewItem(defaultDesc, id1);
        Todo.makeNewItem(defaultDesc, id2);
        Todo.setState(id1, 1);
        assertTrue(Todo.clear(doneOpt));
    }

    @Test
    void deleteDefault() {
        Todo.makeNewItem(defaultDesc, id1);
        assertTrue(Todo.delete(id1));
    }
    @Test
    void deleteWrongIdItem() {
        assertFalse(Todo.delete(wrong));
    }

    @Test
    void setStateDefault() {
        Todo.makeNewItem(defaultDesc, id1);
        assertTrue(Todo.setState(id1, 1));
    }
    @Test
    void setWrongState() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.setState(id1, 5));
    }

    @Test
    void setDescriptionDefault() {
        Todo.makeNewItem(defaultDesc, id1);
        assertTrue(Todo.updateDescription(defaultOpt, defaultDesc, id1));
    }
    @Test
    void setDescriptionDefaultRewrite() {
        Todo.makeNewItem(defaultDesc, id1);
        assertTrue(Todo.updateDescription(rewriteOpt, additionalDesc, id1));
    }
    @Test
    void setDescriptionUndefinedId() {
        assertFalse(Todo.updateDescription(defaultOpt, defaultDesc, id1));
    }
    @Test
    void setDescriptionEmptyDesc() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.updateDescription(rewriteOpt, "", id1));
    }
    @Test
    void setDescriptionUndefinedOption() {
        Todo.makeNewItem(defaultDesc, id1);
        assertFalse(Todo.updateDescription(wrong, defaultDesc, id1));
    }
}