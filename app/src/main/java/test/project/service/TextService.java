package test.project.service;

import test.project.entity.Text;

import java.util.List;

public interface TextService {

    boolean save(Text text);

    boolean delete(Text text);

    List<Text> findAll();
}
