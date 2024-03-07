package com.jcticket.category.dao;

import java.util.List;
import java.util.Map;

/**
 * packageName :  com.jcticket.category.dao
 * fileName : CategoryDao
 * author :  jisoo Son
 * date : 2024-03-06
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2024-03-06             jisoo Son             최초 생성
 */
public interface CategoryDao {

    List<Map<String, Object>> selectConcertList() throws Exception;
    List<Map<String, Object>> selectMusicalList() throws Exception;
    List<Map<String, Object>> selectTheaterList() throws Exception;
    List<Map<String, Object>> selectClassicList() throws Exception;
}