package org.chulgang.hrd.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.chulgang.hrd.classroom.model.service.ClassroomService;
import org.chulgang.hrd.classroom.model.service.ClassroomServiceImpl;
import org.chulgang.hrd.classroom.model.service.TimePeriodService;
import org.chulgang.hrd.classroom.model.service.TimePeriodServiceImpl;
import org.chulgang.hrd.course.model.service.CourseService;
import org.chulgang.hrd.course.model.service.CourseServiceImpl;
import org.chulgang.hrd.course.model.service.SubjectService;
import org.chulgang.hrd.course.model.service.SubjectServiceImpl;
import org.chulgang.hrd.payment.model.service.PaymentService;

import org.chulgang.hrd.payment.model.service.PaymentServiceImpl;
import org.chulgang.hrd.reservation.model.service.ReservationService;
import org.chulgang.hrd.reservation.model.service.ReservationServiceImpl;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryService;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryServiceImpl;

import static org.chulgang.hrd.course.util.RequestConstant.*;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CourseService courseService = CourseServiceImpl.getInstance();
        sce.getServletContext().setAttribute(COURSE_SERVICE_ATTRIBUTE_NAME, courseService);

        SubjectService subjectService = SubjectServiceImpl.getInstance();
        sce.getServletContext().setAttribute(SUBJECT_SERVICE_ATTRIBUTE_NAME, subjectService);

        ClassroomService classroomService = ClassroomServiceImpl.getInstance();
        sce.getServletContext().setAttribute(CLASSROOM_SERVICE_ATTRIBUTE_NAME, classroomService);

        TimePeriodService timePeriodService = TimePeriodServiceImpl.getInstance();
        sce.getServletContext().setAttribute(TIME_PERIOD_SERVICE_ATTRIBUTE_NAME, timePeriodService);

        ReservationService reservationService = ReservationServiceImpl.getInstance();
        sce.getServletContext().setAttribute(RESERVATION_SERVICE_ATTRIBUTE_NAME, reservationService);

        PaymentService paymentService = PaymentServiceImpl.getInstance();
        sce.getServletContext().setAttribute(PAYMENT_SERVICE_ATTRIBUTE_NAME, paymentService);

        WalletHistoryService walletHistoryService = WalletHistoryServiceImpl.getInstance();
        sce.getServletContext().setAttribute(WALLET_HISTORY_SERVICE_ATTRIBUTE_NAME, walletHistoryService);

    }
}