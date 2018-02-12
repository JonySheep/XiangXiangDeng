package DataUtil;

import Util.DocType;

import java.util.List;

public class SqlHelper {

    private String tableName;
    private StringBuffer sb;

    public SqlHelper(String tableName) {
        this.tableName = tableName;
    }

    public SqlHelper insert() {
        sb = new StringBuffer("insert into ");
        sb.append(tableName).append(" values(");
        return this;
    }

    public SqlHelper val(int val) {
        sb.append(val).append(',');
        return this;
    }

    public SqlHelper val(double val) {
        sb.append(val).append(',');
        return this;
    }

    public SqlHelper val(String val) {
        sb.append('\'').append(val).append("',");
        return this;
    }

    public SqlHelper valEnd() {
        sb.deleteCharAt(sb.length() - 1).append(") ");
        return this;
    }

    public SqlHelper update() {
        sb = new StringBuffer("update ");
        sb.append(tableName).append(" set ");
        return this;
    }

    public SqlHelper set(String col, String str) {
        sb.append(col).append(" = '").append(str).append("', ");
        return this;
    }

    public SqlHelper set(String col, int i) {
        sb.append(col).append(" = ").append(i).append(", ");
        return this;
    }

    public SqlHelper set(String col, double d) {
        sb.append(col).append(" = ").append(d).append(", ");
        return this;
    }

    public SqlHelper setEnd() {
        sb.deleteCharAt(sb.length() - 2);
        sb.append("where ");
        return this;
    }

    public SqlHelper delete() {
        sb = new StringBuffer("delete from ");
        sb.append(tableName).append(" where ");
        return this;
    }

    public SqlHelper select() {
        sb = new StringBuffer("select * from ");
        sb.append(tableName).append(" where ");
        return this;
    }

    public SqlHelper key(String col, String str) {
        sb.append(col).append(" = '").append(str).append("' and ");
        return this;
    }

    public SqlHelper key(String col, int i) {
        sb.append(col).append(" = ").append(i).append(" and ");
        return this;
    }

    public SqlHelper keyEnd() {
        int len = sb.length();
        sb.delete(len - 4, len);
        return this;
    }

    public SqlHelper orderBy(List<String> cols, List<Boolean> desc) {
        sb.append("order by ");
        for (int i = 0; i < cols.size() && i < desc.size(); i++) {
            sb.append(cols.get(i)).append(' ');
            if (desc.get(i))
                sb.append("desc,");
            else
                sb.append("asc,");
        }
        sb.setCharAt(sb.length() - 1, ' ');
        return this;
    }

    public SqlHelper custom(String append) {
        sb.append(append);
        return this;
    }

    public void end() {
        sb.append(';');
    }

    public String toString() {
        return sb.toString();
    }
}
