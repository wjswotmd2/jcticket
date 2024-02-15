package com.jcticket.agency.controller;

import com.jcticket.agency.dao.AgencyDao;
import com.jcticket.agency.dto.AgencyDto;
import com.jcticket.agency.dto.EnrollDto;
import com.jcticket.agency.dto.PlayDto;
import com.jcticket.agency.service.AgencyService;
import com.jcticket.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.spec.ECField;
import java.util.Objects;

/**
 * packageName    : com.jcticket.agency.controller
 * fileName       : AgencyController
 * author         : {sana}
 * date           : 2024-02-02
 * description    : 자동 주석 생성
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-02-02        {sana}       최초 생성
 */



@Controller
@RequestMapping("/agency")//이 클래스의 모든 메서드는 /agency 경로에 매핑

public class AgencyController {

    @Autowired
    private AgencyService agencyService;//AgencyService 인터페이스를 사용하기 위해 의존성을 주입 받음




    //RequestMapping("/agency")을 해 줬기 떄문에 GetMapping("/processLogin 만 적어도
    @GetMapping("/processLogin")// /agency/processLogin 경로로 POST 요청을 보내면, 이 메서드가 실행되어 로그인을 처리함
    public String showAgencyLoginForm() {

        return "viewdetail/login"; //로그인 폼을 보여주는 페이지의 경로


    }

//    @RequestMapping(value = "/agencyenroll/insert_data", method = RequestMethod.POST)
//    public String insert_data(@ModelAttribute agencyEnroll agencyenroll){
//        System.out.print(agencyenroll.toString());
//        return "redirect:/agency/agencyenroll";
//    }

//--------------실패시 얼럿창, 서비스단에서--------------
//@PostMapping("/processLogin")
//public String processAgencyLogin(String agency_id, String agency_pwd, boolean rememberId, Model m,
//                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//    if (!agencyLoginCheck(agency_id, agency_pwd)) {
//        // 로그인이 실패한 경우, 로그인 폼으로 리다이렉션하되 파라미터를 전달하지 않도록 하여서 입력값을 url에 보여주지 않음
//        return "redirect:/login";
//    }
//
//        HttpSession session = request.getSession();//로그인이 성공하면 세션에 기관 ID를 저장
//        session.setAttribute("agency_id", agency_id);
//
//        System.out.println("rememberId = " + rememberId);
//
//
//        return "/agency/agencydashboard";//로그인 성공 시
//    }
    //-------------------------------------------------------------------------------
@PostMapping("/processLogin")
public String processAgencyLogin(String agency_id, String agency_pwd, boolean rememberId, Model model,
                                 HttpServletRequest request) {
    try {
        if (agencyService.processAgencyLogin(agency_id, agency_pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute("agency_id", agency_id);
            return "/agency/agencydashboard"; // 성공 시 대시보드 페이지로 이동
        } else {
            model.addAttribute("loginError", true);
            return "redirect:/login"; // 실패 시 로그인 페이지를 다시 표시
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "error"; // 예외 발생 시 로그인 ? 에러?
    }
}


//    @PostMapping("/submitForm")
//    public String submitForm(@ModelAttribute("enrollDto") EnrollDto enrollDto) {
//        agencyService.saveFormData(enrollDto);
//        return "redirect:/success"; // 성공 페이지로 리다이렉트
//    }




    @GetMapping("/dashboard")//홈
    public String showAgencyDashboard(Model model) {
        model.addAttribute("dashboardPage", "agency/agencydashboard");
        return "agency/agencydashboard";
    }

    @GetMapping("/enroll")//신청서페이지
    public String agencyEnroll() {
        try {
            return "agency/agencyenroll";
        } catch (Exception e) {
            return "error"; //존재하지는 않지만 우선 에러 페이지로 이동
        }
    }



    @PostMapping("/enroll")
    public String enroll(@ModelAttribute AgencyDto agencyDto) {
        agencyService.saveFormData(new EnrollDto());
        return "redirect:/success"; // 성공 페이지 필요할까?
    }





//    @GetMapping("/productlist")
//    public  String agencyproductlist() throws Exception{
//        return "agency/agencyproductlist";
//    }

    @GetMapping("/sale")//판매
    public String agencysale() {
        try {
            return "agency/agencysale";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/rollout")//예정
    public String agencyrollout() {
        try {
            return "agency/agencyrollout";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/soldout")//종료
    public String agencysoldout() {
        try {
            return "agency/agencysoldout";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/setting")//셋팅
    public String agencysetting() {
        try {
            return "agency/agencysetting";
        } catch (Exception e) {
            return "error";
        }
    }


//    private boolean agencyLoginCheck (String agency_id, String agency_pwd) {
//        AgencyDto agencyDto = null;
//        System.out.println("agency_id = " + agency_id);
//        System.out.println("agency_pwd = " + agency_pwd);
//        try {
//            agencyDto = agencyService.selectAgency(agency_id);//호출하여 해당  정보를 가져옴. 가져온 정보와 입력된 비밀번호를 비교하여 로그인 여부를 확인
//            System.out.println(agencyDto);//로그인이 성공하면 AgencyDto 객체를 반환
//        } catch (Exception e) {
//            e.printStackTrace();//예외가 발생하면 스택 트레이스를 출력하고, 로그인 실패를 의미하는 null을 반환
//        }
//
//        return agencyDto != null && agencyDto.getAgency_password().equals(agency_pwd);
//
//
//    }


//    @PostMapping("/processEnroll")
//    public String processAgencyEnroll(@ModelAttribute AgencyEnrollmentForm enrollmentForm) {
//        try {
//            // AgencyEnrollmentForm 데이터를 Service 계층으로 전달하여 처리
//            agencyService.processEnrollment(enrollmentForm);
//        } catch (Exception e) {
//            e.printStackTrace(); // 오류 발생 시 스택 트레이스 출력
//            // 오류 처리 로직 추가 가능
//            return "error"; // 예시: 오류 페이지로 리다이렉트
//        }
//
//        // 처리 후 이동할 페이지 지정 (예: 등록 완료 후 메인 대시보드 페이지)
//        return "redirect:/agency/dashboard";
//    }



}

    // 기획사 로그인 체크
//    private boolean loginCheck(String agencyId, String agencyPwd) {
//        try {
//            // 기획사 정보를 데이터베이스에서 가져오는 로직
//            AgencyDto agencyDto = agencyDao.selectAgency(agencyId);
//
//            // 기획사 정보가 존재하고 비밀번호가 일치하는지 확인
//            if (agencyDto != null && agencyDto.getAgency_password().equals(agencyPwd)) {
//                return true; // 로그인 성공
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false; // 로그인 실패
//    }
