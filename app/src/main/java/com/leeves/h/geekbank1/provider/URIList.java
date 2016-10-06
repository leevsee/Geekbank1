package com.leeves.h.geekbank1.provider;
import com.leeves.h.geekbank1.database.DatabaseHelper;
/**
 * Function：
 * Created by h on 2016/8/18.
 *
 * @author Leeves
 */
public class URIList {
    public static final String CONTENT = "content://";
    public static final String AUTHORITY = "com.leeves.h.geekbank1";
    //content://com.leeves.geekband1/user   1
    //content://com.leeves.geekband1/book   2
    public static final String USER_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.USER_TABLE_NAME;
    public static final String BOOK_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.BOOK_TABLE_NAME;
}
