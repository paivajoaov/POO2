package model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class AviaoRepositorio {

    private static Database database;
    private static Dao<Aviao, Integer> dao;
    private List<Aviao> loadedAviões;
    private Aviao loadedAviao;

    public AviaoRepositorio(Database database) {
        AviaoRepositorio.setDatabase(database);
        loadedAviões = new ArrayList<>();
    }

    public static void setDatabase(Database database) {
        AviaoRepositorio.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Aviao.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Aviao.class);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Aviao create(Aviao aviao) {
        int nrows = 0;
        try {
            nrows = dao.create(aviao);
            if (nrows == 0)
                throw new SQLException("Error: object not saved");
            this.loadedAviao = aviao;
            loadedAviões.add(aviao);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return aviao;
    }

    public void update(Aviao aviao) {
        try {
            dao.update(aviao);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Aviao aviao) {
        try {
            dao.delete(aviao);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Aviao loadFromId(int id) {
        try {
            this.loadedAviao = dao.queryForId(id);
            if (this.loadedAviao != null)
                this.loadedAviões.add(this.loadedAviao);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedAviao;
    }

    public List<Aviao> loadAll() {
        try {
            this.loadedAviões = dao.queryForAll();
            if (this.loadedAviões.size() != 0)
                this.loadedAviao = this.loadedAviões.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedAviões;
    }
}
