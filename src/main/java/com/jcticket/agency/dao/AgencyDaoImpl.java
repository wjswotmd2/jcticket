package com.jcticket.agency.dao;

import com.jcticket.agency.dto.*;
import com.jcticket.agency.dto.EnrollDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.jcticket.agency.dao
 * fileName       : AgencyDaoImpl
 * author         : {sana}
 * date           : 2024-02-06
 * description    : 자동 주석 생성
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-02-06        {sana}       최초 생성
 */


//4.DaoImpl : AgencyDao의 interface를  implements한 AgencyDaoImpl class 라는 말 같음.
//그러니까 AgencyDao의 interface를 구현해서 db와 상호작용하며, SqlSession 객체를 주입받고
//그를 사용하여 매퍼에서 정의된 SQL 쿼리를 실행
@Repository
public class AgencyDaoImpl implements AgencyDao {

    private static final String namespace = "AgencyMapper.";
    //AgencyMapper.xml랑 연결?


    //SqlSession객체를  자동 연결해서 주입받음 (의존성 주입)
    @Autowired
    private SqlSession sqlSession;

    @Override
    public AgencyDto selectAgency(String agency_id) throws Exception {
        return sqlSession.selectOne(namespace + "AgencyLogin", agency_id);
    }//selectAgency 메서드는 SqlSession 객체를 사용해 db에서 쿼리를 실행?

    @Override
    public int insertAgency(AgencyDto agencyDto) throws Exception {
        return sqlSession.insert(namespace+"AgencyInsert", agencyDto);
    }

    @Override
    public int deleteAgency() throws Exception {
        return sqlSession.delete(namespace + "AgencyDelete");
    }

    @Override
    public int agencyCount() throws Exception {
        return sqlSession.selectOne(namespace + "AgencyCount");
    }

    @Override
    public void saveAgency(String agencyName) {
        String sql = "INSERT INTO agency (name) VALUES (?)";
        sqlSession.update(sql, agencyName);
    }


    @Override
    public void savePlay(String playName) {
        String sql = "INSERT INTO play (name) VALUES (?)";
        sqlSession.update(sql, playName);
    }

    @Override
    public void saveShowing(String showingName) {
        String sql = "INSERT INTO showing (name) VALUES (?)";
        sqlSession.update(sql, showingName);
    }

    @Override
    public void saveStage(String stageName) {
        String sql = "INSERT INTO stage (name) VALUES (?)";
        sqlSession.update(sql, stageName);
    }


    //로그인에 왜 CRUD를 전부 적어둬야 하는지 모르겠으나.. 시켜서 적는..

//    @Override
//    public void insertAgency(AgencyDto agencyDto) throws Exception {
//        sqlSession.insert(namespace + "insertAgency", agencyDto);
//    }
//
//    @Override
//    public void updateAgency(AgencyDto agencyDto) throws Exception {
//        sqlSession.update(namespace + "updateAgency", agencyDto);
//    }
//
//    @Override
//    public void deleteAgency(String agency_id) throws Exception {
//        sqlSession.delete(namespace + "deleteAgency", agency_id);
//    }










//    @Override //데이터를 데이터베이스에 저장할 save 메서드를 추가
//    public void save(AgencyDto agencyDto) throws Exception {
//        sqlSession.insert(namespace + "insertAgency", agencyDto);
//
//
//    }
//    @Override
//    public void deleteStage(String stage_id) throws Exception {
//        sqlSession.delete(namespace + "deleteStage", stage_id);
//    }
//
//    @Override
//    public void updateStage(StageDto stageDto) throws Exception {
//        sqlSession.update(namespace + "updateStage", stageDto);
//    }

}//selectOne 메서드를 사용해 db에서 한 개의 결과를 가져오는 쿼리를 실행하고, 결과를 AgencyDto 객체로 매핑해서 return