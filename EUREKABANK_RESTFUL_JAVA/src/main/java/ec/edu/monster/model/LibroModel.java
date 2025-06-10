package ec.edu.monster.model;

import java.sql.Date;

public class LibroModel {
    private int ID_LIBRO;
    private String TITULO_LIBRO;
    private String AUTOR_LIBRO;

    public int getID_LIBRO() {
        return ID_LIBRO;
    }

    public void setID_LIBRO(int ID_LIBRO) {
        this.ID_LIBRO = ID_LIBRO;
    }

    public String getTITULO_LIBRO() {
        return TITULO_LIBRO;
    }

    public void setTITULO_LIBRO(String TITULO_LIBRO) {
        this.TITULO_LIBRO = TITULO_LIBRO;
    }

    public String getAUTOR_LIBRO() {
        return AUTOR_LIBRO;
    }

    public void setAUTOR_LIBRO(String AUTOR_LIBRO) {
        this.AUTOR_LIBRO = AUTOR_LIBRO;
    }
    
    
    
}