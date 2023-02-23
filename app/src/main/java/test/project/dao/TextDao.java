package test.project.dao;

import test.project.entity.Text;

import java.util.List;

public interface TextDao {

    boolean save(Text text);

    boolean delete(Text text);

    List<Text> findAll();
}
