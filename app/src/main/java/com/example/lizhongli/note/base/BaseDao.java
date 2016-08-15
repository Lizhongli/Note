package com.example.lizhongli.note.base;

import android.util.Log;
import com.example.lizhongli.note.model.BaseVO;
import com.example.lizhongli.note.utils.StringUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class BaseDAO<T extends BaseVO> {

    private static final String TAG = BaseDAO.class.getSimpleName();
    protected DBHelperOrmlite dbHelper;
    protected Class<T> entityClass;

    public boolean isColumnsExist(String colName) {
        boolean bExist = true;
        try {
            Dao entityDao = getEntityDao();
            QueryBuilder queryBuilder = entityDao.queryBuilder();
            queryBuilder.limit(1L);
            queryBuilder.where().isNotNull(colName);
            queryBuilder.query();
        } catch (Exception e) {
            Log.w(TAG, colName + " not exist");
            bExist = false;
        }

        return bExist;
    }

    public void onTableUpdate(String tablesVersionFileName, long tableCurrentVersion, long tableNewVersion) {

    }

    protected void onTableUpdateAfter(long tableCurrentVersion, long tableNewVersion) throws Exception {

    }

    protected void onTableUpdateIng(long tableCurrentVersion, long tableNewVersion) throws Exception {

    }


    protected void onTableUpdatePre(long tableCurrentVersion, long tableNewVersion) throws Exception {

    }


    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    public List<T> queryForAll() throws SQLException {
        Dao entityDao = getEntityDao();

        return entityDao.queryForAll();

    }


    public long countOf() throws SQLException {
        Dao entityDao = getEntityDao();
        return entityDao.countOf();
    }


    public int insert(T entityObj) throws SQLException {
        Dao entityDao = getEntityDao();
        return entityDao.create(entityObj);
    }


    public void insertOrUpdate(T entityObj) throws SQLException {
        Dao entityDao = getEntityDao();
        entityDao.createOrUpdate(entityObj);
    }

    public Object insertIfNotExists(T entityObj) throws SQLException {
        Dao entityDao = getEntityDao();
        return entityDao.createIfNotExists(entityObj);
    }


    public int update(T entityObj) throws SQLException {
        Dao entityDao = getEntityDao();
        return entityDao.update(entityObj);
    }

    public int delete(T entityObj) throws SQLException {
        Dao entityDao = getEntityDao();
        return entityDao.delete(entityObj);
    }


    public void batchInsert(final List<? extends T> entities) throws Exception {
        if (StringUtil.isEmptyObj(entities)) {
            Log.w(TAG, "batchInsert entities is empty.");
            return;
        }
        final Dao entityDao = getEntityDao();
        entityDao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws SQLException {

                for (int i = 0; i < entities.size(); i++) {
                    T entity = entities.get(i);
                    entityDao.create(entity);
                }

                return null;
            }
        });
    }

    public void batchUpdate(final List<? extends T> entities) throws Exception {
        if (StringUtil.isEmptyObj(entities)) {
            Log.w(TAG, "batchUpdate entities is empty.");
            return;
        }
        final Dao entityDao = getEntityDao();
        entityDao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws SQLException {
                for (int i = 0; i < entities.size(); i++) {
                    T entity = entities.get(i);
                    entityDao.update(entity);
                }
                return null;
            }
        });
    }

    public void batchUpdateWithoutLocalUpdateTime(final List<? extends T> entities) throws Exception {
        if (StringUtil.isEmptyObj(entities)) {
            Log.w(TAG, "batchUpdate entities is empty.");
            return;
        }
        final Dao entityDao = getEntityDao();
        entityDao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws SQLException {
                for (int i = 0; i < entities.size(); i++) {
                    T entity = entities.get(i);
                    entityDao.update(entity);
                }
                return null;
            }
        });
    }

    public void batchInsertOrUpdate(final List<? extends T> entities) throws Exception {
        if (StringUtil.isEmptyObj(entities)) {
            Log.w(TAG, "batchInsertOrUpdate entities is empty.");
            return;
        }

        final Dao entityDao = getEntityDao();
        entityDao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws SQLException {
                for (int i = 0; i < entities.size(); i++) {
                    T entity = entities.get(i);
                    // 索引越小，时间越大
                    entityDao.createOrUpdate(entity);
                }
                return null;
            }
        });
    }


    public void batchInsertIfNotExists(final List<? extends T> entities) throws Exception {
        if (StringUtil.isEmptyObj(entities)) {
            Log.w(TAG, "batchInsertOrUpdate entities is empty.");
            return;
        }

        final Dao entityDao = getEntityDao();
        entityDao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws SQLException {
                for (int i = 0; i < entities.size(); i++) {
                    T entity = entities.get(i);
                    // 索引越小，时间越大
                    entityDao.createIfNotExists(entity);
                }
                return null;
            }
        });
    }

    public void batchDelete(final List<? extends T> entities) throws Exception {
        if (StringUtil.isEmptyObj(entities)) {
            Log.w(TAG, "batchDelete entities is empty.");
            return;
        }
        final Dao entityDao = getEntityDao();
        entityDao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws SQLException {
                for (T entity : entities) {
                    entityDao.delete(entity);
                }
                return null;
            }
        });
    }

    protected Dao getEntityDao() throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao;
    }

    public T queryForId(Object id) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        Object result = entityDao.queryForId(id);
        if (result == null) {
            return null;
        } else {
            return (T) result;
        }

    }

    public Object queryForFirst(PreparedQuery preparedQuery) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);

        Object result = entityDao.queryForFirst(preparedQuery);
        if (result == null) {
            return null;
        } else {
            return (T) result;
        }
    }

    public List<T> queryForEq(String fieldName, Object value) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryForEq(fieldName, value);
    }

    public List<T> queryForMatching(Object matchObj) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryForMatching(matchObj);
    }

    public List<T> queryForMatchingArgs(Object matchObj) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryForMatchingArgs(matchObj);
    }

    public List<T> queryForFieldValues(Map fieldValues) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryForFieldValues(fieldValues);
    }

    public List<T> queryForFieldValuesArgs(Map fieldValues) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryForFieldValuesArgs(fieldValues);
    }

    public Object queryForSameId(Object data) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryForSameId(data);
    }

    public List<T> query(PreparedQuery preparedQuery) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.query(preparedQuery);
    }

    public int create(Object data) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.create(data);
    }

    public Object createIfNotExists(Object data) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.createIfNotExists(data);
    }

    public CreateOrUpdateStatus createOrUpdate(Object data) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.createOrUpdate(data);
    }

    public int updateId(Object data, Object newId) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.updateId(data, newId);
    }

    public int update(PreparedUpdate preparedUpdate) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.update(preparedUpdate);
    }

    public int deleteById(Object id) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.deleteById(id);
    }

    public int delete(Collection datas) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.delete(datas);
    }

    public int deleteIds(Collection ids) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.deleteIds(ids);
    }

    public int delete(PreparedDelete preparedDelete) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.delete(preparedDelete);
    }

    public GenericRawResults queryRaw(String query, String... arguments) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryRaw(query, arguments);
    }

    public GenericRawResults queryRaw(String query, RawRowMapper mapper, String... arguments) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryRaw(query, mapper, arguments);
    }


    public long queryRawValue(String query, String... arguments) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.queryRawValue(query, arguments);
    }

    public int executeRaw(String statement, String... arguments) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.executeRaw(statement, arguments);
    }

    public int executeRawNoArgs(String statement) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.executeRawNoArgs(statement);
    }

    public int updateRaw(String statement, String... arguments) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.updateRaw(statement, arguments);
    }

    public Object callBatchTasks(Callable callable) throws Exception {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.callBatchTasks(callable);
    }

    public Object extractId(Object data) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.extractId(data);
    }

    public long countOf(PreparedQuery preparedQuery) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.countOf(preparedQuery);
    }

    public boolean idExists(Object id) throws SQLException {
        Dao entityDao = dbHelper.getDao(entityClass);
        return entityDao.idExists(id);
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

}
