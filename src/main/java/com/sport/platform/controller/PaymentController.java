package com.sport.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.sport.platform.dto.PaymentRequest;
import com.sport.platform.entity.Student;
import com.sport.platform.payment.RazorpayUtil;
import com.sport.platform.service.PaymentService;
import com.sport.platform.service.StudentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private StudentService studentService;
    
    

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    // Create Razorpay Order
    @PostMapping("/create-order")
    public String createOrder(@RequestParam int amount) {

        return paymentService.createOrder(amount);
    }

    // Verify Payment
    @PostMapping("/verify")
    public String verifyPayment(@RequestParam String paymentId,
                                @RequestParam String orderId,
                                @RequestParam String signature) {

        boolean valid = RazorpayUtil.verifySignature(
                orderId,
                paymentId,
                signature,
                razorpaySecret
        );

        if (valid) {
            return "Payment Verified";
        }

        return "Payment Invalid";
    }
    
    @PostMapping("/complete-registration")
    public String completeRegistration(@RequestBody PaymentRequest request) {

        boolean valid = RazorpayUtil.verifySignature(
                request.orderId,
                request.paymentId,
                request.signature,
                razorpaySecret
        );

        
        if(!valid){
            return "Payment verification failed";
        }

        Student student = new Student();

        student.setName(request.fullName);
        student.setEmail(request.email);
        student.setPhone(request.phone);
        student.setSport(request.sport);
        student.setPlanType(request.planType);
        student.setAge(request.age);
        student.setLocation(request.location);

        studentService.registerStudent(student);

        return "Registration successful";
    }
}