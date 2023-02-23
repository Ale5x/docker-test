package test.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project.dao.TextDao;
import test.project.entity.Text;

import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    private TextDao dao;

    @Autowired
    public TextServiceImpl(TextDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean save(Text text) {
        return dao.save(text);
    }

    @Override
    public boolean delete(Text text) {
        return dao.delete(text);
    }

    @Override
    public List<Text> findAll() {
        return dao.findAll();
    }
}
