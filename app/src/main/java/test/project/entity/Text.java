package test.project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "text")
@Entity
public class Text implements Serializable {

    @Column(name = "id_texts")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int textId;

    @Column(name = "name")
    private String text;

    public Text() {
    }

    public Text(int textId) {
        this.textId = textId;
    }

    public Text(int textId, String text) {
        this.textId = textId;
        this.text = text;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text1 = (Text) o;

        if (textId != text1.textId) return false;
        return text != null ? text.equals(text1.text) : text1.text == null;
    }

    @Override
    public int hashCode() {
        int result = textId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Text{" +
                "textId=" + textId +
                ", text='" + text + '\'' +
                '}';
    }
}
