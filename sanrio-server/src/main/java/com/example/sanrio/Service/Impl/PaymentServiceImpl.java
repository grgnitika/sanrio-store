package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.Payment;
import com.example.sanrio.Entity.User;
import com.example.sanrio.Pojo.PaymentPojo;
import com.example.sanrio.Repo.OrderRepo;
import com.example.sanrio.Repo.PaymentRepo;
import com.example.sanrio.Repo.UserRepo;
import com.example.sanrio.Service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    @Override
    public Payment savePayment(PaymentPojo paymentPojo) {
        Payment payment = new Payment();

        if (paymentPojo.getId() != null) {
            Optional<Payment> existingPayment = paymentRepo.findById(paymentPojo.getId());
            if (existingPayment.isPresent()) {
                payment = existingPayment.get();
            } else {
                throw new IllegalArgumentException("Payment with id " + paymentPojo.getId() + " not found");
            }
        }

        String orderItemsAsString = paymentPojo.getOrderItems().stream()
                .map(String::valueOf) // Convert each Integer to String
                .collect(Collectors.joining(", "));


        // Validate paymentPojo fields if necessary
        payment.setOrderItems(orderItemsAsString);

        payment.setSubTotal(paymentPojo.getSubTotal());
        payment.setDeliveryFee(paymentPojo.getDeliveryFee());
        payment.setTotal(paymentPojo.getTotal());
        payment.setStatus("Paid");

        User user = userRepo.findById(paymentPojo.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + paymentPojo.getUserId()));

        payment.setUser(user);

        Payment successPayment= paymentRepo.save(payment);
        System.out.println(successPayment.getId());

        // Use a logging framework in production
        System.out.println("Payment processed successfully");
        return successPayment;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepo.findAll();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepo.findById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return null;
    }

    @Override
    public String update(Long id, PaymentPojo paymentPojo) {
        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with ID: " + id));

        User user = userRepo.findById(paymentPojo.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + paymentPojo.getUserId()));

        payment.setUser(user);
        payment.setStatus("Paid via Khalti");

        paymentRepo.save(payment);
        return "Payment Updated Successfully!";
    }

}
