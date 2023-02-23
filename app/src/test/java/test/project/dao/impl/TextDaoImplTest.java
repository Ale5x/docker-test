package test.project.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import test.project.config.DevConfig;
import test.project.dao.TextDao;
import test.project.entity.Text;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DevConfig.class)
@ActiveProfiles("dev")
class TextDaoImplTest {

    private Text text = new Text();

    @Autowired
    private TextDao textDao;

    @BeforeEach
    void setUp() {
        text.setText("Test");
    }

    @Test
    void save() {
        boolean condition = textDao.save(text);
        assertTrue(condition);
    }

    @Test
    void delete() {
        Text removeItem = new Text();
        removeItem.setTextId(1);
        int countBeforeDeleting = textDao.findAll().size();

        textDao.delete(removeItem);

        int countAfterDeleting = textDao.findAll().size();
        System.out.println("Count before/count after --->> " + countBeforeDeleting + "/" + countAfterDeleting);
        assertTrue(countBeforeDeleting > countAfterDeleting);
    }

    @Test
    void findAll() {
        List<Text> texts = textDao.findAll();
        assertFalse(texts.isEmpty());
        assertTrue(texts.size() > 0);
    }
}